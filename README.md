# Example database initializers

In ProductInitlizer set proper path to 'products.csv'

# Postgresql database

run in terminal: docker-compose up

# OpenAPI

Each start of Spring server generate axtual api
You can check schemas here: http://localhost:8080/swagger-ui/index.html#
JSON file from http://localhost:8080/v3/api-docs needs to be saved in frontend/src/openapi/openapi.json
Then run command in frontend directory: npm run api-gen
