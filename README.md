# noteFlowchatSystem


A Spring Boot project that provides a demo of a Note Flow Chat System. The project integrates several functionalities, including user authentication, database management, and interaction with AWS Rekognition for image recognition.

Prerequisites
Before you start, ensure you have the following installed on your system:

Java 17 or later
Maven 3.x or later
PostgreSQL (or another compatible database if modified)
Docker (optional, if you want to run the project in a containerized environment)
Setting Up the Project
Follow these steps to set up and run the project:

1. Clone the Repository
Clone the repository to your local machine:

bash
Copy code
git clone https://github.com/your-username/noteFlowchatSystem.git
cd noteFlowchatSystem
2. Configure the Database
Make sure you have PostgreSQL running locally or configure the application-dev.properties file to connect to an existing database.

Default PostgreSQL Configuration (application-dev.properties):
properties
Copy code
spring.datasource.url=jdbc:postgresql://localhost:5432/flowchat1
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.hbm2ddl.auto=update
3. Build the Application
Use Maven to build the project:

bash
Copy code
mvn clean install
This command will download the required dependencies and package the application into a JAR file.

4. Run the Application Locally
 -> go to spring boot app.
