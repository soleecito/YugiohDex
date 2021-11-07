package com.elvisoperator.yugiohdex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        checkUserValue()

    }

    private fun checkUserValue() {
        if (prefs.getName().isNotEmpty()){
            goToMain()
            finish()
        }
    }

    fun initUI(){
        binding.btnContinue.setOnClickListener { accessToMain()}
    }

    private fun accessToMain() {
        if(binding.etName.text.toString().isNotEmpty()){
            prefs.saveName(binding.etName.text.toString())
            goToMain()
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}