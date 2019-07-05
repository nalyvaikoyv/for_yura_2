package io.github.hkusu.rxRetrofit


import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("login.php")
    fun loginUser(@Field("login") login: String, @Field("pass") password: String): Observable<Login>


    @GET("zones.php")
    fun makeRequest(@Header("token") token: String): Observable<List<JsonZones_Base>>

}
