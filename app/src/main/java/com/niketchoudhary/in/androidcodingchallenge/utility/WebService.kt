package com.niketchoudhary.`in`.androidcodingchallenge.utility

import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.data.ShadiMatchDataTable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface WebService {

    @GET
    fun fetchOnlineInspectionList(
        @Url url: String?,
        @Query("results") limit: Int?
    ): Call<CommonApiResponse<List<ShadiMatchDataTable>>>
}