package dev.rio.ledremote.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.rio.ledremote.databinding.ActivityMainBinding
import dev.rio.ledremote.databinding.LedLayoutBinding
import dev.rio.ledremote.model.Led
import dev.rio.ledremote.tool.ButtonOption

class LedAdapter : RecyclerView.Adapter<LedAdapter.LedViewHolder>() {
    var onClickListener: (Long, Led, ButtonOption) -> Unit = { l: Long, led: Led, buttonOption: ButtonOption -> }
    var leds: MutableList<Led> = mutableListOf()

    class LedViewHolder(val binding: LedLayoutBinding,val mainBinding: ActivityMainBinding ) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LedAdapter.LedViewHolder {
        val binding = LedLayoutBinding.inflate(LayoutInflater.from(parent.context))
        val mainBinding = ActivityMainBinding.inflate(LayoutInflater.from(parent.context))
        return LedViewHolder(binding,mainBinding)
    }

    override fun onBindViewHolder(holder: LedAdapter.LedViewHolder, position: Int) {
        val led = leds[position]
        holder.mainBinding.ledStatus.text = led.status
        holder.binding.btnLedOn.setOnClickListener {
            onClickListener(led.id,led , ButtonOption.ON)
        }
        holder.binding.btnLedOff.setOnClickListener {
            onClickListener(led.id,led , ButtonOption.OFF)
        }
    }

    override fun getItemCount(): Int {
        return leds.size
    }
}