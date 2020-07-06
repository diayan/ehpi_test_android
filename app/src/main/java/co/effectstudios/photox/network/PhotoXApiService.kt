package co.effectstudios.photox.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://picsum.photos/v2/"

//moshi builder to for retrofit to convert json
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//retrofit builder to build a retrofit object using moshi converter
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface PhotoXApiService {

    @GET("list")
    fun getPhotos(): Deferred<List<PhotoProperty>>
}


//exposes retrofit service to be used publicly
object PhotoApi {

    val retrofitService: PhotoXApiService by lazy {
        retrofit.create(PhotoXApiService::class.java)
    }
}