package com.norm.mymanualapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.norm.mymanualapp.utils.ListItem

@Database(
    entities = [ListItem::class],
    version = 1

)
abstract class MainDb : RoomDatabase() {
    abstract val dao: Dao
}