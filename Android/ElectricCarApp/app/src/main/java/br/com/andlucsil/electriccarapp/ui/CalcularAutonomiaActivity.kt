package br.com.andlucsil.electriccarapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.andlucsil.electriccarapp.R

class CalcularAutonomiaActivity : AppCompatActivity() {

    private lateinit var preco : EditText
    private lateinit var kmPercorrido : EditText
    private lateinit var btnCalcular : Button
    private lateinit var tvResultado : TextView
    private lateinit var ivClose : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcular_autonomia)
        setupViews()
        setupListeners()
    }

    private fun setupViews() {
        preco = findViewById(R.id.et_preco_kwh)
        kmPercorrido = findViewById(R.id.et_km_percorrido)
        btnCalcular = findViewById(R.id.btn_calcular)
        tvResultado = findViewById(R.id.tv_resultado)
        ivClose = findViewById(R.id.iv_close)
    }

    private fun setupListeners() {
        btnCalcular.setOnClickListener {
            calcular()
        }
        ivClose.setOnClickListener {
            finish()
        }
    }

    private fun calcular() {
        val preco = preco.text.toString().toFloat()
        val km = kmPercorrido.text.toString().toFloat()
        val resultado = preco / km
        Log.d("Preço -> ", preco.toString())
        Log.d("Km Percorrido -> ", km.toString())
        Log.d("Resultado -> ", resultado.toString())
        tvResultado.text = resultado.toString()
    }
}
