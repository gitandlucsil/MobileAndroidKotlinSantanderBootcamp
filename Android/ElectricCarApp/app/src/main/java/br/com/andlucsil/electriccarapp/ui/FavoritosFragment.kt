package br.com.andlucsil.electriccarapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.andlucsil.electriccarapp.R
import br.com.andlucsil.electriccarapp.data.local.CarRepository
import br.com.andlucsil.electriccarapp.domain.Carro
import br.com.andlucsil.electriccarapp.ui.adapter.CarAdapter

class FavoritosFragment : Fragment() {

    private lateinit var listaCarrosFavoritos : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favoritos_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList()
    }

    private fun getCarsOnLocalDb(): List<Carro> {
        val repository = CarRepository(requireContext())
        val carList = repository.getAll()
        Log.d("Lista de carros",carList.size.toString())
        return carList
    }

    private fun setupView(view: View) {
        view.apply {
            listaCarrosFavoritos = findViewById(R.id.rv_lista_carros_favoritos)
        }
    }

    private fun setupList() {
        val carroAdapter = CarAdapter(getCarsOnLocalDb(), true)
        listaCarrosFavoritos.apply {
            visibility = View.VISIBLE
            adapter = carroAdapter
        }
        carroAdapter.carItemListener = {
        }
    }


}