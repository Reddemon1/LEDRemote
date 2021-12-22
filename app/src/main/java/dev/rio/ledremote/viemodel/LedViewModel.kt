package dev.rio.ledremote.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dev.rio.ledremote.datasource.LedDatasource
import dev.rio.ledremote.model.Led
import dev.rio.ledremote.tool.ButtonOption
import java.util.*

class LedViewModel(var ledDatasource: LedDatasource) : ViewModel() {
    val leds : MutableLiveData<MutableList<Led>> = MutableLiveData(Collections.emptyList())
    fun loadLed() : LiveData<MutableList<Led>>{
        return liveData {
            val led = ledDatasource.getLed()
            this@LedViewModel.leds.postValue(led)
        }
    }

    fun editLed(id:Long, led: Led , buttonOption : ButtonOption) : LiveData<MutableList<Led>> {
        return liveData {
            if (buttonOption == ButtonOption.ON){
                led.status = "ON"
            }else if(buttonOption == ButtonOption.OFF){
                led.status = "OFF"
            }
            ledDatasource.editLed(id,led)
            val leds = ledDatasource.getLed()
            this@LedViewModel.leds.postValue(leds)
        }
    }
}