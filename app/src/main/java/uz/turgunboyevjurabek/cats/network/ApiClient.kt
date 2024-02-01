package uz.turgunboyevjurabek.cats.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import uz.turgunboyevjurabek.cats.utils.ConsItem
import java.util.concurrent.TimeUnit

object ApiClient {


    private fun getClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.header("Authorization", "Token 287fbe4ea8ba04f6718e0d72ecde7ef96f669bdd")
            chain.proceed(builder.build())
        })
        .build()

    private val client= getClient()
    val api:ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://crm.ziyodev.uz/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }


}