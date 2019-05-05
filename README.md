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

## Using the app
Click `Choose File` and select a CSV of contact records in the bellow format:

```text
id | first_name | last_name | company | email              | address1    | address2 | zip   | city | state_long    | state | phone
-----------------------------------------------------------------------------------------------------------------------------------------
1  | nathan     | lucas     | bnlucas | nathan@bnlucas.com | 123 Main St | Apt 1    | 01234 | Test | Massachusetes | MA    | 999-555-1234
 ```
 
 Once the file is selected, click `Upload` and wait for the tables below to populate.