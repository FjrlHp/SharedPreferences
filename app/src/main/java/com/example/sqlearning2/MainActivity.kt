package com.example.sqlearning2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.sqlearning2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sharedPrefs :SharedPreferences
    lateinit var dbHelper: UserDBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences("DataUser", Context.MODE_PRIVATE)

        dbHelper = UserDBHelper(this)

        binding.btnLogin.setOnClickListener{
            val email  = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                val user = dbHelper.getUser(email, password)

                if(user != null){
                    val intent = Intent(this, DetailActivity::class.java)

                    with(sharedPrefs.edit()){
                        putInt("user_id", user.id)
                        putString("user_email", user.email)
                        putString("user_password", user.password)
                        apply()
                    }
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,"Email atau Password Salah!!!", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Data tidak boleh Kosong!!!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}