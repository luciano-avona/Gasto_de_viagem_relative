package com.example.gastoviagemrelative

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gastoviagemrelative.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener(this)
        binding.buttonAutonomy.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_calculate -> calculate()
            R.id.button_autonomy -> calculateAutonomy()
        }
    }

    private fun convertToFloat(value: String): Float? {
        return value.replace(",", ".").toFloatOrNull()
    }

    private fun calculate() {

        val distance = convertToFloat(binding.editDistance.text.toString())
        val price = convertToFloat(binding.editPrice.text.toString())
        val autonomy = convertToFloat(binding.editAutonomy.text.toString())

        if (distance != null && price != null && autonomy != null && autonomy != 0f) {

            val totalValue = (distance * price) / autonomy

            binding.textTotalValue.text = "R$ ${"%.2f".format(totalValue)}"

        } else {
            Toast.makeText(this, R.string.validation_fill_all_fields, Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateAutonomy() {

        val liters = convertToFloat(binding.editLiters.text.toString())
        val km = convertToFloat(binding.editKm.text.toString())

        if (liters != null && km != null && liters != 0f) {

            val result = km / liters

            binding.textResultAutonomy.text = "%.2f KM/L".format(result)
            binding.editAutonomy.setText("%.2f".format(result))

        } else {
            Toast.makeText(this, "Preencha os campos corretamente", Toast.LENGTH_SHORT).show()
        }
    }
}