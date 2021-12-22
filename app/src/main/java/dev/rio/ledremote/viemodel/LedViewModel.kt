package dev.rio.ledremote.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dev.rio.ledremote.datasource.LedDatasource
import dev.rio.ledremote.model.Led
import java.util.*

class LedViewModel(var ledDatasource: LedDatasource) : ViewModel() {
    val leds : MutableLiveData<MutableList<Led>> = MutableLiveData(Collections.emptyList())
    fun loadLed() : LiveData<MutableList<Led>>{
        return liveData {
            val led = ledDatasource.getLed()
            this@LedViewModel.leds.postValue(led)
        }
    }

    fun editLed(id:Long, led: Led ) : LiveData<MutableList<Led>> {
        return liveData {
            if (led.status == "ON"){
                led.status = "OFF"
            }else{
                led.status = "ON"
            }
            ledDatasource.editLed(id,led)
            val leds = ledDatasource.getLed()
            this@LedViewModel.leds.postValue(leds)
        }
    }
}