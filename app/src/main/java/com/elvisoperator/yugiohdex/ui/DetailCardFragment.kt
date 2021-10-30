package com.elvisoperator.yugiohdex.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.data.Data
import com.elvisoperator.yugiohdex.data.DataSource
import com.elvisoperator.yugiohdex.data.database.AppDatabase
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.data.model.BasicCardImage
import com.elvisoperator.yugiohdex.databinding.FragmentDetailCardBinding
import com.elvisoperator.yugiohdex.domain.RepositoryImplement
import com.elvisoperator.yugiohdex.ui.viewmodel.MainViewModel
import com.elvisoperator.yugiohdex.ui.viewmodel.VMFactory
import com.squareup.picasso.Picasso


//Requerir que el buendle sea del tipo BasicCard

class DetailCardFragment : Fragment() {


    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(RepositoryImplement(DataSource(AppDatabase.getDatabase(requireActivity().applicationContext))))
    }
    private lateinit var detailBinding: FragmentDetailCardBinding
    private lateinit var cardModel: Data
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().let {
            cardModel = it.getParcelable("card")!!
            Log.d(TAG, "OnCreate")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_detail_card, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailBinding = FragmentDetailCardBinding.bind(view)

        Picasso.get().load(cardModel.card_images[0].image_url).into(detailBinding.imageCard)
        detailBinding.tvName.text = cardModel.name
        detailBinding.tvType.text = cardModel.type
        detailBinding.tvDesc.text = cardModel.desc

        listeners()

    }

    private fun listeners() {
        saveOrDeletedListener()
    }

    private fun saveOrDeletedListener() {
        detailBinding.btnSaveOrDeleteCard.setOnClickListener {





            val image = BasicCardImage(
                id = cardModel.card_images[0].id,
                image_url = cardModel.card_images[0].image_url,
                image_url_small = cardModel.card_images[0].image_url_small
            )
            Log.d("Text", "$id")
            Log.d("Text", image.image_url)

            viewModel.saveCard(
                BasicCard(
                    id = cardModel.id,
                    name = cardModel.name,
                    type = cardModel.type,
                    level = cardModel.level,
                    image = image
                )
            )
            Toast.makeText(requireContext(), "letter was saved to favorites", Toast.LENGTH_LONG)
                .show()
        }
    }

}