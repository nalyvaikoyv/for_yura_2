package io.github.hkusu.rxRetrofit
import com.google.gson.annotations.SerializedName


data class JsonZones_Base (

        @SerializedName("id") val id : Int,
        @SerializedName("index") val index : String,
        @SerializedName("name") val name : String,
        @SerializedName("type") val type : String
)