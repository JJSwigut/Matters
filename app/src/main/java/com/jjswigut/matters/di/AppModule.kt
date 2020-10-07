package com.jjswigut.matters.di

import android.content.Context
import com.jjswigut.matters.database.MatterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) =
        MatterDatabase.getInstance(appContext)

    @Provides
    @Singleton
    fun provideDao(db: MatterDatabase) = db.matterDataBaseDao


}