package dev.rio.ledremote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.rio.ledremote.adapter.LedAdapter
import dev.rio.ledremote.api.ApiService
import dev.rio.ledremote.databinding.ActivityMainBinding
import dev.rio.ledremote.datasource.LedDatasource
import dev.rio.ledremote.model.Led
import dev.rio.ledremote.viemodel.LedViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var ledViewModel: LedViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var ledAdapter: LedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setView()


    }

    private fun setView() {
        setAdapter()
        setViewModel()
        initData()
    }

    private fun initData() {

        ledViewModel.loadLed().observe(this) {

        }

        ledViewModel.leds.observe(this, androidx.lifecycle.Observer {
            ledAdapter.apply {
                leds = it
                notifyDataSetChanged()
            }
            ledViewModel.loadLed()
        })
    }

    private fun setViewModel() {
        var ledDatasource: LedDatasource = LedDatasource(ApiService.getLed())
        ledViewModel = LedViewModel(ledDatasource)

    }

    private fun setAdapter() {
        ledAdapter = LedAdapter()
        ledAdapter.leds = Collections.emptyList()
        binding.ledRecycler.layoutManager = LinearLayoutManager(this)
        binding.ledRecycler.adapter = ledAdapter


        ledAdapter.onClickListener = { id: Long, led: Led ->
            ledViewModel.editLed(id,led).observe(this, androidx.lifecycle.Observer {
                if(led.status == "ON"){
                    Toast.makeText(this,"Berhasil Mematikan Lampu",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Berhasil Menghidupkan Lampu",Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}