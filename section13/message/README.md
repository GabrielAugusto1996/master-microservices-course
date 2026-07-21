# Recommendation for images:

- Dockerfiles: Used almost everywhere because they work across languages and platforms.
- Buildpacks: Common in organizations using Spring Boot, Cloud Foundry, or platforms that favor convention over configuration.
- Google Jib: Popular among Java teams, especially those focused on Maven/Gradle and optimized build pipelines.


# RUN IT USING DOCKER FILE

NOTE: The -t (tags) reference to the tag name that will be generate for the Docker, the s4 means that we are into the section 4 for this course.

```bash
docker build . -t <USER>/<IMAGE_NAME>:<TAG>
```

For example:

docker build . -t gabrielmorato139/accounts:0.0.1-SNAPSHOT

! You can run the commands 

```bash
docker images --> LIST ALL OF THE IMAGES
```

```bash
docker inspect <IMAGE_ID> --> CHECK THE DETAILS FOR THE DOCKER IMAGE
```

```bash
docker run -d -p 8080:8080 gabrielmorato139/accounts/s4 --> Start the docker using the port 8080, to expose the docker outside the docker network.
```

-d --> detached mode
-p --> port mapping

- First 8080 --> Custom port
- Second 8080 --> Network port

# Adding the config server:

On the application.yml must be included the follower code:

```yml
  config:
    import: "optional:configserver:http://localhost:8071/"
```

NOTE: The "optional" means that if there are some issue to run the configserver, it should not be a blocker to start the application.

# Configserver in the pom:

```xml

Adding into the properties:
	<properties>
		<java.version>17</java.version>
		<spring-cloud.version>2025.1.2</spring-cloud.version>
	</properties>
```
NOTE: The version of the spring-cloud depends of your spring version.

---

Adding into the dependencies:
```xml
<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
```
---

```xml
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
```

# Enable actuator:

In the application.yml must be added it:

```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

### Actuator Refresh

http://localhost:8080/actuator/refresh -> POST

Its used to refresh on real time the configserver configurations.

---

# Adding Rabbit MQ:

Install using this command for docker:

```bash
# latest RabbitMQ 4.x
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management
```

After that include the dependency in the pom.xml:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bus-amqp</artifactId>
</dependency>
```

Include it into the application.yml:

```yaml
  rabbitmq:
    host: "localhost"
    port: 5672
    username: "guest"
    password: "guest"
```

Actuator call POST request for refresh using Service BUS:

http://localhost:8080/actuator/busrefresh

# Running a MySQL image using docker:

```bash
docker run -p 3306:3306 --name accountsdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=accountsdb -d mysql
```

# Running a Postgres image using docker:

Create a docker file:

```bash
services:
  accountsdb:
    image: postgres:17
    container_name: accountsdb
    restart: unless-stopped
    environment:
      POSTGRES_USER: root
      POSTGRES_PASSWORD: root
      POSTGRES_DB: accountsdb
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
```

Running the docker file:

```bash
docker compose -f postgres.yml up -d
```

# How to run redis:

```bash
docker run -p 6379:6379 --name eazyredis -d redis
```

Using hookdeck to test grafana alerts:

https://console.hookdeck.com/

# Start KeyCloak using Docker:

```bash
docker run -d --name keycloak -p 7080:8080 -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:latest start-dev
```

Open it using: http://localhost:7080/admin/

# How to configure the Spring Boot Function:

```xml
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-function-context</artifactId>
		</dependency>

		<!--
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-function-web</artifactId>
		</dependency> Uncomment it if you want to use Spring Cloud Function as REST API-->
```

So include it and your configuration:

```java
package com.eazybytes.message.functions;

import com.eazybytes.message.dto.AccountMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * The type Message functions.
 */
@Configuration
public class MessageFunctions {

    private static final Logger logger = LoggerFactory.getLogger(MessageFunctions.class);

    /**
     * Email function.
     *
     * @return the function
     */
    @Bean
    public Function<AccountMessageDto, AccountMessageDto> email() {
        return  accountMessageDto -> {
            logger.info("Sending email for accounting to the email: {}", accountMessageDto.email());

            return accountMessageDto;
        };
    }

    /**
     * Sms function.
     *
     * @return the function
     */
    @Bean
    public Function<AccountMessageDto, Long> sms() {
        return  accountMessageDto -> {
            logger.info("Sending sms for accounting to the mobile number: {}", accountMessageDto.mobileNumber());

            return accountMessageDto.accountNumber();
        };
    }
}
```

# Spring Cloud Stream

Add the folowing dependencies:

```xml
<dependencies>
		<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-amqp</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.amqp</groupId>
		<artifactId>spring-rabbit-stream</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-stream</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-stream-binder-rabbit</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
	</dependency>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-stream-test-binder</artifactId>
		<scope>test</scope>
	</dependency>
	</dependencies>
```