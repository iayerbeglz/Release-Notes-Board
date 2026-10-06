# notesboard-app

## Requirements

For building and running the application you need:

- [JDK 17](https://docs.aws.amazon.com/es_es/corretto/latest/corretto-17-ug/downloads-list.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.dbcargo.notesoard.NotesboardApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```bash
./mvnw spring-boot:run        # http://localhost:8080 , in-memory H2 database (default "test" profile)
./mvnw verify                 # tests + coverage report in target/site/jacoco/index.html
```

| What | Where |
|------|-------|
| Swagger UI / OpenAPI document (not in production) | `/swagger-ui.html`, `/v3/api-docs` |
| H2 console (localhost only) | `/h2-console` |

### Using the API

```bash
# 1. List application entries
curl --location 'http://localhost:8080/api/applications' \
--data ''

# 2. List item entries
curl --location 'http://localhost:8080/api/items' \
--data ''

# 3. Create item entries
curl --location 'http://localhost:8080/api/items' \
--header 'Content-Type: application/json' \
--data '{
    "release": "v2",
    "description": "Release notes v2",
    "status": "REVIEW"
}'

# 4. Update item entry status
curl --location --request PATCH 'http://localhost:8080/api/items/1' \
--header 'Content-Type: application/json' \
--data '{
    "status": "PUBLISHED"
}'
```

### Configuration

Common settings live in `src/main/resources/application.yml`; the `dev` profile uses H2 and `prod`
profile use Postgres and only override what differs. Everything environment specific is read from environment
variables,

| Variable | Purpose |
|----------|---------|
| `SPRING_PROFILES_ACTIVE` | `dev` , `prod` |
| `DB_USERNAME`, `DB_PASSWORD` | H2 connection |

### Short architecture decisions

A basic springboot application, with a database-driven microservice and layered arcitecture
As an additional behaviour, each time an item is saved, and event would be sent to inform the consumers

The Swagger needs to be fullfilled in each Controller to expose API Rest endpoint to FrontEnd or other microservices consumers

The Angular frontend would consume the API Rest exposed by the backend microservice and I will create 2 different docker files to isolate the development work of the teams
