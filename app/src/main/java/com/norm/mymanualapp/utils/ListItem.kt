package com.norm.mymanualapp.utils

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "main")
data class ListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val imgName: String,
    val infoName: String,
    val category: String,
    val isFav: Boolean
)