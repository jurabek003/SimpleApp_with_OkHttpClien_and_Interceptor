package uz.turgunboyevjurabek.cats.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import uz.turgunboyevjurabek.cats.madels.GetAllProductItem
import uz.turgunboyevjurabek.cats.madels.GetImageResponse
import uz.turgunboyevjurabek.cats.utils.ConsItem

interface ApiService {
    @GET("products/")
    suspend fun getListImages():ArrayList<GetAllProductItem>
}