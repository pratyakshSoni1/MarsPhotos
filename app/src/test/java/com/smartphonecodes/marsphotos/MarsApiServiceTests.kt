package com.smartphonecodes.marsphotos

import com.smartphonecodes.marsphotos.network.MarsApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MarsApiServiceTests : BaseTest() {

    private lateinit var service: MarsApiService

    @Before
    fun setup(){
        val url = mockWebServer.url("/")

        val service = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            ))
            .baseUrl(url)
            .build()
            .create(MarsApiService::class.java)

    }

    @Test
    fun api_service(){
        enqueue("mars_photos.json")
        runBlocking {
            val apiResponse = service.getPhotos()
            assertNotNull(apiResponse)
            assertTrue("Api list was empty",apiResponse.isNotEmpty())
            assertEquals("The IDs did not match", "424905", apiResponse[0].id)
        }
    }

}