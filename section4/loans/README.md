# Recommendation for images:

- Dockerfiles: Used almost everywhere because they work across languages and platforms.
- Buildpacks: Common in organizations using Spring Boot, Cloud Foundry, or platforms that favor convention over configuration.
- Google Jib: Popular among Java teams, especially those focused on Maven/Gradle and optimized build pipelines.


# RUN IT USING BUILD PACK

It must be included the follower code into the maven-plugin:

```maven
<image>
    <name>${dockerUser}/${project.artifactId}:${tag}</name>
</image>
```

After that you can run the following command to build the image:

mvn spring-boot:build-image

After create the image, you can check using the command:

docker images

Run the following command to run it:

docker run -d -p 8090:8090 gabrielmorato139/loans:s4