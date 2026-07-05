# Google chrome extensions:

- JSONView: You can use this extension to see the JSON in the best format as possible: https://chromewebstore.google.com/detail/jsonview/gmegofmjomhknnokphhckolhcffdaihd

# How to encrypt values:

    email: "{cipher}d0387d54ffe815c67ccb337582583ec94372402134c19d78cedb9e532d95276c75047fa002ab3c987afbd55f9aacf475"

Must always include the {cipher} before to value.

# Generate it by the POST call:

http://localhost:8071/encrypt

# Application.yml add it:

```yaml
encrypt:
  key: "45D81EC1EF61DF9AD8D3E5BB397F9"
```

# Decrypt the values POST call:

http://localhost:8071/decrypt

# How to enabled Health Liveness and Readiness:

It must be included these changes into the application.yml:

```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
  health:
    readiness-state:
      enabled: true
    liveness-state:
      enabled: true
  endpoint:
    health:
      probes:
        enabled: true
```

After that it will be able to test using the follower endpoints (GET):

http://localhost:8071/actuator/health -> Check the health for the whole microservice

http://localhost:8071/actuator/health/liveness -> Check if the liveness its ok

http://localhost:8071/actuator/health/readiness -> Check if the readiness its ok

# Adding the validation into the docker-compose:

Must be provided the configuration:

```yaml
    deploy:
      resources:
        limits:
          memory: 700m
    healthcheck:
      test: "curl --fail --silent localhost:8071/actuator/health/readiness | grep UP || exit 1"
      interval: 10s
      timeout: 5s
      retries: 10
      start_period: 10s
```
Details about the command: 

- curl --> makes an HTTP request.
- localhost:8071 --> means it connects to port 8071 on the same machine.
- /actuator/health/readiness --> is a readiness health endpoint, commonly exposed by applications using Spring Boot with Spring Boot Actuator.
- --fail -->
Causes curl to return a non-zero exit code if the HTTP response is an error (4xx or 5xx).
It also suppresses printing the error page.
- --silent -->
Suppresses progress bars and other unnecessary output.

