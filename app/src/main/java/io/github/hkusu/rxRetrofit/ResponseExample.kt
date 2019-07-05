package io.github.hkusu.rxRetrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseExample {

    @SerializedName("response")
    @Expose
    var response: String? = null
    @SerializedName("method")
    @Expose
    var method: String? = null
    @SerializedName("ip")
    @Expose
    var ip: String? = null

}