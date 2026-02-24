package com.example.journalapp.model

import java.sql.Timestamp

data class Journal(
    val title: String,
    val thoughts: String,
    val imageUrl: Int,

    val userId: String,
    val timeAdded: Timestamp,
    val userName: String,
)
