# Square Android Test

This is test application built by Andrew Glose to showcase an employee directory

## Focus Areas
Clean Architecture
* The app is layered with View -> Domain -> Data
Modularity
* I love a highly modular app and I wanted to demonstrate best practices with this structure
Model View ViewModel (MVVM)
* This is general best practice when using JetPack libraries. LiveData and Jetpack's ViewModel
make this experience a breeze
Kotlin - Coroutines
* Kotlin is my favorite language and have been using it almost 2 years now. This is also a showcase
of my Kotlin knowledge
Unit testing
* Testing with some of Android's newest libraries

## Basic App design structure
The View layer (ie. Fragment/Activity) observes the ViewModel using LiveData
The ViewModel layer launches coroutines and invokes the UseCase or Domain layer
The Domain layer is basic in this project but normally this is where you would alter or transform the response
The Data layer uses a standard Repository pattern which only uses a remote data source and interacts
with services via Retrofit

## Copied Code - Dependencies
1. MainNavigationFragment is code pulled in from Google sample repos
2. Response is code that is created for personal projects
3. Adapter pattern is a pattern I have used for personal projects and my current job
4. Koin is very easy DI
5. Retrofit for networking
6. Lots of Jetpack libs :)

## Focus
Phone

## Hours Spend
6 (wanted to spend more because this was really fun)
