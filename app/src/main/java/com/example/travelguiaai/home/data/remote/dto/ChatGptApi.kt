package com.example.travelguiaai.home.data.remote.dto


import retrofit2.http.Body
import retrofit2.http.POST

interface ChatGptApi {

        companion object {
            const val BASE_URL = "https://api.openai.com/v1/"
        }

        @POST("completions")
        suspend fun getTravelInformation(@Body body: ChatRequestDto): ChatReponseDto
    }

