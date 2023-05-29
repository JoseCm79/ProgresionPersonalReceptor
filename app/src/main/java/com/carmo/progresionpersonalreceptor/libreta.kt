package com.carmo.progresionpersonalreceptor

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import android.widget.TextView

class libreta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libreta)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        val Siempre = getSharedPreferences("Siempre", Context.MODE_PRIVATE)

        findViewById<TextView>(R.id.libreta).setPaintFlags(findViewById<TextView>(R.id.libreta).getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        findViewById<TextView>(R.id.libreta).setText(Siempre.getString(Siempre.getString("LibretaAct",""),""))

    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val Siempre = getSharedPreferences("Siempre", Context.MODE_PRIVATE)
        if(keyCode == KeyEvent.KEYCODE_BACK){

            if(Siempre.getString("LibretaAct","") == "personales" || Siempre.getString("LibretaAct","") == "equipo" || Siempre.getString("LibretaAct","") == "wak"){
                startActivity(Intent(this, MetasWak::class.java))
            }
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }
}