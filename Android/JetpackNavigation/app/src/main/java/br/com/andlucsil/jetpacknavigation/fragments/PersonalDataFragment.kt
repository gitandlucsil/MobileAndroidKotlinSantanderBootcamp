package br.com.andlucsil.jetpacknavigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.andlucsil.jetpacknavigation.R
import br.com.andlucsil.jetpacknavigation.databinding.FragmentPersonalDataBinding
import br.com.andlucsil.jetpacknavigation.extensions.text
import br.com.andlucsil.jetpacknavigation.model.PersonModel

class PersonalDataFragment : Fragment() {

    private var _binding : FragmentPersonalDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, saved: Bundle?): View? {
        _binding = FragmentPersonalDataBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, saved: Bundle?) {
        super.onViewCreated(view, saved)
        binding.btNext.setOnClickListener {
            val model = PersonModel(
                name = binding.tilName.text,
                age = binding.tilAge.text.toInt()
            )
            val directions = PersonalDataFragmentDirections.actionPersonalDataFragmentToAddressFragment(model)
            findNavController().navigate(directions)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}