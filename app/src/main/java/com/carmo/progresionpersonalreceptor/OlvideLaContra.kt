package com.carmo.progresionpersonalreceptor

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class OlvideLaContra : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_olvide_la_contra)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        val contra2 = findViewById<EditText>(R.id.NombreCompleto)
        val cancion = findViewById<EditText>(R.id.cancion2)
        val boton = findViewById<ImageButton>(R.id.ingresarBoton)

        val Siempre = getSharedPreferences("Siempre", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = Siempre.edit()

        val checkbox1 = findViewById<CheckBox>(R.id.checkBox4)
        val checkbox2 = findViewById<CheckBox>(R.id.checkBox5)
        val checkbox3 = findViewById<CheckBox>(R.id.checkBox6)
        var Seccion = Siempre.getString("SeccionMain","0").toString().toInt()
        fun checkear(estado : Int, col : MutableList<CheckBox>){
            if(estado == 0){
                col.get(0).isChecked = true
                col.get(1).isChecked = false
                col.get(2).isChecked = false
            }
            else{
                if(estado == 1){
                    col.get(0).isChecked = false
                    col.get(1).isChecked = true
                    col.get(2).isChecked = false
                }else {
                    col.get(0).isChecked = false
                    col.get(1).isChecked = false
                    col.get(2).isChecked = true
                }


            }
        }
        val checkboxCol1 = mutableListOf(checkbox1,checkbox2,checkbox3)
        checkear(Seccion, checkboxCol1)
        checkbox1.setOnClickListener(){
            Seccion = 0
            checkear(Seccion, checkboxCol1)

            editor.putString("SeccionMain", Seccion.toString()).commit()
            editor.putString("SeccionMainLink", "tropa").commit()
        }
        checkbox2.setOnClickListener(){
            Seccion = 1
            checkear(Seccion, checkboxCol1)

            editor.putString("SeccionMain", Seccion.toString()).commit()
            editor.putString("SeccionMainLink", "wak").commit()
        }
        checkbox3.setOnClickListener(){
            Seccion = 2
            checkear(Seccion, checkboxCol1)

            editor.putString("SeccionMain", Seccion.toString()).commit()
            editor.putString("SeccionMainLink", "comu").commit()
        }


        boton.setOnClickListener(){
            val canciontxt = cancion.getText().toString()
            val queue = Volley.newRequestQueue(this)
            val url =
                "http://josecarmona79.tk/"+Siempre.getString("SeccionMainLink","")+"/consultar.php/?Nombre=${contra2.getText().toString()}"
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                Response.Listener { response ->
                    if (response.getString("Cancion") == null) {
                        Toast.makeText(
                            this,
                            "Inicia sesión con la contraseña predeterminada",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                            if (response.getString("Cancion").toString() == cancion.getText().toString()) {
                            editor.putString("Cambiar", contra2.getText().toString()).commit()
                            startActivity(Intent(this, cambiarContra::class.java))
                            finish()
                        }else{
                            Toast.makeText(this, "La canción es erronea", Toast.LENGTH_SHORT).show()
                        }
                    }
                }, Response.ErrorListener { error ->
                    Toast.makeText(this, "No se encontró un receptor a ese nombre", Toast.LENGTH_SHORT).show()
                }

            )
            queue.add(jsonObjectRequest)
        }


    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }
    }
