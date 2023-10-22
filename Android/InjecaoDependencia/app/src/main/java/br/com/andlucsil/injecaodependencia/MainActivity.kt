package br.com.andlucsil.injecaodependencia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var  model : HelloModel

    private val viewmodel : HelloViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.hello).text = model.text
    }
}

interface HelloModel {
    val text: String
}

class HelloModelImpl @Inject constructor(): HelloModel {
    override val text: String = "Hello from Impl!"
}

class HelloViewModel @Inject constructor() : ViewModel() {
    val text: String = "Hello from View MOdel!"
}

