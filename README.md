# Stephen King Books App

## Description
The **Stephen King Books App** is an Android application designed to fetch and display books authored by Stephen King. Utilizing the **Stephen King API**, the app will show book details in UI built with Jetpack Compose. The architecture follows the MVVM  pattern, ensuring clear separation of concerns and ease of testing.

Key features include:
- List and detail views of Stephen King’s books.
- Clean and modern design with Jetpack Compose.
- Unit and UI testing for reliable performance.

---

## :construction: Roadmap :construction: 
Note: (Project still in progress)
### Project Initialization
- [x] Set up a new Android project in Android Studio.
- [x] Configure project dependencies for Jetpack Compose, Retrofit, Hilt, and other necessary libraries.
- [x] Add the Stephen King API base URL to the project’s build configuration.

### Core Functionality
#### Data Layer
- [x] Define API endpoints using Retrofit.
- [x] Create data models representing books and API responses.
- [x] Implement a repository for data fetching and caching.

#### Domain Layer
- [x] Create repository for retrieving books and handling business logic.
- [x] Create use cases for retrieving books and handling business logic.

#### Presentation Layer
- [x] Set up the MVVM architecture with ViewModel for state management.
- [ ] Design screens with Jetpack Compose:
  - [x] Book list screen.
  - [ ] Book details screen.
- [ ] Integrate StateFlow for reactive UI updates.

### Additional Features
- [ ] Add a search functionality to filter books by title or genre.
- [x] Implement error handling for network issues and API failures.

### Testing
- [x] Write unit tests for use cases and ViewModels.
- [ ] Create UI tests using Jetpack Compose Testing.

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
