package br.com.andlucsil.electriccarapp.data

import br.com.andlucsil.electriccarapp.domain.Carro

object CarFactory {

    val list = listOf<Carro>(
        Carro (1, "R$ 300.000", "300 kWh", "200cv", "30 min", "", isFavorite = false),
        Carro (2, "R$ 200.000", "200 kWh", "150cv", "60 min", "", isFavorite = false),
        Carro (3, "R$ 100.000", "100 kWh", "84cv", "120 min", "", isFavorite = false)
    )
}