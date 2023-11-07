package com.example.bookapi.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.bookapi.data.local.Shp
import com.example.bookapi.data.remote.service.AuthApi
import com.example.bookapi.data.remote.service.BookApi
import com.example.bookapi.data.remote.service.UserApi
import com.example.bookapi.utils.BASE_URL
import com.mocklets.pluto.Pluto
import com.mocklets.pluto.PlutoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDatabaseModule {

    @Provides
    @Singleton
    fun client(@ApplicationContext context: Context): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain
                .request()
                .newBuilder()
                .addHeader("authorization", "Bearer ${Shp(context).token}")
                .build()

            chain.proceed(request)
        }
        .addInterceptor(PlutoInterceptor())
        .addInterceptor(ChuckerInterceptor(context))
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, @ApplicationContext context: Context): Retrofit {
        Pluto.initialize(context)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideBookApi(retrofit: Retrofit): BookApi = retrofit.create(BookApi::class.java)

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

}