# Internet Services Architectures - Laboratories | Gdańsk University of Technology 2024/2025

## Overview

This repository contains the laboratory project for the "Internet Services Architectures" course, completed during the fifth semester. The project is a comprehensive application designed for developers to manage warehouse materials. It consists of three backend applications and one frontend application:

1. **Gateway-Application**: Acts as a gateway to connect the other backend services.
2. **Material-Management-Application**: Manages materials in the warehouses.
3. **Warehouse-Management-Application**: Manages the warehouses themselves.
4. **Web-Application**: A frontend application built with Angular for user interaction.

## Technologies Used

### Backend

- **Spring Boot**: A framework for building production-ready applications in Java.
- **Spring Cloud Gateway**: Used in the Gateway-Application for routing and filtering requests.
- **Spring Data JPA**: For database interactions in the Material-Management-Application and Warehouse-Management-Application.
- **H2 Database**: An in-memory database used for development and testing.
- **Lombok**: A Java library that helps to reduce boilerplate code.

### Frontend

- **Angular**: A platform for building mobile and desktop web applications.
- **TypeScript**: A superset of JavaScript that adds static types.

## Project Structure

### Gateway-Application

- **Framework**: Spring Boot, Spring Cloud Gateway
- **Port**: 8085
- **Description**: Routes requests to the Material-Management-Application and Warehouse-Management-Application.

### Material-Management-Application

- **Framework**: Spring Boot, Spring Data JPA
- **Port**: 8082
- **Description**: Manages materials, including CRUD operations and interactions with warehouses.

### Warehouse-Management-Application

- **Framework**: Spring Boot, Spring Data JPA
- **Port**: 8081
- **Description**: Manages warehouses, including CRUD operations and interactions with materials.

### Web-Application

- **Framework**: Angular
- **Port**: 4200
- **Description**: Provides a user interface for managing warehouses and materials.

## Getting Started

### Prerequisites

- **Java 17**
- **Node.js and npm**
- **Maven**

### Accessing the Application

- **Frontend**: Open your browser and navigate to [http://localhost:4200/].
- **Gateway**: The gateway routes can be accessed via [http://localhost:8085/].

## API Endpoints

### Gateway-Application

- **Warehouse Management**: [http://localhost:8085/warehouses]
- **Material Management**: [http://localhost:8085/materials]

### Material-Management-Application

- **Create Material**: `POST /materials/{warehouseId}`
- **Get Material by ID**: `GET /materials/{id}`
- **Get All Materials**: `GET /materials`
- **Update Material**: `PUT /materials/{id}`
- **Delete Material**: `DELETE /materials/{id}`
- **Get Materials by Warehouse ID**: `GET /materials/warehouse/{warehouseId}`

### Warehouse-Management-Application

- **Create Warehouse**: `POST /warehouses`
- **Get Warehouse by ID**: `GET /warehouses/{id}`
- **Get All Warehouses**: `GET /warehouses`
- **Update Warehouse**: `PUT /warehouses/{id}`
- **Delete Warehouse**: `DELETE /warehouses/{id}`
