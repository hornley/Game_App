package com.example.hornley.fragments.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.hornley.databinding.FragmentUpdateBinding
import com.example.hornley.database.viewmodel.CharacterViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)

        binding.updateName.setHint(args.currentCharacter.name)
        binding.updatebtn.setOnClickListener {
//            updateItem()
        }

//        setHasOptionsMenu()

        return binding.root
    }

//    private fun updateItem() {
//        val name = binding.updateName.text.toString()
//        if(inputCheck(name)) {
//            val updatedUser = User(args.currentUser.id, name)
//            mUserViewModel.updateUser(updatedUser)
//            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
//        }
//    }

    private fun inputCheck(name: String): Boolean {
        return !(TextUtils.isEmpty(name))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//    }

}