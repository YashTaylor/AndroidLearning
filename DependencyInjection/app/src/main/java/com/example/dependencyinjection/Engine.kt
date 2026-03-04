package com.example.dependencyinjection

import javax.inject.Inject

// Dependency
// @Inject: tells Hilt how to create an instance of object (in this case engine)
class Engine @Inject constructor()
{
    fun start() = "Engine Started"
}