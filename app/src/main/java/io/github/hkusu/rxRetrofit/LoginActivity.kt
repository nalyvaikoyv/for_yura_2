package io.github.hkusu.rxRetrofit

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_login.*
import android.R.id.edit
import android.content.SharedPreferences
import android.app.Activity




class LoginActivity : AppCompatActivity() {

    private var qiitaApiService: ApiService? = null
    private var dispLogin: Disposable? = null

    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        qiitaApiService = Provider.provideQiitaApiService()
        setSupportActionBar(toolbar)
        }

    override fun onResume() {
        super.onResume()
        btnLogin.setOnClickListener {
            dispLogin = qiitaApiService!!.loginUser(etLogin.text.toString(), etPassword.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ resp ->


                        resp.token?.let {
                            token = resp.token!!
                            val sp = getSharedPreferences("tokens", Activity.MODE_PRIVATE)
                            val editor = sp.edit()
                            editor.putString("token", token)
                            editor.apply()

                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }

                        resp.response?.let {
                            token = ""
                        }

                    }, { t ->
                        tvDebug.text = Gson().toJson(t)
                    }, {})
        }

    }

    override fun onPause() {
        super.onPause()
        dispLogin?.dispose()
    }
}
