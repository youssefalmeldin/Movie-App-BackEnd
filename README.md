# Movie Management Application

## Overview
This project is a **Movie Management Application** built using **Spring Boot** and **MongoDB**. It provides different dashboards for **admin** and **regular users** with movie management and search functionalities, integrating with the **OMDB API**.

## Features

### Admin Dashboard:
- **Login functionality** for admin users.
- **Dashboard** displaying movies from the **OMDB API** based on search criteria.
- **Add or remove movies** from the application database.
- **Batch add or remove movies**.

### Regular User Dashboard:
- **Login functionality** for regular users.
- **View full list** of movies added by the admin (fetched from the database).
- **View detailed information** of each movie.
- **Search bar** to find specific movies.
- **Rate a movie** individually.
- **Pagination** applied for better user experience.

## Technologies Used
- **Java 8+**
- **Spring Boot**
- **MongoDB**
- **OMDB API**

## API Endpoints

### Authentication APIs
- **Register Admin:** `POST http://localhost:8080/auth/register/admin`
- **Register User:** `POST http://localhost:8080/auth/register/user`

### Admin APIs
- **Add Movie to Database:** `POST http://localhost:8080/admin/movie?imdbID=tt0111958`
- **List All Movies (Admin View):** `GET http://localhost:8080/admin/movie/all`
- **Delete Movie:** `DELETE http://localhost:8080/admin/movie/{movieId}`
- **Search Movies from OMDB:** `GET http://localhost:8080/admin/movie?searchText=father`

### User APIs
- **Get Movie Details:** `GET http://localhost:8080/users/movie/{movieId}`
- **List Movies Available:** `GET http://localhost:8080/users/movie`

## Setup & Installation

### Prerequisites
- **Java 8+** installed
- **MongoDB** running locally or remotely , i used Docker 
- OMDB API Key (sign up at [OMDB API](https://www.omdbapi.com/) for a free API key)

### Steps to Run Locally
1. **Clone the repository**
   ```sh
   git clone https://github.com/youssefalmeldin/Movie-App-BackEnd.git
   cd Movie-App-BackEnd
   ```
2. **Configure Environment Variables**
   - Add your OMDB API key in `application.properties`:
     ```properties
     omdb.api.key=YOUR_OMDB_API_KEY
     ```
   - Configure MongoDB connection details in `application.properties`:
     ```properties
     spring.data.mongodb.uri=mongodb://localhost:27017/movie_db
     ```
3. **Build & Run the Application**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

## Future Enhancements
- Implement **JWT-based authentication** for security.
- Add **role-based authorization**.
- Integrate a **frontend** with Angular for better UI.
- Improve **error handling** and validation.

## Repository Link
[Movie-App-BackEnd](https://github.com/youssefalmeldin/Movie-App-BackEnd)


---
**Author:** Youssef Alm El-Den

