package com.gurihouses.postproperty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gurihouses.R
import com.gurihouses.databinding.FragmentPostPropertyBinding

class PostPropertyFragment : Fragment() {

    lateinit var binding: FragmentPostPropertyBinding

    companion object {

        fun newInstance() =
            PostPropertyFragment().apply {
                arguments = Bundle().apply {


                }
            }
    }


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            // Inflate the layout for this fragment
            binding = FragmentPostPropertyBinding.inflate(layoutInflater)
            return binding.root
        }
