package dev.rio.ledremote.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.rio.ledremote.databinding.LedLayoutBinding
import dev.rio.ledremote.model.Led

class LedAdapter : RecyclerView.Adapter<LedAdapter.LedViewHolder>() {
    var onClickListener: (Long, Led) -> Unit = { l: Long, led: Led -> }
    var leds: MutableList<Led> = mutableListOf()

    class LedViewHolder(val binding: LedLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LedAdapter.LedViewHolder {
        val binding = LedLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return LedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LedAdapter.LedViewHolder, position: Int) {
        val led = leds[position]
        holder.binding.btnLedOn.setOnClickListener {
            onClickListener(led.id,led)
        }
    }

    override fun getItemCount(): Int {
        return leds.size
    }
}