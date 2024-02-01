package uz.turgunboyevjurabek.cats

import uz.turgunboyevjurabek.cats.network.ApiService

class MyRepozitory(private val apiService: ApiService) {
    suspend fun GetAllItem()=apiService.getListImages()
}