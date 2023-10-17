package br.com.andlucsil.jetpacknavigation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.andlucsil.jetpacknavigation.databinding.FragmentAddressBinding
import br.com.andlucsil.jetpacknavigation.extensions.text

class AddressFragment  : Fragment() {

    private var _binding : FragmentAddressBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<AddressFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, saved: Bundle?): View? {
        _binding = FragmentAddressBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, saved: Bundle?) {
        super.onViewCreated(view, saved)
        Log.e("TAG", "onViewCreated: ${args.model}")
        binding.btNext.setOnClickListener {
            val model = args.model.copy(
                street = binding.tilStreet.text,
                number = binding.tilNumber.text.toInt()
            )
            val directions = AddressFragmentDirections.actionAddressFragmentToResumeFragment(model)
            findNavController().navigate(directions)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}