package com.niketchoudhary.`in`.androidcodingchallenge.utility

import com.google.gson.annotations.SerializedName

data class CommonApiResponse<T>(

    @field:SerializedName("results")
    val results: T?,

    @field:SerializedName("info")
    var info: Info? = null

) {

    data class Info(
        @field:SerializedName("results")
        val results: Int?
    )

}