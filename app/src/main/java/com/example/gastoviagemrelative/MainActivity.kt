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
        if (view.id == R.id.button_calculate) {
            calculate()
        } else if (view.id == R.id.button_autonomy) {
            calculateAutonomy()
        }
    }

    private fun isValid(): Boolean {
        return (binding.editDistance.text.toString() != ""
                && binding.editPrice.text.toString() != ""
                && binding.editAutonomy.text.toString() != ""
                && binding.editAutonomy.text.toString().toFloat() != 0f)
    }

    private fun calculate() {
        if (isValid()) {
            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val autonomy = binding.editAutonomy.text.toString().toFloat()

            val totalvalue = (distance * price) / autonomy

            binding.textTotalValue.text = "R$ ${"%.2f".format(totalvalue)}"
        } else {
            Toast.makeText(this, R.string.validation_fill_all_fields, Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidAutonomy(): Boolean {
        return (binding.editLiters.text.toString() != ""
                && binding.editKm.text.toString() != ""
                && binding.editLiters.text.toString().toFloat() != 0f)
    }
    private fun calculateAutonomy() {
        if (isValidAutonomy()) {

            val liters = binding.editLiters.text.toString().toFloat()
            val km = binding.editKm.text.toString().toFloat()

            val result = km / liters

            binding.textResultAutonomy.text = "%.2f KM/L".format(result)
            binding.editAutonomy.setText("%.2f".format(result))

        } else {
            Toast.makeText(this, "Preencha os campos de autonomia", Toast.LENGTH_SHORT).show()
        }
    }
}