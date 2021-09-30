package com.elvisoperator.yugiohdex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elvisoperator.yugiohdex.databinding.FragmentCardListBinding

class CardListFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCardListBinding>(inflater,
            R.layout.fragment_card_list,container,false)
        return binding.root
    }

}




