package com.jk.pixabay.retrofit

import com.jk.pixabay.model.PixaModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.security.Key

interface PixaApi {
    @GET("api/")
    fun searchImage(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int=5,
        @Query("q") keyWord: String,
        @Query("key") key: String = "32430227-a1b9aa4d617ba460e51a3b8b4"


    ): Call<PixaModel>
}