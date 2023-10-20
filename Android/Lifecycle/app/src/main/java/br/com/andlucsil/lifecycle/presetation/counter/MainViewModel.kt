package br.com.andlucsil.lifecycle.presetation.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.andlucsil.lifecycle.domain.CounterRepository

/*class MainViewModel : ViewModel(){

    private val _counter = NumberLiveData()
    val counter:  LiveData<Int> = _counter

    public var incrementBy = 1

    fun increment() {
        val number = _counter.value?: 0
        _counter.value = number+incrementBy
    }
}

class NumberLiveData(initial: Int = 0) : MutableLiveData<Int>() {

    override fun onActive() {
        super.onActive()
        Log.d("MainViewModel", "onActive")
    }

    override fun onInactive() {
        super.onInactive()
        Log.d("MainViewModel", "onInactive")
    }
}*/

// View Model with dependencies
class MainViewModel(private val repository: CounterRepository) : ViewModel(){

    val counter:  LiveData<Int> = repository.getCounter()
    var incrementBy = 1

    fun increment() {
        repository.incrementCounterBy(incrementBy)
    }
}