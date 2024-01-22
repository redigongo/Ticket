# Ticket Management System

This is a Spring Boot project for a Ticket Management System.

## Prerequisites

Before running the project, make sure you have the following prerequisites installed:

- Java Development Kit (JDK)
- MySQL Database

## Setup

1. **Clone the repository:**

    ```bash
    git clone https://github.com/redigongo/Ticket.git
    cd ticket
    ```

2. **Database Setup:**

    - Create a MySQL schema named `ticket`:

        ```sql
        CREATE SCHEMA IF NOT EXISTS ticket;
        ```

3. **Update Database Configuration:**

   Open `src/main/resources/application.properties` and update the MySQL connection details:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/ticket
    spring.datasource.username=root
    spring.datasource.password=1234
    ```

4. **Run the Application:**

   The application will start on `http://localhost:8081`.

5. **Swagger API Documentation:**

   Swagger API documentation is available at `http://localhost:8081/TICKET/swagger`. You can use this interface to explore the API endpoints.



## Postman Collection

Explore the API endpoints and test various use cases using the provided Postman collection:

[Postman Collection - Ticket](https://www.postman.com/gold-star-692236/workspace/ticket/collection/20581054-336e3299-8585-48c4-a028-16ad332cd5ca?action=share&creator=20581054)

The Postman collection includes:

- All use cases supported by the Ticket Management System.
- Different types of requests (GET, POST, PUT, DELETE).
- Examples of request payloads and expected responses.

To use the Postman collection:

1. Click on the link above to open the collection in Postman.
2. Import the collection into your Postman workspace.
3. Explore the available requests and execute them against your running instance of the Ticket Management System.

Feel free to modify the request payloads or parameters to test different scenarios. Refer to the Swagger documentation for detailed information about each API endpoint.

## Additional Information

- If you encounter any issues or have suggestions for improvements, please open an [issue](https://github.com/yourusername/ticket-management/issues).
