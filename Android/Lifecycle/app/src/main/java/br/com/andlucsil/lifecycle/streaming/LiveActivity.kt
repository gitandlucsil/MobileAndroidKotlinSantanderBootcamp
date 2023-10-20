package br.com.andlucsil.lifecycle.streaming

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class LiveActivity : AppCompatActivity() {

    private val liveVideo = LiveVideo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(liveVideo)
    }


}