# Recommendation for images:

- Dockerfiles: Used almost everywhere because they work across languages and platforms.
- Buildpacks: Common in organizations using Spring Boot, Cloud Foundry, or platforms that favor convention over configuration.
- Google Jib: Popular among Java teams, especially those focused on Maven/Gradle and optimized build pipelines.


# RUN IT USING BUILD PACK

It must be included the follower code into the maven-plugin:

```maven
<image>
    <builder>paketobuildpacks/builder-jammy-base</builder>
    <name>gabrielmorato139/${project.artifactId}:${project.version}</name>
    <env>
        <BP_JVM_VERSION>17</BP_JVM_VERSION>
    </env>
</image>
```
Consideration about the builders for builderpack:

paketobuildpacks/builder-jammy-base ✅ Recommended for most apps
paketobuildpacks/builder-jammy-tiny (smaller images)
paketobuildpacks/builder-jammy-full (includes more build tools)

After that you can run the following command to build the image:

```bash
mvn spring-boot:build-image
```

After create the image, you can check using the command:

```bash
docker images
```

Run the following command to run it:

```bash
docker run -d -p 8090:8090 gabrielmorato139/loans:s4
```

Push the image to the docker:

```bash
docker image push docker.io/${dockerImage}
```