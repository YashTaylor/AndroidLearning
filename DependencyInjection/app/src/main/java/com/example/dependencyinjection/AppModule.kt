package com.example.dependencyinjection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule
{
    @Provides // tells hilt how to provide an object
    @Singleton // Ensures that hilt provides an single instance of dependency across entire app
    fun provideEngine(): Engine
    {
        return Engine()
    }

    // For constructor injection
    /*@Provides
    @Singleton
    fun provideCar(engine: Engine): Car
    {
        return Car(engine)
    }*/

}