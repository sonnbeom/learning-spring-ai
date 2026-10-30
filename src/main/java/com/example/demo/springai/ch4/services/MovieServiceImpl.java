package com.example.demo.springai.ch4.services;

import com.example.demo.springai.ch4.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService{

    private final ChatClient chatClient;

    public MovieServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public Movie findMovie(String title){
        return chatClient.prompt()
                .user(u -> u.text("영화 {title}의 제목, 개봉연도, 감독을 알려줘").param("title", title))
                .call()
                .entity(Movie.class);
    }

    @Override
    public List<Movie> findMoviesByDirector(String director, int count){
        return chatClient.prompt()
            .user(u -> u.text("{director} 감독의 대표작 {count}편을 알려줘")
                    .param("director", director)
                    .param("count", String.valueOf(count)))
            .call()
            .entity(new ParameterizedTypeReference<List<Movie>>() {});
    }

    @Override
    public String askRawJsonTrap(String title){
        String raw = chatClient.prompt()
                .user(u -> u.text("영화 {title}의 정보를 title, year, director 필드를 가진 JSON으로 알려줘")
                        .param("title", title))
                .call()
                .content();
        log.info("TRAP RAW >>> {}", raw);
        return raw;
    }

    @Override
    public String askRawJsonFixed(String title){
        String raw = chatClient.prompt()
                .user(u -> u.text("""
                                영화 {title}의 정보를 title, year, director 필드를 가진 JSON으로 알려줘
                                Resposne in JSON format without markdown tags.
                                """)
                        .param("title", title))
                .call()
                .content();
        log.info("FIXED RAW >>> {}", raw);
        return raw;
    }

    @Override
    public Movie describeFormat() {
        return null;
    }

}
