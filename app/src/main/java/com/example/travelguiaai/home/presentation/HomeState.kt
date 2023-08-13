package com.example.travelguiaai.home.presentation

import com.example.travelguiaai.home.domain.model.HomeFilterSettings
import com.example.travelguiaai.home.domain.model.Place
import com.example.travelguiaai.home.domain.model.Region


data class HomeState(
    val searchText: String = "",
    val showDialog: Boolean = false,
    val filterSettings: HomeFilterSettings = HomeFilterSettings(),
    val filterSettingsBackup: HomeFilterSettings = filterSettings,
    val chatReply: String? = null,
    val selectedRegion: Region = Region.TODAS,
    val popularPlaces: List<Place> = emptyList(),
    val popularPlacesBackup: List<Place> = popularPlaces,
    val isLoading: Boolean = false
)
