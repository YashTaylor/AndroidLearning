package com.example.dependencyinjection

/*

// WITHOUT DEPENDENCY INJECTION
class Car
{
    // Dependency is created inside the class
    //Dependency is passed from inside the class: TIGHTLY COUPLED
    private val engine = Engine()

    fun start()
    {
        println(engine.start())
    }
}

class Main()
{
    fun main()
    {
        println(Car().start())
    }
}*/


//WITH CONSTRUCTOR DEPENDENCY INJECTION
class Car(val engine: Engine)
{
    //Dependency is passed from outside the class: LOOSELY COUPLED
    fun start()
    {
        println(engine.start())
    }
}

class Main()
{
    fun main()
    {
        val engine = Engine()
        val car = Car(engine)
        println(car.start())
    }
}