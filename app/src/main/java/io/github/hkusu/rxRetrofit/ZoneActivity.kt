package io.github.hkusu.rxRetrofit

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_zone.*

class ZoneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zone)

        val zoneId = intent.getStringExtra("id")
        val zoneIndex = intent.getStringExtra("index")
        val zoneName = intent.getStringExtra("name")
        val zoneType = intent.getStringExtra("type")

        txtZoneIndex.setText(zoneIndex)
        txtZoneName.setText(zoneName)
        txtZoneType.setText(zoneType)

        btnBack.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }

    }
}
