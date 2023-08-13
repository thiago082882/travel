package com.example.travelguiaai.home.data

import com.example.travelguiaai.home.data.remote.dto.ChatGptApi
import com.example.travelguiaai.home.data.remote.dto.ChatRequestDto
import com.example.travelguiaai.home.domain.HomeRepository
import com.example.travelguiaai.home.domain.model.HomeFilterSettings
import com.example.travelguiaai.home.domain.model.Place
import com.example.travelguiaai.home.domain.model.Region


class HomeRepositoryImpl(
    private val api: ChatGptApi
) : HomeRepository {
    override suspend fun getTravelGuide(
        location: String,
        settings: HomeFilterSettings
    ): Result<String> {
        return try {
            var places = ""
            if (settings.restaurant) places += "Restaurantes,"
            if (settings.museums) places += "Museus,"

            val placesToVisit = if (places.isNotEmpty()) "Eu quero visitar : $places" else ""

            val request = ChatRequestDto(
                maxTokens = 1500,
                model = "text-davinci-003",
                "\n" +
                        "Sou  um guia de viagem. Vou lhe dar minha localização e você vai sugerir lugares para visitar nas proximidades. Também vou dar a vocês os tipos de lugares que quero visitar. Além disso, quero que você sugira lugares de tipo semelhante aos que mencionei e que sejam próximos ao meu primeiro local. Estou em $location $placesToVisit. Dê-me os preços de cada lugar em USD. Ordene-os por tipo de lugar.",
                temperature = 0.7
            )
            val information = api.getTravelInformation(request)
            Result.success(information.choices.first().text)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPopularPlaces(): Result<List<Place>> {
        return Result.success(
            listOf(
                Place(
                    "USA",
                    "New York",
                    Region.AMERICA,
                    "https://images.pexels.com/photos/2224861/pexels-photo-2224861.png?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                ),
                Place(
                    "Argentina",
                    "Salta",
                    Region.AMERICA,
                    "https://images.pexels.com/photos/13430634/pexels-photo-13430634.jpeg?auto=compress"
                ),
                Place(
                    "Espanha",
                    "Barcelona",
                    Region.EUROPA,
                    "https://www.fodors.com/wp-content/uploads/2022/03/Hero-UPDATEBarcelona-iStock-1320014700-1.jpg"
                ),
                Place(
                    "Australia",
                    "Sydney",
                    Region.OCEANIA,
                    "https://images.squarespace-cdn.com/content/v1/55ee34aae4b0bf70212ada4c/1577545161018-1F9Z9ZZQG9JO2O4WCWQX/keith-zhu-qaNcz43MeY8-unsplash+%281%29.jpg"
                ),
                Place(
                    "Japão",
                    "Tokio",
                    Region.ASIA,
                    "https://lonelyplanetes.cdnstatics2.com/sites/default/files/styles/max_1300x1300/public/fotos/japon_tokio_shibuya_shutterstock_666197236_f11photo_shutterstock.jpg"
                ),
                Place(
                    "Italia",
                    "Roma",
                    Region.EUROPA,
                    "https://images.pexels.com/photos/2225442/pexels-photo-2225442.jpeg?auto=compress&cs=tinysrgb&w=1600"
                ),
                Place(
                    "Brazil",
                    "Rio de Janeiro",
                    Region.AMERICA,
                    "https://images.pexels.com/photos/4737518/pexels-photo-4737518.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                )
            )
        )
    }
}