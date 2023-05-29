package com.carmo.progresionpersonalreceptor

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class EtapasWak : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etapas_wak)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        val Siempre = getSharedPreferences("Siempre", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = Siempre.edit()
        val etapasText = mutableListOf(findViewById<TextView>(R.id.DuraText), findViewById<TextView>(R.id.DaririrText), findViewById<TextView>(R.id.TsuríText))
        findViewById<ImageButton>(R.id.imageButton4).setOnClickListener(){
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val inflanter: LayoutInflater = getLayoutInflater()
            val view: View = inflanter.inflate(R.layout.dialog_duradura, null)
            builder.setView(view)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        findViewById<ImageView>(R.id.imageView8).setOnClickListener(){
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val inflanter: LayoutInflater = getLayoutInflater()
            val view: View = inflanter.inflate(R.layout.dialog_dariri, null)
            builder.setView(view)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        findViewById<ImageView>(R.id.imageView10).setOnClickListener(){
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val inflanter: LayoutInflater = getLayoutInflater()
            val view: View = inflanter.inflate(R.layout.dialog_tsuri, null)
            builder.setView(view)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        for (i in 1..etapasText.size){
            if(Siempre.getString("Etapa"+i,"0") == "0"){
                etapasText.get(i-1).setText("Evaluación: Inicial")
            }else{
                if(Siempre.getString("Etapa"+i,"0") == "1"){
                    etapasText.get(i-1).setText("Evaluación: Intermedio")
                }else{
                    etapasText.get(i-1).setText("Evaluación: Avanzado")
                }
            }


        }
        findViewById<ImageButton>(R.id.imageButton5).setOnClickListener(){
            editor.putString("LibretaAct","Etapal1").commit()
            startActivity(Intent(this, libreta::class.java))
        }
        findViewById<ImageButton>(R.id.imageButton6).setOnClickListener(){
            editor.putString("LibretaAct","Etapal2").commit()
            startActivity(Intent(this, libreta::class.java))
        }
        findViewById<ImageButton>(R.id.imageButton7).setOnClickListener(){
            editor.putString("LibretaAct","Etapal3").commit()
            startActivity(Intent(this, libreta::class.java))
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