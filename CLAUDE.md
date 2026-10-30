# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

A freshly generated Spring Initializr project for experimenting with Spring AI. There is no application code yet beyond the `@SpringBootApplication` entry point (`com.example.demo.DemoApplication`).

- Java 21, Maven (wrapper pinned to Maven 3.10.0). The machine's default JDK is 17 (other projects need it), so `maven-toolchains-plugin` in `pom.xml` selects JDK `${java.version}` from `~/.m2/toolchains.xml` for compile/test/run. Don't change the global `JAVA_HOME`; register new JDKs in `toolchains.xml` instead.
- Spring Boot 4.1.2 (parent POM), using the Boot 4 modular starters `spring-boot-starter-webmvc` / `spring-boot-starter-webmvc-test`
- Spring AI 2.0.1, managed through `spring-ai-bom` in `<dependencyManagement>`. Add other Spring AI modules without a version and let the BOM supply it.
- Model provider: OpenAI via `spring-ai-starter-model-openai`, which auto-configures a `ChatModel` and a `ChatClient.Builder` for injection.

## Commands

On Windows use `mvnw.cmd` (or `.\mvnw` in PowerShell); on POSIX shells use `./mvnw`.

```bash
./mvnw spring-boot:run                                        # run the app (port 8080 by default)
./mvnw test                                                   # run all tests
./mvnw test -Dtest=DemoApplicationTests                       # run one test class
./mvnw test -Dtest=DemoApplicationTests#contextLoads          # run one test method
./mvnw clean package                                          # build the jar into target/
./mvnw spring-boot:build-image                                # build an OCI image
```

No linter or formatter is configured.

## Configuration

Configuration lives in `src/main/resources/application.yaml` (YAML, not `.properties`). The OpenAI starter needs an API key at startup. Supply it with the `SPRING_AI_OPENAI_API_KEY` environment variable or `spring.ai.openai.api-key`, and never commit it. Without a key, app startup and the `@SpringBootTest` context-load test (`DemoApplicationTests`) will fail. Model options go directly under `spring.ai.openai.chat.*` (for example `model`, `temperature`). The Spring AI 1.x `spring.ai.openai.chat.options.*` keys are deprecated in 2.0.

## Conventions

The existing sources indent with tabs (Spring Initializr style). Keep doing that.
