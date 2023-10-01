package com.uit.food_delivery

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.uit.food_delivery.core.utils.Constants
import com.uit.food_delivery.data.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.FOOD_TNT_DATASTORE)

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager =
        DataStoreManager(context)
}
