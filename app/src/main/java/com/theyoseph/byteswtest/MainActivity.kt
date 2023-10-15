package com.theyoseph.byteswtest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login()
    }

    private fun login(){
        val btnLogin = findViewById<Button>(R.id.loginBtn)
        val btnRegister = findViewById<Button>(R.id.registerBtn)
        val txtUser = findViewById<EditText>(R.id.userText).text
        val txtPass = findViewById<EditText>(R.id.passText).text
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val regex = Regex("^(?=.*[A-Z]).{6,}$")

        btnLogin.setOnClickListener {
            val localUser = prefs.getString(txtUser.toString(), null)
            val localPass = prefs.getString("${txtUser}_pass", null)
            if (txtUser.isNotEmpty() && txtPass.isNotEmpty()) {
                if (localUser != null && localPass != null){
                    if ((txtUser.toString() == localUser.toString()) && (txtPass.toString() == localPass.toString())){
                        val intent = Intent(this, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent.apply { putExtra("user", localUser) })
                    } else {
                        showAlert("Usuario o clave incorrectos")
                    }
                } else {
                    showAlert("El usuario o contraseña no existe")
                }
            } else {
                showAlert("Campos obligatorios vacios")
            }
        }

        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun showAlert(info: String){
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Error")
        alert.setMessage(info)
        alert.setPositiveButton("Aceptar", null)
        alert.create().show()
    }
    public override fun onBackPressed() {
        //Bloqueo de boton atras
    }
}
