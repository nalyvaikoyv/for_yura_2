package io.github.hkusu.rxRetrofit


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Login {

    @SerializedName("token")
    @Expose
    var token: String? = null

    @SerializedName("endToken")
    @Expose
    var endToken: String? = null


    @SerializedName("response")
    @Expose
    var response: String? = null

}