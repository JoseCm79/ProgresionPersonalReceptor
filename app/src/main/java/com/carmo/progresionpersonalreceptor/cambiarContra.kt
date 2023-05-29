package com.carmo.progresionpersonalreceptor

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class cambiarContra : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambiar_contra)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        val contra1 = findViewById<EditText>(R.id.contrasena)
        val contra2 = findViewById<EditText>(R.id.contrasena2)

        contra1.setPaintFlags(contra1.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        contra2.setPaintFlags(contra2.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

        val boton = findViewById<ImageButton>(R.id.ingresarBoton3)

        val Siempre = getSharedPreferences("Siempre", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = Siempre.edit()


        boton.setOnClickListener(){
            if(contra1.getText().toString() == contra2.getText().toString()){
                if(contra1.getText().toString() != "1234"){
                    if(contra1.getText().toString().length in 8..20){

                            var url = "http://josecarmona79.tk/"+Siempre.getString("SeccionMainLink","")+"/ActualizarContra.php"
                            val queue = Volley.newRequestQueue(this)
                            val resultadoPost = object : StringRequest(
                                Method.POST, url,
                                Response.Listener<String> { response ->
                                    Toast.makeText(this, "Contraseña actualizada", Toast.LENGTH_LONG).show()
                                    startActivity(Intent(this, MainActivity::class.java))
                                    Toast.makeText(this, "Ahora inicia sesión con tu nueva contraseña", Toast.LENGTH_SHORT).show()
                                    finish()
                                }, Response.ErrorListener { error ->
                                    Toast.makeText(this, "Contraseña no ha sido actualizada"+error, Toast.LENGTH_LONG).show()
                                }){
                                override fun getParams(): MutableMap<String, String>? {
                                    val parametros = HashMap<String, String>()
                                    parametros.put("Nombre", Siempre.getString("Cambiar","").toString())
                                    parametros.put("Contrasena", contra1.getText().toString())
                                    return parametros
                                }
                            }
                            queue.add(resultadoPost)
                            editor.putString("Cambiar","").commit()
                    }else{
                        Toast.makeText(this, "La contraseña debe ser entre 8 a 20 caracteres", Toast.LENGTH_SHORT).show()
                        contra1.setText("")
                        contra2.setText("")
                    }
                }else{
                    Toast.makeText(this, "La contraseña no puede ser igual a la predetermida", Toast.LENGTH_SHORT).show()
                    contra1.setText("")
                    contra2.setText("")
                }


            }else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                contra1.setText("")
                contra2.setText("")
            }
        }
    }
}