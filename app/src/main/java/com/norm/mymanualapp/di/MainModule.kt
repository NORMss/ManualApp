package com.norm.mymanualapp.di

import android.app.Application
import androidx.room.Room
import com.norm.mymanualapp.db.MainDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideMainDb(app: Application): MainDb {
        return Room.databaseBuilder(
            app,
            MainDb::class.java,
            "info_fix.db"
        ).createFromAsset("db/info_fix.db").build()
    }
}