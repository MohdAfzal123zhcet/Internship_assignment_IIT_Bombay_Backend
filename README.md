#  Courses Backend (Spring Boot + PostgreSQL + Docker)

This is the backend service for the Course Management System. It provides REST APIs for managing courses and course instances.

---
# first i check on postman and in last it is pull using docker hub and which is explain below 
![Screenshot (127)](https://github.com/user-attachments/assets/c875cf3c-3e8a-490e-bdc0-0374cc7bbbaa)



# Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker

---

# Project Structure

- model/ – Entity classes (Course, CourseInstance)
- repository/ – Spring Data Repositories
- controller/ – REST Controllers for API endpoints
- config/ – Configuration files (like CORS settings)

---

# API Endpoints

# Course APIs

| Method | Endpoint                  | Description               |
|--------|---------------------------|---------------------------|
| GET    | /api/courses             | Get all courses           |
| GET    | /api/courses/{id}      | Get course by ID          |
| POST   | /api/courses          | Create new course         |
| DELETE | /api/courses/{id}     | Delete a course (if not prerequisite) |

# Course Instance APIs

| Method         | Path                                          | Functionality                           |
| -------------- | --------------------------------------------- | --------------------------------------- |
| POST           | `/api/instances`                              | Create a new course instance            |
| GET            | `/api/instances/{year}/{semester}`            | Get all instances for a year & semester |
| GET            | `/api/instances/{year}/{semester}/{courseId}` | Get a specific instance                 |
| DELETE         | `/api/instances/{year}/{semester}/{courseId}` | Delete a specific instance              |

---
# use postman first to check all my apis working correctly 

# http://localhost:8080 → Spring Boot

# Also check on final execution after integrated with front end all are working fine.


# Docker Instructions

### Build Image

bash
docker build -t mohdafzal123/courses-backend .

mohdafzal123  is my user name on docker account

# docker push – Uploads image to DockerHub

docker push mohdafzal123/image-name

# backend contains docker-compose.yaml file for pulling both frontend and backend and database

