package com.theyoseph.byteswtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val servGen = ServiceGenerator.buildService(ApiService::class.java)
        val call = servGen.getData()

        val userName = intent.extras?.getString("user", "USUARIO_NO_VALIDO")
        val txtUser = findViewById<TextView>(R.id.txtUser)
        val btnOut = findViewById<Button>(R.id.homeBtnSalir)
        txtUser.text = "Bienvenido $userName"

        call.enqueue(object: Callback<MutableList<ResponseAPIElement>>{
            override fun onResponse(
                call: Call<MutableList<ResponseAPIElement>>,
                response: Response<MutableList<ResponseAPIElement>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let { remplaceFragment(MasterFragment(), it) }
                }
            }
            override fun onFailure(call: Call<MutableList<ResponseAPIElement>>, t: Throwable) {
                t.printStackTrace()
                Log.e("Response: ", t.message.toString())
            }
        })

        showAlert("Sea Bienvenido De Nuevo $userName")

        btnOut.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun remplaceFragment(fragment: Fragment, info: MutableList<ResponseAPIElement>){
        val fragment = MasterFragment.newInstance(info)
        val fragTrans = supportFragmentManager.beginTransaction()
        fragTrans.replace(R.id.frameLayout, fragment)
        fragTrans.commit()
    }

    private fun showAlert(info: String){
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Bienvenido")
        alert.setMessage(info)
        alert.setPositiveButton("Aceptar", null)
        alert.create().show()
    }

    public override fun onBackPressed() {
        //super.onBackPressed()
        //Bloqueo de boton atras
    }
}