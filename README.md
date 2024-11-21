# Race application command service

# Configuration and Installation

1. Setup development environment via docker compose if you don't do that with race-application-command-service microservice
```
version: '3.9'

services:
  rabbitmq:
    image: rabbitmq:management
    container_name: rabbitmq
    ports:
      - "5672:5672" # RabbitMQ broker port
      - "15672:15672" # RabbitMQ management console
    environment:
      RABBITMQ_DEFAULT_USER: guest
      RABBITMQ_DEFAULT_PASS: guest

  postgres:
    image: postgres:15
    container_name: postgres
    ports:
      - "5432:5432"
    environment:
      POSTGRES_USER: race_user
      POSTGRES_PASSWORD: race_password
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:

```
2. Configure database
```
CREATE USER race_user WITH PASSWORD 'race_password';
CREATE DATABASE race_db WITH ENCODING 'UTF8' LC_COLLATE 'C.UTF-8' LC_CTYPE 'C.UTF-8' TEMPLATE template0;
GRANT USAGE, CREATE ON SCHEMA public TO race_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO race_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO race_user;
```
3. Configure app **before** building:

   The basic configuration is defined in [application.properties](src/main/resources/application.properties):
    - You _must_ create application-local.properties file and configure all properties from [application-local-template.properties](src/main/resources/application-local-template.properties) file


4. Build app:

   To compile app simply use Maven. A runnable `jar` file containing the application and configuration will be created in the subdirectory `/target`.
   For windows run following command
    ```
    # .\mvnw.cmd package -DskipTests
    ```

5. Run app:

   To start the application run (please do not run app as root):

    ```
    # java -jar .\target\race-application-query-service-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
    ```
   