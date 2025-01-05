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
- [ ] Set up a new Android project in Android Studio.
- [ ] Configure project dependencies for Jetpack Compose, Retrofit, Hilt, and other necessary libraries.
- [ ] Add the Stephen King API base URL to the project’s build configuration.

### Core Functionality
#### Data Layer
- [ ] Define API endpoints using Retrofit.
- [ ] Create data models representing books and API responses.
- [ ] Implement a repository for data fetching and caching.

#### Domain Layer
- [ ] Create use cases for retrieving books and handling business logic.

#### Presentation Layer
- [ ] Set up the MVVM architecture with ViewModel for state management.
- [ ] Design screens with Jetpack Compose:
  - [ ] Book list screen.
  - [ ] Book details screen.
- [ ] Integrate LiveData or StateFlow for reactive UI updates.

### Additional Features
- [ ] Implement pagination for the book list.
- [ ] Add a search functionality to filter books by title or genre.
- [ ] Implement error handling for network issues and API failures.

### Testing
- [ ] Write unit tests for use cases and ViewModels.
- [ ] Create UI tests using Jetpack Compose Testing.
- [ ] Add mock responses for API testing.

---

## Technologies and Tools
- **Programming Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM
- **Network Library**: Retrofit
- **Dependency Injection**: Hilt
- **Testing Frameworks**: JUnit, Mockito, Jetpack Compose Testing
- **State Management**: Flows 
