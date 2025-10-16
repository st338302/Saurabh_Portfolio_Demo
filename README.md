# 💼 Saurabh_Portfolio_Demo – Modern Android Portfolio App

A fully functional sample app that showcases **Clean Architecture**, **MVVM**, and **Jetpack Compose** using the latest Android development tools and libraries.

---

## 🚀 Overview

**Holdings Demo** displays a portfolio of financial holdings with real-time summary calculations and expandable holding details — built entirely with **Jetpack Compose**.

This project is designed as a **reference architecture** for scalable Android development using modern practices:
- Separation of concerns (data / domain / presentation layers)
- Reactive UI state with `StateFlow`
- Dependency injection via Hilt
- Offline-ready data architecture (Retrofit + repository abstraction)
- Unit-tested business logic and ViewModels

---

## 🧰 Tech Stack

| Layer | Technologies / Libraries |
|-------|---------------------------|
| **Language** | [Kotlin](https://kotlinlang.org/) (latest stable, Kotlin 2.2.20gra) |
| **UI** | [Jetpack Compose](https://developer.android.com/jetpack/compose), [Material3](https://m3.material.io/), animations |
| **Architecture** | MVVM + Clean Architecture |
| **DI** | [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) |
| **Networking** | [Retrofit2](https://square.github.io/retrofit/), [Gson](https://github.com/google/gson) |
| **Coroutines** | Kotlin Coroutines + Flow + `kotlinx-coroutines-test` |
| **Testing** | JUnit4, MockK, Coroutines Test, Compose UI Tests |
| **Build** | Gradle (KTS) + Android Gradle Plugin 8.13-bin |
| **Minimum SDK** | 24 |
| **Compose BOM** | 2025.10.00 |
| **Design System** | Material3 + Custom Theme |

---

## 🧩 Project Architecture
app/
├── core/ # Common utilities (Result, extensions)
├── di/ # Hilt dependency injection modules
├── feature/
│ └── holdings/
│ ├── data/ # Retrofit API, repository implementations
│ ├── domain/ # Entities & use cases
│ └── presentation/
│ ├── ui/ # Jetpack Compose screens
│ └── viewmodel/
└── ui/theme/ # Material3 theming

This structure promotes:
- Testability
- Scalability (feature modularization-ready)
- Clear separation of layers

---

## 🧪 Testing

- ✅ **Unit Tests** for repositories, use cases, and ViewModels  
- ✅ **Coroutines Test** for deterministic async testing  
- ✅ **Compose UI Tests** for verifying UI states (expand/collapse, loading, error)  

🧭 Key Features
- Fetch holdings data from mock REST API
- Calculate current value, total investment, total P&L, and today’s P&L
- Expand/collapse detailed rows with smooth animations
- Error & loading state handling
- Modular, testable MVVM with full DI support

🎯 Goals
- This project demonstrates:
- Production-grade Android architecture for modern Kotlin apps
- Jetpack Compose best practices
- End-to-end test coverage
- Clean code readability for portfolio or interview demonstrations

🧑‍💻 Author
Saurabh
[LinkedIn Profile](https://www.linkedin.com/in/saurabh-sa)
