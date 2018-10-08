package io.github.hkusu.rxRetrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Provider {
    private var qiitaApiService: ApiService? = null

    fun provideQiitaApiService(): ApiService? {
        if (qiitaApiService == null) {
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://warwindiuser.000webhostapp.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            qiitaApiService = retrofit.create(ApiService::class.java)
        }

        return qiitaApiService
    }
}
