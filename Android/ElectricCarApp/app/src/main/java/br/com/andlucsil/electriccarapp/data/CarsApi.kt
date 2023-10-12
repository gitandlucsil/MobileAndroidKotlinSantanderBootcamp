package br.com.andlucsil.electriccarapp.data

import retrofit2.Call
import br.com.andlucsil.electriccarapp.domain.Carro
import retrofit2.http.GET

interface CarsApi {

    @GET("cars.json")
    fun getAllCars(): Call<List<Carro>>
}