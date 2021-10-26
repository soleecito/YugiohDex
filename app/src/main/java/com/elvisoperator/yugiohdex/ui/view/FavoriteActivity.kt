package com.elvisoperator.yugiohdex.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}