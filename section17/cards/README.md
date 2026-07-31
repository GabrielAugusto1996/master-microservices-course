# Recommendation for images:

- Dockerfiles: Used almost everywhere because they work across languages and platforms.
- Buildpacks: Common in organizations using Spring Boot, Cloud Foundry, or platforms that favor convention over configuration.
- Google Jib: Popular among Java teams, especially those focused on Maven/Gradle and optimized build pipelines.

# Configure it using Google Jib

REFERENCE: https://github.com/googlecontainertools/jib

First must be included the follower plugin into the pom.xml:

```maven
<plugin>
<groupId>com.google.cloud.tools</groupId>
<artifactId>jib-maven-plugin</artifactId>
<version>3.5.1</version>
<configuration>
  <to>
    <image>gabrielmorato139/${project.artifactId}:${project.version}</image>
  </to>
</configuration>
</plugin>
```

# Build the image

Run the command to build the image:

```bash
mvn compile jib:build
```

# Validate the images

```bash
docker images
```

# Run the image:

```bash
docker run -d -p 9000:9000 gabrielmorato139/cards:0.0.1-SNAPSHOT  
```