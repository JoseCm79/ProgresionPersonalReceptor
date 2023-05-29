package com.carmo.progresionpersonalreceptor

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class wakinicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wakinicio)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        val Siempre = getSharedPreferences("Siempre", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = Siempre.edit()
        val persona_Space = findViewById<TextView>(R.id.persona_wak)
        persona_Space.text = Siempre.getString("NombreActWak","")


        editor.putString("CuentaLog",Siempre.getString("NombreActWak","")).commit()



        val queue = Volley.newRequestQueue(this)
        val url = "http://josecarmona79.tk/wak/consultar.php/?Nombre=${Siempre.getString("NombreActWak","")}"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener   {
                    response ->
                editor.putString("Etapa1", response.getString("Etapa1")).commit()
                editor.putString("Etapa2", response.getString("Etapa2")).commit()
                editor.putString("Etapa3", response.getString("Etapa3")).commit()
                editor.putString("Trillos1",response.getString("Trillos1")).commit()
                editor.putString("Trillos2",response.getString("Trillos2")).commit()
                editor.putString("Trillos3",response.getString("Trillos3")).commit()
                editor.putString("Trillos4",response.getString("Trillos4")).commit()
                editor.putString("Trillos5",response.getString("Trillos5")).commit()
                editor.putString("Area1",response.getString("Area1")).commit()
                editor.putString("Area2",response.getString("Area2")).commit()
                editor.putString("Area3",response.getString("Area3")).commit()
                editor.putString("Area4",response.getString("Area4")).commit()
                editor.putString("Area5",response.getString("Area5")).commit()
                //libretas
                editor.putString("Etapal1",response.getString("Etapal1")).commit()
                editor.putString("Etapal2",response.getString("Etapal2")).commit()
                editor.putString("Etapal3",response.getString("Etapal3")).commit()
                editor.putString("Trillosl1",response.getString("Trillosl1")).commit()
                editor.putString("Trillosl2",response.getString("Trillosl2")).commit()
                editor.putString("Trillosl3",response.getString("Trillosl3")).commit()
                editor.putString("Trillosl4",response.getString("Trillosl4")).commit()
                editor.putString("Trillosl5",response.getString("Trillosl5")).commit()
                editor.putString("equipoL",response.getString("equipoL")).commit()
                editor.putString("wakL",response.getString("wakL")).commit()
                editor.putString("personalL",response.getString("personalL")).commit()
                editor.putString("arealibreta1",response.getString("arealibreta1")).commit()
                editor.putString("arealibreta2",response.getString("arealibreta2")).commit()
                editor.putString("arealibreta3",response.getString("arealibreta3")).commit()
                editor.putString("arealibreta4",response.getString("arealibreta4")).commit()
                editor.putString("arealibreta5",response.getString("arealibreta5")).commit()
            }
            , Response.ErrorListener { error ->
                Toast.makeText(this, "No se ha podido actualizar los datos", Toast.LENGTH_SHORT).show()
            }

        )
        queue.add(jsonObjectRequest)




        val etapasWak = findViewById<ImageButton>(R.id.etapasWak)
        val metasWak = findViewById<ImageButton>(R.id.metasWak)
        val trillosWak = findViewById<ImageButton>(R.id.trillosWak)
        val areaWak = findViewById<ImageButton>(R.id.areaWak)
        val salir = findViewById<ImageButton>(R.id.configuracion)
        salir.setOnClickListener(){
            editor.putString("CuentaLog","").commit()
            Toast.makeText(this, "Se ha cerrado la sesión", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        etapasWak.setOnClickListener(){
            startActivity(Intent(this, EtapasWak::class.java))
            finish()
        }
        metasWak.setOnClickListener() {
            startActivity(Intent(this, MetasWak::class.java))
            finish()
        }
        trillosWak.setOnClickListener() {
            startActivity(Intent(this, TrillosWak::class.java))
            finish()
        }
        areaWak.setOnClickListener() {
            startActivity(Intent(this, AreaWak::class.java))
            finish()
        }

    }
}