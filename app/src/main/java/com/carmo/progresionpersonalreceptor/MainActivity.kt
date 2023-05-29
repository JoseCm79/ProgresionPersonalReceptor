package com.carmo.progresionpersonalreceptor

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.graphics.Paint
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        val Siempre = getSharedPreferences("Siempre", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = Siempre.edit()

        if(Siempre.getString("CuentaLog","") != ""){
            val intento = Intent(this, wakinicio::class.java)
            startActivity(intento)
            finish()
        }

        val con : ConnectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = con.activeNetworkInfo;
        if(networkInfo?.isConnected.toString() == "null" && Siempre.getString("CuentaLog","") == ""){
            Toast.makeText(this,"Necesitas tener la sesión iniciada para poder conectarte sin internet.", Toast.LENGTH_SHORT).show()
            finish()

        }

        val checkbox1 = findViewById<CheckBox>(R.id.checkBox)
        val checkbox2 = findViewById<CheckBox>(R.id.checkBox2)
        val checkbox3 = findViewById<CheckBox>(R.id.checkBox3)

        val NombreEntry = findViewById<EditText>(R.id.NombreEntry)
        val ContraEntry = findViewById<EditText>(R.id.ContraEntry)
        NombreEntry.setPaintFlags(NombreEntry.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        ContraEntry.setPaintFlags(ContraEntry.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        val Boton = findViewById<ImageButton>(R.id.IngresarBoton)
        val Olvidar = findViewById<TextView>(R.id.Olvido)
        val infoboton = findViewById<ImageButton>(R.id.info2)
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

        infoboton.setOnClickListener(){


            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val inflanter: LayoutInflater = getLayoutInflater()
            val view: View = inflanter.inflate(R.layout.dialog_info, null)
            builder.setView(view)
            val dialog: AlertDialog = builder.create()
            dialog.show()
            view.findViewById<TextView>(R.id.textView21).setPaintFlags(view.findViewById<TextView>(R.id.textView21).getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
            view.findViewById<TextView>(R.id.info).setPaintFlags(view.findViewById<TextView>(R.id.info).getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

            val gracias = view.findViewById<ImageView>(R.id.gracias)
            gracias.setOnClickListener() {
                Toast.makeText(this, "Muchas gracias por leer la información", Toast.LENGTH_SHORT)
                    .show()
                dialog.dismiss()
            }



        }
        Olvidar.setOnClickListener(){
            startActivity(Intent(this, OlvideLaContra::class.java))
        }
        Boton.setOnClickListener(){
            if(NombreEntry.getText().toString() != "" && ContraEntry.getText().toString() != ""){
                var queue = Volley.newRequestQueue(this)
                var url = "http://josecarmona79.tk/"+Siempre.getString("SeccionMainLink","")+"/consultar.php/?Nombre=${NombreEntry.getText().toString()}"
                val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.GET, url, null,
                    { response ->
                        if (response.getString("Disponible") == "true"){
                            editor.putString("NombreAct",NombreEntry.getText().toString()).commit()
                            if(response.getString("Contrasena") == "1234" && ContraEntry.getText().toString() == "1234") {

                                startActivity(Intent(this, iniciarsesion::class.java))
                                finish()
                            }else{

                                if(ContraEntry.getText().toString() == response.getString("Contrasena")){

                                    editor.putString("NombreActWak",NombreEntry.getText().toString()).commit()

                                    val intento = Intent(this, wakinicio::class.java)
                                    startActivity(intento)
                                    Toast.makeText(this, "Se ha iniciado sesión", Toast.LENGTH_SHORT).show()
                                    finish()


                                }else{
                                    Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }else{
                            Toast.makeText(this, "Tu cuenta no está habilitada", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }

                    }, { error ->
                        Toast.makeText(this, "Pide a tu dirigente que registre tu cuenta", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }

                )
                queue.add(jsonObjectRequest)
            }else{
                Toast.makeText(this, "Llena todos los espacios", Toast.LENGTH_SHORT).show()
            }
            
        }
    }


}