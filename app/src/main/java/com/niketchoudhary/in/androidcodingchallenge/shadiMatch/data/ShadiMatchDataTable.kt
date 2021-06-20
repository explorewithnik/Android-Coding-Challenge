package com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ShadiMatchDataTable")
data class ShadiMatchDataTable(
    @PrimaryKey
    @SerializedName("cell")
    var cell: String = "-1",

    @SerializedName("gender")
    var gender: String? = null,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("phone")
    var phone: String? = null,

    @field:SerializedName("name")
    var name: Name? = null,

    @field:SerializedName("location")
    var location: Location? = null,

    @field:SerializedName("dob")
    var mDob: CustomAge? = null,

    @field:SerializedName("picture")
    var picture: Picture? = null,

    var selectionStatus: String? = null, // accept, decline

    var indexInResponse: Int = 0,

    var upload: Int = -1


) {

    inner class Name(
        @field:SerializedName("title")
        var title: String? = null,

        @field:SerializedName("first")
        var first: String? = null,

        @field:SerializedName("last")
        var last: String? = null
    ) {

        fun getFullName(): String {
            return "$title. $first ${last?.get(0)}"
        }
    }

    inner class Location(
        @field:SerializedName("city")
        var city: String? = null,

        @field:SerializedName("state")
        var state: String? = null,

        @field:SerializedName("country")
        var country: String? = null
    ) {

        fun getFullLocation(): String {
            return "$city, $state, $country"
        }
    }

    inner class CustomAge(
        @field:SerializedName("age")
        var mAge: String? = null
    )

    inner class Picture(
        @field:SerializedName("large")
        var large: String? = null
    )
}