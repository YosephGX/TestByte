package com.theyoseph.byteswtest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        register()
    }

    private fun  register(){
        val btnRegister = findViewById<Button>(R.id.registerBtnRegis)
        val btnLogin = findViewById<Button>(R.id.loginBtnRegis)
        val txtUser = findViewById<EditText>(R.id.userTextRegis).text
        val txtPass = findViewById<EditText>(R.id.passTextRegis).text
        val txtPassVer = findViewById<EditText>(R.id.verPassTextRegis).text
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        val regex = Regex("^(?=.*[A-Z]).{6,}$")

        btnRegister.setOnClickListener {
            val localUser = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).getString(txtUser.toString(), null)
            if ((txtUser.length > 7) && regex.matches(txtPass.toString())){
                if (txtPass.toString() == txtPassVer.toString()){
                    if (localUser == null){
                        prefs.putString(txtUser.toString(), txtUser.toString())
                        prefs.putString("${txtUser}_pass", txtPass.toString())
                        prefs.apply()
                        txtUser.clear()
                        txtPass.clear()
                        txtPassVer.clear()
                        showAlert("Registro Correcto", "Se ah registrado el usuario satisfactoriamente, ya puede iniciar sesion.")
                    } else {
                        showAlert("Error", "El usuario que intenta crear ya existe")
                    }
                } else {
                    showAlert("Error", "Las contraseñas ingresadas no coinciden")
                }
            } else {
                showAlert("Error", "El usuario debe tener al menos 8 caracteres y la clave debe tener al menos 6 caracteres y una mayuscula")
            }
        }

        btnLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun showAlert(title: String, info: String){
        val alert = AlertDialog.Builder(this)
        alert.setTitle(title)
        alert.setMessage(info)
        alert.setPositiveButton("Aceptar", null)
        alert.create().show()
    }

    public override fun onBackPressed() {
        //Bloqueo de boton atras
    }
}