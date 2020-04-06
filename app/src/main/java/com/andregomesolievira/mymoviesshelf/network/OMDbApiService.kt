package com.andregomesolievira.mymoviesshelf.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://omdbapi.com/"

/**
 * Build the Moshi object that Retrofit will be using
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getPropertiesAsync] method
 */
interface OMDbApiService {
    /**
     * Returns a Coroutine [Deferred] [List] of [OMDbApiMovie] which can be fetched with await() if
     * in a Coroutine scope.
     */
    @GET("/")
    fun getPropertiesAsync(
        @Query("s") search: String,
        @Query("apikey") apikey: String
    ): Deferred<List<OMDbApiMovie>>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object OMDbApi {
    val retrofitService: OMDbApiService by lazy { retrofit.create(OMDbApiService::class.java) }
}
