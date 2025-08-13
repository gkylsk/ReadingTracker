# Reading Tracker
Reader Tracker contains RESTful CRUD operations in spring boot using PostgreSQL and Spring data JPA with user authentication. Allows user to track their current reads.

## Features
- Users can register or login
- Users can add, update or delete book
- Books are categorized based on genres
- Book statuses are to read, currently reading and read
- Users can search books based on genre, rating or status

## Technologies Used
- Spring Boot - Framework to build production-ready Java applications quickly with minimal configuration
- Spring data JPA - Simplifies database interaction using Java Persistence API and repository abstractions
- PostgreSQL - Open-source relational database for efficient and reliable data storage
- Spring Security - Provides authentication, authorization, and security features for Java applications
- JWT (JSON Web Token) - Secure way to transmit user authentication data between client and server
- JUnit - Testing framework for writing and running unit tests in Java
- HTML, CSS, JavaScript - For front-end

## API Endpoints
- /api/register - Register a new user
- /api/login - User login and generates JWT token
- /api/home/books - Displays the books added by the user
- /api/home/view/book - User can view a particular book
- /api/home/add - Add book
- /api/home/update - Update an existing book
- /api/home/search - Search books
- /api/home/genres/list - List the Genres 
- /api/home/genres/books - List the books based on genre
