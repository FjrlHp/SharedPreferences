package com.example.sqlearning2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.sqlearning2.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var dbHelper: UserDBHelper
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = UserDBHelper(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnRegister.setOnClickListener{
            val userEmail = binding.inputEmail.text.toString()
            val userPassword = binding.inputPassword.text.toString()

            if(userEmail.isNotEmpty() && userPassword.isNotEmpty()){
                val user = User(email = userEmail, password = userPassword)
                dbHelper.insertData(user)
                Toast.makeText(this,"Register Berhasil!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Data tidak boleh Kosong!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                onBackPressed()
                true
            }else -> super.onOptionsItemSelected(item)
        }
    }
}