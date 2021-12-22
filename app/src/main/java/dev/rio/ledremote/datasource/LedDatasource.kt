package dev.rio.ledremote.datasource

import android.app.Service
import dev.rio.ledremote.api.LedApi
import dev.rio.ledremote.model.Led

class LedDatasource(var ledApi: LedApi) {
    suspend fun getLed(): MutableList<Led> {
        return ledApi.getLed();
    }

    suspend fun editLed(id: Long, led: Led): MutableList<Led> {
        return ledApi.editLed(id, led)
    }
}