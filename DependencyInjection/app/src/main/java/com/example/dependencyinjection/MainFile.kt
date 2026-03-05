package com.example.dependencyinjection

fun main()
{
    // Hilt/Dagger generates the DaggerAppComponent class at compile time
    val appComponent: AppComponent = DaggerAppComponent.create()

    // For constructor injection
    /*// Retrieve the car instance
    val car = appComponent.getCar()
    println(car.start())*/

    // For field injection
    val car = Car()
    appComponent.inject(car)
    car.start()
}