# Recommendation for images:

- Dockerfiles: Used almost everywhere because they work across languages and platforms.
- Buildpacks: Common in organizations using Spring Boot, Cloud Foundry, or platforms that favor convention over configuration.
- Google Jib: Popular among Java teams, especially those focused on Maven/Gradle and optimized build pipelines.


# RUN IT USING DOCKER FILE

NOTE: The -t (tags) reference to the tag name that will be generate for the Docker, the s4 means that we are into the section 4 for this course.

```bash
docker build . -t eazybytes/accounts/s4
```

! You can run the commands 

```bash
docker images --> LIST ALL OF THE IMAGES
```

```bash
docker inspect <IMAGE_ID> --> CHECK THE DETAILS FOR THE DOCKER IMAGE
```

```bash
docker run -d -p 8080:8080 eazybytes/accounts/s4 --> Start the docker using the port 8080, to expose the docker outside the docker network.
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