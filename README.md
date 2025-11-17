Product Browser App (Kotlin Multiplatform)

This is a Kotlin Multiplatform Mobile (KMM) application built for the Revest Solutions technical assignment.

The application is a product catalog browser built using Kotlin Compose Multiplatform, targeting both Android and iOS from a single, shared codebase. It features a rich, category-based UI, search, filtering, and a modern, scalable architecture.

📋 Summary of Business Requirements

The app serves as a prototype for a mobile product catalog, allowing users to:

View Products: Display a list of all products, grouped by category, showing the product name, price, and thumbnail.

View Product Details: Tap a product to view detailed information, including its title, full description, brand, price, and rating.

Search Products: Search for products by keyword using an API-based search.

Filter Products:  Filter the product list by selecting a specific category.

🏛️ Project Architecture Overview

The project follows Clean Architecture principles, strictly separating concerns into different layers within the composeApp/commonMain module.

Core Principles

Kotlin Multiplatform (KMP): All code (UI, business logic, data, and presentation) is written in Kotlin and shared 100% between Android and iOS.

Compose Multiplatform: The entire UI is built with Compose Multiplatform, providing a single, shared, and declarative UI layer for both platforms.

Layers

Data Layer (data package)

Repository Pattern: A ProductRepository interface abstracts the data source.

Ktor Client: Used for all networking to consume the dummyjson.com API.

kotlinx.serialization: Used for parsing all JSON responses into Kotlin data models.

Domain Layer (domain package)

Use Cases: Contains all business logic, encapsulated in simple UseCase classes (e.g., GetProductsUseCase, SearchProductsUseCase). This isolates what the app does from how it's done.

Presentation Layer (presentation package)

Decompose: Used for navigation and as a modern, lifecycle-aware alternative to ViewModels. RootComponent manages the navigation stack, while ProductListComponent manages the state for the main screen.

Koin: Used for Dependency Injection to manage and provide dependencies (like repositories and use cases) to the presentation layer, making the app modular and easy to test.

Key Libraries

Compose Multiplatform: Shared Declarative UI.

Ktor: Networking Client.

kotlinx.serialization: JSON Parsing.

Decompose: Navigation & State Management.

Koin: Dependency Injection.

Kamel: Asynchronous image loading for Compose Multiplatform.

Compose Shimmer: Provides a professional loading animation.

🛠️ How to Build and Run

Prerequisites

Android Studio (latest version, e.g., Koala or Iguana)

Kotlin Multiplatform Mobile Plugin (in Android Studio)

Xcode (for running the iOS app on a Mac)

Android

Open the project in Android Studio.

Let Gradle sync and download all dependencies.

Select the composeApp configuration from the "Run" dropdown.

Choose an Android emulator or a connected physical device.

Click the "Run" (▶) button.

iOS

Ensure you have Xcode installed and configured.

Open the project in Android Studio.

Select the composeApp configuration from the "Run" dropdown.

Choose an iOS Simulator (e.g., iosSimulatorArm64).

Click the "Run" (▶) button.

(Alternatively, you can open the iosApp/iosApp.xcworkspace file in Xcode and run the project from there after the initial Gradle sync in Android Studio.)

💡 Trade-offs and Assumptions

Dependency Injection: The prompt stated DI was optional (manual was acceptable). I chose to fully integrate Koin as it's a best practice for a scalable and testable KMP application.

State Management: Decompose was chosen over a manual solution as it's the recommended library for state management and navigation in Compose Multiplatform, providing lifecycle-aware components.

UI Design: The initial requirement was a simple list. I refactored this into a more modern, "rich" UI that groups products by category on the main screen. This provides a better user experience and demonstrates handling more complex data structures. Search and filter results are displayed in a dedicated flat list for clarity.

Image Loading: I used Kamel, a popular KMP-native library for loading images, to avoid platform-specific implementations.

Loading State: Instead of a simple CircularProgressIndicator, I implemented a Shimmer animation for a more polished, professional loading experience.

Error Handling: Basic error handling is implemented. A message is shown on the UI if a network call fails. A more robust solution would involve dedicated error components.



<div style="display: flex; justify-content: center;">
  <video src="screenshots/Screen_recording_20251117_170424.mp4" 
         style="width: 100%; max-width: 360px; aspect-ratio: 9/16;" 
         controls 
         playsinline>
  </video>
</div>
