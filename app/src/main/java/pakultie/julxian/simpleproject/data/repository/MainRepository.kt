package pakultie.julxian.simpleproject.data.repository

import pakultie.julxian.simpleproject.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getParameters()
}