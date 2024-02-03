package uz.turgunboyevjurabek.cats.network

import retrofit2.http.GET
import uz.turgunboyevjurabek.cats.madels.GetAllProductItem

interface ApiService {
    @GET("products/")
    suspend fun getListImages():ArrayList<GetAllProductItem>
}