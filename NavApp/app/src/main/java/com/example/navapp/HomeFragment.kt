package com.example.navapp

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.navapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment()
{
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.btnSubmit.setOnClickListener {
            if (!TextUtils.isEmpty(binding.editText.text))
            {
                val bundle = bundleOf("name" to binding.editText.text.toString())
                it.findNavController().navigate(R.id.action_homeFragment_to_secondFragment, bundle)
            } else
            {
                binding.editText.error = "Field is mandatory"
            }
        }

        return binding.root
    }

}