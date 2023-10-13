package br.com.andlucsil.electriccarapp.ui

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.andlucsil.electriccarapp.R
import br.com.andlucsil.electriccarapp.data.CarsApi
import br.com.andlucsil.electriccarapp.data.local.CarRepository
import br.com.andlucsil.electriccarapp.domain.Carro
import br.com.andlucsil.electriccarapp.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class CarFragment : Fragment() {

    private lateinit var btnCalcular : FloatingActionButton
    private lateinit var listaCarros : RecyclerView
    private lateinit var progressBar : ProgressBar
    private lateinit var noInternetImage : ImageView
    private lateinit var noInternetText : TextView
    private lateinit var carsApi : CarsApi
    //var carrosArray : ArrayList<Carro> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetrofit()
        setupViews(view)
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        val checkInternet = checkForInternet(context)
        Log.d("Internet Connection", ""+checkInternet.toString())
        if (checkInternet) {
            getAllCars()
        } else {
            emptyState()
        }
    }

    fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://igorbag.github.io/cars-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        carsApi = retrofit.create(CarsApi::class.java)
    }

    fun getAllCars() {
        carsApi.getAllCars().enqueue(object : Callback<List<Carro>> {

            override fun onResponse(call: Call<List<Carro>>, response: Response<List<Carro>>) {
                if (response.isSuccessful) {
                    progressBar.isVisible = false
                    noInternetImage.isVisible = false
                    noInternetText.isVisible = false
                    response.body()?.let { setupList(it) }
                } else {
                    Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Carro>>, t: Throwable) {
                Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
            }

        })
    }

    fun emptyState() {
        progressBar.isVisible = false
        listaCarros.isVisible = false
        noInternetImage.isVisible = true
        noInternetText.isVisible = true
    }

    private fun setupViews(view: View) {
        view?.apply {
            btnCalcular = findViewById(R.id.fab_calcular)
            listaCarros = findViewById(R.id.rv_lista_carros)
            progressBar = findViewById(R.id.pb_loader)
            noInternetImage = findViewById(R.id.iv_empty_state)
            noInternetText = findViewById(R.id.tv_no_wifi)
        }
    }

    private fun setupListeners() {
        btnCalcular.setOnClickListener {
            startActivity(Intent(context, CalcularAutonomiaActivity::class.java))
        }
    }

    private fun setupList(lista: List<Carro>) {
        val carroAdapter = CarAdapter(lista, false)
        listaCarros.apply {
            visibility = View.VISIBLE
            adapter = carroAdapter
        }
        carroAdapter.carItemListener = {
            carro ->
            val isSaved = CarRepository(requireContext()).saveIfNotExist(carro)
            if (isSaved) {
                Toast.makeText(context, R.string.saved_database, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, R.string.saved_database_failure, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun checkForInternet(context: Context?) : Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network)?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo?:return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    /*fun callService() {
        //MyTask().execute("https://igorbag.github.io/cars-api/cars.json")
    }

    inner class MyTask : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d("MyTask", "Iniciando...")
            progressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg url: String?): String {
            var urlConnection: HttpURLConnection? = null
            try {
                val urlBase = URL(url[0])
                urlConnection = urlBase.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 60000
                urlConnection.readTimeout = 60000
                urlConnection.setRequestProperty(
                    "Accept",
                    "application/json"
                )
                val responseCode = urlConnection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    var response = urlConnection.inputStream.bufferedReader().use {
                        it.readText()
                    }
                    publishProgress(response)
                } else {
                    Log.e("Erro", "Serviço indisponível no momento")
                }

            } catch (ex: Exception) {
                Log.e("Erro", "Erro ao realizar o processamento...")
            } finally {
                urlConnection?.disconnect()
            }
            return ""
        }

        override fun onProgressUpdate(vararg values: String?) {
            try {
                val jsonArray = JSONTokener(values[0]).nextValue() as JSONArray
                for (i in 0 until jsonArray.length()) {
                    val id = jsonArray.getJSONObject(i).getString("id")
                    val preco = jsonArray.getJSONObject(i).getString("preco")
                    val bateria = jsonArray.getJSONObject(i).getString("bateria")
                    val potencia = jsonArray.getJSONObject(i).getString("potencia")
                    val recarga = jsonArray.getJSONObject(i).getString("recarga")
                    val urlPhoto = jsonArray.getJSONObject(i).getString("urlPhoto")
                    val model = Carro(
                        id = id.toInt(),
                        bateria = bateria,
                        potencia = potencia,
                        recarga = recarga,
                        preco = preco,
                        urlPhoto = urlPhoto,
                    )
                    carrosArray.add(model)
                }
                progressBar.isVisible = false
                noInternetImage.isVisible = false
                noInternetText.isVisible = false
                setupList(carrosArray)
            } catch (ex: Exception) {
                Log.e("Erro", ex.message.toString())
            }
        }

    }*/
}