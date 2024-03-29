package com.example.cuseconnect

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://app.ticketmaster.com/discovery/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface TicketmasterApiService {
    @GET("events.json")
    suspend fun getEvents(
        @Query("apikey") apiKey: String,
        @Query("city") city: String
    ): EventResponse
}
object TicketmasterApi {
    val retrofitService: TicketmasterApiService by lazy { retrofit.create(TicketmasterApiService::class.java) }
}
