# Areeba Phone Customer Management Microservice
 
A Java Spring Boot microservice that manage crud operations for the customers
 
## Features

- Create new customer
- Update Customer
- Delete Customer
- Get customer(s)
 
## Getting Started
 
### Prerequisites
 
- Java 17 or higher - [Download Java](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- Maven - [Learn more about Maven](https://maven.apache.org/)
- Postgres database - https://www.postgresql.org/download/
 
### Installation
 
1. Clone the repository:
   ```
   git clone https://github.com/johnnyam8/areeba_customer_management.git
   ```
3. Configure PostgreSQL:
    - Set up your PostgreSQL database.
      
2. Change directory:
   ```
   cd areeba_customer_management
   ```
3. Application Configuration:
   - Modify the `application.properties` file located in the `src/main/resources` directory of your project to configure parameters.
      - **Database Configuration**:
         - Configure the database connection details as follows, replacing the default values if necessary:
           ```properties
            spring.datasource.url = ${DATASOURCE_URL:jdbc:postgresql://localhost:5432/postgres}
            spring.datasource.username = ${DATASOURCE_USERNAME:postgres}
            spring.datasource.password = ${DATASOURCE_PASSWORD:postgres}
           ```
      - **Flyway Configuration**:
         - Configure the flyway parameters, the parameters that related to the database connection should match the params configured above. 
           ```properties
            spring.flyway.enabled = ${FLYWAY_ENABLED:true}
            spring.flyway.locations = ${FLYWAY_LOCATION:filesystem:./flyway/sql}
            spring.flyway.password = ${DATASOURCE_PASSWORD:postgres}
            spring.flyway.url = ${DATASOURCE_URL:jdbc:postgresql://localhost:5432/postgres}
            spring.flyway.user = ${DATASOURCE_USERNAME:postgres}
            spring.flyway.schemas= ${DB_SCHEMA:areeba}
           ```
     - **Phone Validation Service Configuration**:
      - You can specify the url host
       ```properties
            mobileservice.base.url=${MOBILE_SERVICE_URL:http://localhost:8080}
       ```
      - Since we need to validate customer mobile number,  the areeba_phone_validation microservice should be up and running.
        
      - Kindly read and follow the instruction in the readme file provided the following link -  https://github.com/johnnyam8/areeba_phone_validation
            
   - These environment variables can be overridden by setting them in your operating system, or passing them as command-line parameters when starting the 
      application.
     
3. **Build the Application**:
    - Run the following command to build the application:
      ```
      ./mvnw clean install
      ```
4. **Run the Application**:
    - Start the application with:
      ```
      ./mvnw spring-boot:run
      ```
    - The microservice will run on http://localhost:8081.
    - Verify the application is running by checking the output logs.
      
5. **Run the Application Using Docker** :
    -  Download docker and follow the details installation  - https://docs.docker.com/get-docker/
      
    - Navigate to the project directory: 
      ```
       cd areeba_customer_management
      ```
    - Clone areeba_phone_validation - https://github.com/johnnyam8/areeba_phone_validation.git and put it in the same folder.
        
    - Application Configuration:
     - When running the application using docker, the communication between the two microservices and the database is done
        between the docker containers, as result the connection url and the Phone validation service url should be the changed.
        
    - Modify the `application.properties` file located in the `src/main/resources` directory of your project to configure parameters.
      - **Database Configuration**:
         - Configure the database connection details in order to connect to the containner database:
           ```properties
            spring.datasource.url = ${DATASOURCE_URL:jdbc:postgresql://postgres-db:5432/postgres}
            spring.datasource.username = ${DATASOURCE_USERNAME:postgres}
            spring.datasource.password = ${DATASOURCE_PASSWORD:postgres}
           ```
      - **Flyway Configuration**:
         - Configure the flyway parameters, the parameters that related to the database connection should match the params configured above. 
           ```properties
            spring.flyway.enabled = ${FLYWAY_ENABLED:true}
            spring.flyway.locations = ${FLYWAY_LOCATION:filesystem:./flyway/sql}
            spring.flyway.password = ${DATASOURCE_PASSWORD:postgres}
            spring.flyway.url = ${DATASOURCE_URL:jdbc:postgresql://postgres-db:5432/postgres}
            spring.flyway.user = ${DATASOURCE_USERNAME:postgres}
            spring.flyway.schemas= ${DB_SCHEMA:areeba}
           ```
     - **Phone Validation Service Configuration**:
      - You can specify the url host
       ```properties
            mobileservice.base.url=${MOBILE_SERVICE_URL:http://areeba_phone_validation:8080}
       ```
   -  Build the docker compose: 
       ```
        docker-compose build
       ```
    -  Run the docker compose:
     ```
        docker-compose up
     ```
     -  Verify the application is running by checking the output logs or by running the following command:
     ```
        docker ps
     ```
     
### Usage
 
Access the Swagger API documentation at http://localhost:8081/swagger-ui/index.html#/ for details on API usage.
 
## Contribution
 
Contributions are welcome! If you have feedback, bug reports, or pull requests, please submit them through the project's GitHub page.
 
## Author
 
Johnny Abi Mansour


