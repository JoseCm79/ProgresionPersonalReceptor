package com.carmo.progresionpersonalreceptor

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import android.widget.ImageButton

class MetasWak : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metas_wak)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        val Siempre = getSharedPreferences("Siempre", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = Siempre.edit()

        findViewById<EditText>(R.id.libretas).setText(Siempre.getString("equipoL",""))
        findViewById<EditText>(R.id.libretas2).setText(Siempre.getString("wakL",""))
        findViewById<EditText>(R.id.libretas3).setText(Siempre.getString("personalL",""))

        if(findViewById<EditText>(R.id.libretas).getText().toString() == null){
            findViewById<EditText>(R.id.libretas).setText("Vacío")
        }
        if(findViewById<EditText>(R.id.libretas2).getText().toString() == null){
            findViewById<EditText>(R.id.libretas2).setText("Vacío")
        }
        if(findViewById<EditText>(R.id.libretas3).getText().toString() == null){
            findViewById<EditText>(R.id.libretas3).setText("Vacío")
        }

        findViewById<ImageButton>(R.id.imageButton11).setOnClickListener(){
            editor.putString("LibretaAct","personalL").commit()
            startActivity(Intent(this, libreta::class.java))
            finish()
        }
        findViewById<ImageButton>(R.id.imageButton13).setOnClickListener(){
            editor.putString("LibretaAct","equipoL").commit()
            startActivity(Intent(this, libreta::class.java))
            finish()

        }
        findViewById<ImageButton>(R.id.imageButton12).setOnClickListener(){
            editor.putString("LibretaAct","wakL").commit()
            startActivity(Intent(this, libreta::class.java))
            finish()
        }

    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            startActivity(Intent(this, wakinicio::class.java))
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }

}