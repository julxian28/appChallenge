package pakultie.julxian.simpleproject.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getParameters() = apiService.getParams()
}