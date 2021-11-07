package com.elvisoperator.yugiohdex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.elvisoperator.yugiohdex.UserApplication.Companion.prefs
import com.elvisoperator.yugiohdex.databinding.ActivityMainBinding
import com.elvisoperator.yugiohdex.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        checkUserValues()
    }

    private fun checkUserValues() {
        if (prefs.getName().isNotEmpty()){
            goToMain()
        }
    }

    fun initUI(){
        binding.btnContinue.setOnClickListener { accessToMain()}
    }

    private fun accessToMain() {

        val userName = binding.etName.text.toString()
        val deckName = binding.etDeck.text.toString()

        if (userName.isEmpty() || deckName.isEmpty()){
            Toast.makeText(this, "Por favor llena los campos", Toast.LENGTH_SHORT).show()
        }else{
            prefs.saveName(userName)
            prefs.saveDeckName(deckName)
            goToMain()
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}