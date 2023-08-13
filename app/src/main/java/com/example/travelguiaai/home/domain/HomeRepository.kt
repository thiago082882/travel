package com.example.travelguiaai.home.domain

import com.example.travelguiaai.home.domain.model.HomeFilterSettings
import com.example.travelguiaai.home.domain.model.Place

interface HomeRepository {

    suspend fun getTravelGuide(location : String,settings: HomeFilterSettings) : Result<String>
    suspend fun getPopularPlaces(): Result<List<Place>>


}