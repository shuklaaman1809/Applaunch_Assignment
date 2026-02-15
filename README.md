# Applaunch Assignment – User Management App

A simple user management Android application built using modern Android development practices.
The app demonstrates an offline-first architecture with Room as the single source of truth.

---

## Tech Stack

- Kotlin
- Jetpack Compose (UI)
- MVVM Architecture
- Room Database
- Retrofit
- Coroutines + Flow / StateFlow
- Hilt Dependency Injection

---

## Features

- Fetch users from remote API
- Store users locally using Room
- Display users **only from the local database**
- Edit user details (local update)
- Delete user from database
- Automatic UI updates using Flow
- Background data synchronization
- Loading & error handling states

---

## Architecture

The app follows **Single Source of Truth** principle.


### Key Points

- UI never consumes API data directly
- API calls update the Room database in the background
- UI observes Room using Flow / StateFlow
- Changes in database are immediately reflected in UI
- App continues to work even if network fails (offline-first behavior)

---

## Module Responsibilities

### Repository
- Handles API calls
- Persists data into Room
- Exposes database flows to ViewModels

### ViewModels
- Maintain UI state using `StateFlow`
- Trigger background sync
- Expose database-backed state to UI

### UI (Compose)
- Observes `StateFlow`
- Reacts to database changes
- Stateless where possible

---

## Navigation

- Jetpack Navigation Compose
- Only IDs are passed between screens
- Detail screen loads user data from Room using ID
- Prevents crashes during recomposition and process recreation

---

## Error Handling Strategy

- Network failures do not break UI
- Previously stored data remains visible
- API errors are isolated to the repository layer
- UI reflects loading or empty states gracefully

---

## Setup

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle
4. Run the app

---

## API Used

https://jsonplaceholder.typicode.com/users

---

## APK Download

You can download and install the latest APK from GitHub Releases:

👉 [Download APK](https://github.com/shuklaaman1809/Applaunch_Assignment/releases/latest)
