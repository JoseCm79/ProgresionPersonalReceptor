package com.carmo.progresionpersonalreceptor

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class AreaWak : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area_wak)




        val Siempre = getSharedPreferences("Siempre", Context.MODE_PRIVATE)
        val AreasText = mutableListOf(findViewById<TextView>(R.id.basuText), findViewById<TextView>(R.id.usureText2), findViewById<TextView>(R.id.awakalText2), findViewById<TextView>(R.id.senukText3), findViewById<TextView>(R.id.senukText2))
        val editor : SharedPreferences.Editor = Siempre.edit()

        findViewById<ImageButton>(R.id.imageButton20).setOnClickListener(){
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val inflanter: LayoutInflater = getLayoutInflater()
            val view: View = inflanter.inflate(R.layout.dialog_kabata, null)
            builder.setView(view)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        findViewById<ImageView>(R.id.imageView15).setOnClickListener(){
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val inflanter: LayoutInflater = getLayoutInflater()
            val view: View = inflanter.inflate(R.layout.dialog_servicio, null)
            builder.setView(view)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        findViewById<ImageView>(R.id.imageView13).setOnClickListener(){
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val inflanter: LayoutInflater = getLayoutInflater()
            val view: View = inflanter.inflate(R.layout.dialog_tecno, null)
            builder.setView(view)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        findViewById<ImageView>(R.id.imageView12).setOnClickListener(){
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val inflanter: LayoutInflater = getLayoutInflater()
            val view: View = inflanter.inflate(R.layout.dialog_arte, null)
            builder.setView(view)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        findViewById<ImageView>(R.id.imageView14).setOnClickListener(){
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val inflanter: LayoutInflater = getLayoutInflater()
            val view: View = inflanter.inflate(R.layout.dialog_deporte, null)
            builder.setView(view)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        for (i in 1..AreasText.size){
            if(Siempre.getString("Area"+i,"0") == "0"){
                AreasText.get(i-1).setText("Evaluación: Inicial")
            }else{
                if(Siempre.getString("Area"+i,"0") == "1"){
                    AreasText.get(i-1).setText("Evaluación: Intermedio")
                }else{
                    AreasText.get(i-1).setText("Evaluación: Avanzado")
                }
            }


        }


        findViewById<ImageButton>(R.id.imageButton21).setOnClickListener(){
            editor.putString("LibretaAct","arealibreta5").commit()
            startActivity(Intent(this, libreta::class.java))
        }
        findViewById<ImageButton>(R.id.imageButton18).setOnClickListener(){
            editor.putString("LibretaAct","arealibreta4").commit()
            startActivity(Intent(this, libreta::class.java))
        }
        findViewById<ImageButton>(R.id.imageButton17).setOnClickListener(){
            editor.putString("LibretaAct","arealibreta3").commit()
            startActivity(Intent(this, libreta::class.java))
        }
        findViewById<ImageButton>(R.id.imageButton22).setOnClickListener(){
            editor.putString("LibretaAct","arealibreta2").commit()
            startActivity(Intent(this, libreta::class.java))
        }
        findViewById<ImageButton>(R.id.imageButton19).setOnClickListener(){
            editor.putString("LibretaAct","arealibreta1").commit()
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