package com.example.dependencyinjection

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent
{
    // A Component is like a bridge  between the modules(which provide the dependencies) & classes that need those dependencies.

    fun getCar(): Car

}