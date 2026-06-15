# AnoUlam Backend API

## About

AnoUlam Backend is the REST API that powers the AnoUlam mobile application.

It handles recipe recommendations, dish retrieval, ingredient matching, and communication with the MySQL database.

The backend was built using Spring Boot and follows a layered architecture consisting of Controllers, Services, DTOs, Repositories, Config, and Models.

---

## Architecture

```text
Client (React Native)
          ↓
        Config
          ↓
     Controllers
          ↓
       Services
          ↓
    Repositories
          ↓
   Aiven MySQL Database
```

---

## Tech Stack

### Backend

- Java
- Spring Boot
- Spring Data JPA
- Maven

### Database

- MySQL
- Aiven

### API

- REST Architecture

---

## Project Structure

```text
src/main/java
├── config
├── controller
├── dto
├── model
├── repository
└── service
```

---

## Features

### Recipe Recommendation

Recommend dishes based on available ingredients.

### Dish Details

Retrieve complete dish information by ID.

### Ingredient Matching

Flexible ingredient matching supporting Filipino and English ingredient names.

---

## API Endpoints

### Recommendation

```http
POST /api/v1/dishes/recommendations
```

Request:

```json
{
  "ingredients": [
    "bawang",
    "sibuyas",
    "kamatis"
  ]
}
```

Response:

```json
[
  {
    "id": 1,
    "name": "Adobong Manok",
    "matchPercentage": 80.0
  }
]
```

---

### Dish Details

```http
GET /api/v1/dishes/{id}
```

---

## Installation

### Clone Repository

```bash
git clone https://github.com/jude342/anoulam-backend.git
```

### Configure Environment Variables

Create an `.env` file or configure application properties:

```env
DB_URL=YOUR_DATABASE_URL
DB_USERNAME=YOUR_DATABASE_USERNAME
DB_PASSWORD=YOUR_DATABASE_PASSWORD
```

---

### Run Application

```bash
mvn spring-boot:run
```

---

## Database Design

Main Tables:

### dishes

Stores dish information.

### ingredients

Stores ingredient information.

### dish_ingredients

Many-to-many relationship table connecting dishes and ingredients.

---

## Lessons Learned

This project helped me learn:

- Spring Boot architecture
- DTO pattern
- Service layer design
- Repository pattern
- REST API development
- Many-to-many database relationships
- Query optimization
- Deployment and cloud databases
- Database Indexing (But not implemented)

---

## Related Repository

Frontend Repository:

https://github.com/jude342/anoulam-frontend

---

## Developer

Developed by Jude

Created as both a learning project and a practical solution to help Filipino families decide what to cook based on the ingredients available at home.