package br.com.andlucsil.electriccarapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import br.com.andlucsil.electriccarapp.R
import br.com.andlucsil.electriccarapp.data.CarFactory
import br.com.andlucsil.electriccarapp.ui.adapter.CarAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var btnRedirect : Button
    private lateinit var listaCarros : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        setupListeners()
        setupList()
    }

    private fun setupViews() {
        btnRedirect = findViewById(R.id.btn_redirect)
        listaCarros = findViewById(R.id.rv_lista_carros)
    }

    private fun setupListeners() {
        btnRedirect.setOnClickListener {
            //calcular()
            startActivity(Intent(this, CalcularAutonomiaActivity::class.java))
        }
    }

    private fun setupList() {
        val adapter = CarAdapter(CarFactory.list)
        listaCarros.adapter = adapter
    }

}