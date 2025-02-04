# Stephen King Books App

## Description
The **Stephen King Books App** is an Android application designed to fetch and display books authored by Stephen King. Utilizing the **Stephen King API**, the app will show book details in UI built with Jetpack Compose. The architecture follows the MVVM  pattern, ensuring clear separation of concerns and ease of testing.

Key features include:
- List and detail views of Stephen King’s books.
- Clean and modern design with Jetpack Compose.
- Unit and UI testing for reliable performance.

---
## Current Jacoco test coverage report:
![imagen](https://github.com/user-attachments/assets/4c8d0b52-0a3e-4ca6-97a1-31396e01c53d)

---
## :construction: Roadmap :construction: 
Note: (Project still in progress)
#### Data Layer
- [x] Define API endpoints.
- [x] Create data models for the API responses.
- [x] Implement a repository for data fetching and caching.

#### Domain Layer
- [x] Create repository for retrieving books and handling business logic.
- [x] Create use cases for retrieving books and handling business logic.

#### Presentation Layer
- [x] Set up the MVVM architecture with ViewModel for state management.
- [x] Design screens with Jetpack Compose:
  - [x] Book list screen.
  - [x] Book details screen.
- [x] Integrate StateFlow for reactive UI updates.

### Testing
- [x] Write unit tests for use cases and ViewModels.
- [x] Create UI tests using Jetpack Compose Testing.

### Additional Features
- [x] Implement error handling for network issues and API failures.
- [x] Change UI to a responsive design.
- [x] Add jacoco for Unit testing reports
- [x] Raise test coverage at least to 75%
- [ ] Keep improving the project 

---
## Technologies and Tools
- **Programming Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM
- **Network Library**: Retrofit
- **Dependency Injection**: Hilt
- **Testing Frameworks**: JUnit, Mockito, Jetpack Compose Testing
- **State Management**: Flows 

---
## Instructions

To run this project add to you local properties the following line:
`` BASE_URL ="https://stephen-king-api.onrender.com/api/" ``
