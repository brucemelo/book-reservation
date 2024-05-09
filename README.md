# Book Reservation - Java 21 - Micronaut

## Requirements

- Java 21

## Tech stack

- Java 21
- Micronaut
- Jooq
- H2
- Liquibase

## Running the application

```shell script
./gradlew run
```

or open in Intellij and run

## ER Diagram

```mermaid
erDiagram
    USER-PROFILE {
        int id PK
        string username
        string email
        timestamp created_at
        timestamp last_updated_at
    }

    BOOK {
        int id PK
        string title
        string author
        string isbn
        int published_year
        string genre
        int status
    }

    RESERVATION {
        int id PK
        int book_id FK
        int user_id FK
        date reservation_date
        date due_date
        int status
    }

    REVIEW {
        int id PK
        int book_id FK
        int user_id FK
        int score
        text comment
        timestamp review_date
    }

    USER-PROFILE ||--o{ RESERVATION : "has"
    USER-PROFILE ||--o{ REVIEW : "writes"
    BOOK ||--o{ RESERVATION : "reserved"
    BOOK ||--o{ REVIEW : "reviewed"


```

## Test
> **_NOTE:_**  Look at file requests.http
> 
> ![img.png](img.png)

```
###
GET http://localhost:8080/api/books/search

###
GET http://localhost:8080/api/books/search?title=Hobbit

###
GET http://localhost:8080/api/users

###
GET http://localhost:8080/api/reviews

###
POST http://localhost:8080/api/reservations
Content-Type: application/json

{
  "bookId": 1,
  "userId": 1,
  "reservationDate":"2024-12-01"
}

###
POST http://localhost:8080/api/reviews
Content-Type: application/json

{
  "bookId": 1,
  "userId": 1,
  "score": 4,
  "comment":"Nice!"
}


```