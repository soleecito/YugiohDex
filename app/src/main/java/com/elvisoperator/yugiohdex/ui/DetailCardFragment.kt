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


class DetailCardFragment : Fragment() {


    private val viewModel by activityViewModels<MainViewModel>{
        VMFactory(RepositoryImplement(DataSource( AppDatabase.getDatabase(requireActivity().applicationContext) )))
    }
    private lateinit var detailBinding: FragmentDetailCardBinding
    private lateinit var cardModel : Data
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


        cardModel.card_images.forEach {
            Picasso.get().load(it.image_url).into(detailBinding.imageCard)
        }
        detailBinding.tvName.text  = cardModel.name
        detailBinding.tvType.text = cardModel.type
        detailBinding.tvDesc.text = cardModel.desc

        detailBinding.btnSaveOrDeleteCard.setOnClickListener {

            var  id  = 0
            var image  = ""
            var imageSmall  = ""

            cardModel.card_images.forEach { result ->
                id = result.id
                image = result.image_url
                imageSmall = result.image_url_small
            }

            Log.d("Text" , "${id}")
            Log.d("Text" , "${image}")


            val imagen  = BasicCardImage(id,image,imageSmall)

            /*a revisar */
            viewModel.saveCard(BasicCard(cardModel.id, cardModel.name , cardModel.type , cardModel.level , imagen  ) )
            Toast.makeText(requireContext(), "letter was saved to favorites", Toast.LENGTH_LONG).show()

        }
    }

}