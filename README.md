# Contacts Records
Finding potential duplicates within an uploaded CSV file using Metaphone tokens.

## Running the app
This is a Spring Boot app with a React client app bundled with it. To run, simply use
```text
./mvnw spring-boot:run
```

This in turn runs the following to start up the React app:
```text
# installs node and npm
npm install
npm run build
```

Once the app has booted up, you can access it at [http://localhost:8080](http://localhost:8080)
