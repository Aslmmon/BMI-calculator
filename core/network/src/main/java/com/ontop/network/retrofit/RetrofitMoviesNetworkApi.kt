package com.ontop.network.retrofit

import androidx.core.os.trace
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ontop.core.network.BuildConfig
import com.ontop.network.MoviesDataSource
import com.ontop.network.model.MoviesResponse
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton


const val versionMovieDB = "/3/"
const val BASE_URL = BuildConfig.BASE_URL

/**
 * Retrofit API declaration for MovieDb Network API
 */
private interface RetrofitNetworkApi {
    @GET(value = "${versionMovieDB}discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int,
    ): MoviesResponse
}


@Singleton
class RetrofitMoviesNetworkApi @Inject constructor(
    okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : MoviesDataSource {
    private val networkApi = trace("RetrofitNetwork") {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitNetworkApi::class.java)
    }

    override suspend fun getMovies(page: Int) = networkApi.getMovies(page = page)
}