package com.example.dependencyinjection

import javax.inject.Inject

//Dependent of engine class
class Car @Inject constructor (val engine: Engine)
{
    // Car depends on engine, hilt handles the injecting part
    fun start()
    {
        println(engine.start())
    }
}