package pakultie.julxian.simpleproject.data.api

import pakultie.julxian.simpleproject.data.model.SampleItem
import retrofit2.http.GET

interface ApiService {

    @GET("v2/deliveries")
    suspend fun getParams(): List<SampleItem>

}