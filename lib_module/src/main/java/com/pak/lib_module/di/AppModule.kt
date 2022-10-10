package com.pak.lib_module.di

import android.content.Context
import androidx.room.Room
import com.pak.lib_module.db.MediaDB
import com.pak.lib_module.language.LanguageHelper
import com.pak.lib_module.network.ApiService
import com.pak.lib_module.respository.MediaRepository
import com.pak.lib_module.respository.MediaRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun injectRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, MediaDB::class.java, "ArtBookDB"
    ).build()

    @Singleton
    @Provides
    fun injectArtDao(database: MediaDB) = database.mediaDao()

    @ViewModelScoped
    @Provides
    fun provideMediaRepository(apiService: ApiService): MediaRepository {
        return MediaRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideLanguageHelper() = LanguageHelper()
}