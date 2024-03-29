package io.github.hkusu.rxRetrofit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Menu
import android.view.MenuItem
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private var qiitaApiService: ApiService? = null
    private var dispLogin: Disposable? = null
    private var dispRequest: Disposable? = null
    private var adapter: RecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        qiitaApiService = Provider.provideQiitaApiService()

        adapter = RecyclerAdapter()

        val layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter

        adapter?.onZoneClickListener = object : OnZoneClickListener {
            override fun onZoneClick(zone: JsonZones_Base) {
                Toast.makeText(baseContext, " ${zone.name} ", Toast.LENGTH_LONG).show()
                zoneActivity(zone)

            }
        }
    }

    override fun onResume() {
        super.onResume()
        val sp = getSharedPreferences("tokens", Activity.MODE_PRIVATE)
        val myToken = sp.getString("token", null)
        if (myToken != null) {
            Log.e("TAG", myToken)

            dispRequest = qiitaApiService!!.makeRequest(myToken)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ resp ->

                        adapter?.setItems(resp)

                    }, { t ->
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }, {})

        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun zoneActivity(zone: JsonZones_Base){
            Log.e("TAG", zone.id.toString())
            Log.e("TAG", zone.index)
            Log.e("TAG", zone.name)
            Log.e("TAG", zone.type)
        val intent = Intent(this, ZoneActivity::class.java)
        intent.putExtra("id", zone.id)
        intent.putExtra("index", zone.index)
        intent.putExtra("name", zone.name)
        intent.putExtra("type", zone.type)
        startActivity(intent)

    }

    override fun onPause() {
        super.onPause()
        dispLogin?.dispose()
        dispRequest?.dispose()
    }
}
