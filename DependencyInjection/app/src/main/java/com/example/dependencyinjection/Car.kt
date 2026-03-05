package com.example.dependencyinjection

import javax.inject.Inject

/*
//Dependent of engine class
class Car @Inject constructor (val engine: Engine)
{
    // Car depends on engine, hilt handles the injecting part
    fun start()
    {
        println(engine.start())
    }
}*/

//Dependent of engine class
class Car
{
    // Field Injection: 'engine' will be injected into this field
    @Inject
    lateinit var engine: Engine

    // Car depends on engine, hilt handles the injecting part
    fun start()
    {
        println(engine.start())
    }
}