package io.github.hkusu.rxRetrofit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var qiitaApiService: ApiService? = null
    private var dispLogin: Disposable? = null
    private var dispRequest: Disposable? = null


    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        qiitaApiService = Provider.provideQiitaApiService()
    }

    override fun onResume() {
        super.onResume()
        btnLogin.setOnClickListener {
            dispLogin = qiitaApiService!!.loginUser(etLogin.text.toString(), etPass.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ resp ->
                        tvLogin.text = Gson().toJson(resp)

                        resp.token?.let {
                            token = resp.token!!
                            btnGet.isEnabled = true
                        }

                        resp.response?.let {
                            btnGet.isEnabled = false
                            token = ""
                        }

                    }, { t ->
                        tvLogin.text = Gson().toJson(t)
                    }, {})
        }

        btnGet.setOnClickListener {
            dispRequest = qiitaApiService!!.makeRequest(token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ resp ->
                        tvMainResponse.text = Gson().toJson(resp)
                    }, { t ->
                        tvMainResponse.text = Gson().toJson(t)
                    }, {})
        }
    }

    override fun onPause() {
        super.onPause()
        dispLogin?.dispose()
        dispRequest?.dispose()
    }
}
