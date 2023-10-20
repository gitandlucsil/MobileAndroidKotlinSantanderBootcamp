package br.com.andlucsil.lifecycle.presetation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import br.com.andlucsil.lifecycle.data.CounterRepositoryImpl
import br.com.andlucsil.lifecycle.presetation.counter.MainViewModel

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass == MainViewModel::class.java) {
            return MainViewModel(repository = CounterRepositoryImpl()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel instance for $modelClass")
        //return super.create(modelClass, extras)
    }
}