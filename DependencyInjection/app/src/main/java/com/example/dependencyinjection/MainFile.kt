package com.example.dependencyinjection

fun main()
{
    // Hilt/Dagger generates the DaggerAppComponent class at compile time
    val appComponent: AppComponent = DaggerAppComponent.create()

    // Retrieve the car instance
    val car = appComponent.getCar()
    println(car.start())
}