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

class TrillosWak : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trillos_wak)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        val Siempre = getSharedPreferences("Siempre", Context.MODE_PRIVATE)
        val TrillosText = mutableListOf(findViewById<TextView>(R.id.BasuText), findViewById<TextView>(R.id.usureText), findViewById<TextView>(R.id.awakalText), findViewById<TextView>(R.id.senukText), findViewById<TextView>(R.id.DulekalomText))
        val editor : SharedPreferences.Editor = Siempre.edit()
        for (i in 1..TrillosText.size){
            if(Siempre.getString("Trillos"+i,"0") == "0"){
                TrillosText.get(i-1).setText("Evaluación: Inicial")
            }else{
                if(Siempre.getString("Trillos"+i,"0") == "1"){
                    TrillosText.get(i-1).setText("Evaluación: Intermedio")
                }else{
                    TrillosText.get(i-1).setText("Evaluación: Avanzado")
                }
            }


        }

        findViewById<ImageButton>(R.id.imageButton).setOnClickListener(){
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val inflanter: LayoutInflater = getLayoutInflater()
            val view: View = inflanter.inflate(R.layout.dialog_basu, null)
            builder.setView(view)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        findViewById<ImageView>(R.id.imageView6).setOnClickListener(){
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val inflanter: LayoutInflater = getLayoutInflater()
            val view: View = inflanter.inflate(R.layout.dialog_usure, null)
            builder.setView(view)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        findViewById<ImageView>(R.id.imageView4).setOnClickListener(){
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val inflanter: LayoutInflater = getLayoutInflater()
            val view: View = inflanter.inflate(R.layout.dialog_awakal, null)
            builder.setView(view)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        findViewById<ImageView>(R.id.imageView5).setOnClickListener(){
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val inflanter: LayoutInflater = getLayoutInflater()
            val view: View = inflanter.inflate(R.layout.dialog_senuk, null)
            builder.setView(view)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        findViewById<ImageView>(R.id.imageView7).setOnClickListener(){
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val inflanter: LayoutInflater = getLayoutInflater()
            val view: View = inflanter.inflate(R.layout.dialog_duleka, null)
            builder.setView(view)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        findViewById<ImageButton>(R.id.imageButton2).setOnClickListener(){
            editor.putString("LibretaAct","Trillosl1").commit()
            startActivity(Intent(this, libreta::class.java))
        }
        findViewById<ImageButton>(R.id.imageButton3).setOnClickListener(){
            editor.putString("LibretaAct","Trillosl2").commit()
            startActivity(Intent(this, libreta::class.java))
        }
        findViewById<ImageButton>(R.id.imageButton8).setOnClickListener(){
            editor.putString("LibretaAct","Trillosl3").commit()
            startActivity(Intent(this, libreta::class.java))
        }
        findViewById<ImageButton>(R.id.imageButton9).setOnClickListener(){
            editor.putString("LibretaAct","Trillosl4").commit()
            startActivity(Intent(this, libreta::class.java))
        }
        findViewById<ImageButton>(R.id.imageButton10).setOnClickListener(){
            editor.putString("LibretaAct","Trillosl5").commit()
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