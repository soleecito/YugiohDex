package com.elvisoperator.yugiohdex.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.UserApplication.Companion.prefs
import com.elvisoperator.yugiohdex.databinding.FragmentEditProfileBinding


class EditProfileFragment : Fragment() {

    lateinit var binding: FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditProfileBinding.bind(view)

        setView()
        setClickListeners()
    }

    private fun setView() {
        binding.tvName.text = "Actual name: ${prefs.getName()}"
        binding.tvDeckName.text = "Actual Deck name: ${prefs.getDeckName()}"
        binding.etDeck.isSingleLine = true
        binding.etName.isSingleLine = true
    }

    private fun setClickListeners() {
        binding.btnDone.setOnClickListener {
            saveNameToPrefs()
            saveDeckNameToPrefs()
            findNavController().popBackStack()
        }
    }

    private fun saveDeckNameToPrefs() {
        if (!binding.etDeck.text.toString().isNullOrEmpty()) {
            prefs.saveDeckName(binding.etDeck.text.toString())
        }
    }

    private fun saveNameToPrefs() {
        if (!binding.etName.text.toString().isNullOrEmpty()) {
            prefs.saveName(binding.etName.text.toString())
        }
    }

}