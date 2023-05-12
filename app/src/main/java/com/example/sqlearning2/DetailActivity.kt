package com.example.sqlearning2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqlearning2.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("DataUser", Context.MODE_PRIVATE)

        val email = sharedPref.getString("user_email","")
        val password = sharedPref.getString("user_password", "")

        binding.txtEmail.text = "Email : ${email.toString()}"
        binding.txtPassword.text = "Password : ${password.toString()}"

        binding.btnLogout.setOnClickListener{
            with(sharedPref.edit()){
                clear()
                apply()
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}