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