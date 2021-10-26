package com.elvisoperator.yugiohdex.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.data.model.CardProvider
import com.elvisoperator.yugiohdex.data.repository.RecyclerRepository
import com.elvisoperator.yugiohdex.databinding.ActivityFavoriteBinding
import com.elvisoperator.yugiohdex.ui.fragments.CardAdapter

class FavoriteActivity : AppCompatActivity() {

    lateinit var binding: ActivityFavoriteBinding
    lateinit var favoriteCards: List<BasicCard>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        favoriteCards = RecyclerRepository().getFavorites()

        binding.recyclerFavoriteCard.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerFavoriteCard.setHasFixedSize(true)
        binding.recyclerFavoriteCard.adapter = CardAdapter(favoriteCards) {}


    }
}