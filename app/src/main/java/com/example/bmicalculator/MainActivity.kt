package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ageText = findViewById<TextView>(R.id.etAge)
        val weightText = findViewById<TextView>(R.id.etWeight)
        val heightText = findViewById<TextView>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)

        calcButton.setOnClickListener {
            val age = ageText.text.toString()
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if(inputvalidator(weight,height,age)){

                val bmi = weight.toFloat()/((height.toFloat()/100)*(height.toFloat()/100))
                val newage = age.toFloat()
                //limiting the result to 2 decimal places for the user to easily understand the result.
                val bmilimited = String.format("%.2f",bmi).toFloat()
                displayResult(bmilimited)
                agecal(newage,bmilimited)
            }

        }
    }

    private fun inputvalidator(weight:String?, height:String?,age:String?):Boolean{
        var ager:String ?= age.toString()

        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this,"No input for weight", Toast.LENGTH_LONG).show()
                return false
            }
            ager.isNullOrEmpty() ->{
                Toast.makeText(this,"No input for Age", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this,"No input for height", Toast.LENGTH_LONG).show()
                return false
            }else -> {return true}
        }
    }

    private fun displayResult(bmi : Float){
        var resultIndex = findViewById<TextView>(R.id.tvindex)
        var resultDescription = findViewById<TextView>(R.id.tvresult)
        val info = findViewById<TextView>(R.id.tvinfo)

        resultIndex.text = bmi.toString()
        info.text = "(Normal range is between 18.5 - 24.9)"

        var resultText = ""
        var color = 0

        when{
            bmi < 16 ->{
                resultText = "Severe Thiness"
                color = R.color.SevereThinness
            }


            bmi in 16.0..17.0-> {
                resultText = "Moderate Thinness"
                color = R.color.ModerateThinness
            }
            bmi in 17.0..18.5 ->{
                resultText = "Mild Thinness"
                color = R.color.MildThinness
            }
            bmi in 18.5..25.99 ->{
                resultText = "Normal"
                color = R.color.Normal
            }
            bmi in 25.99..30.00 -> {
                resultText = "Over weight"
                color = R.color.Overweight
            }
            bmi in 30.0..35.0 ->{
                resultText = "Obese Class 1"
                color = R.color.ObeseClass1
            }
            bmi in 35.0..40.0 ->{
                resultText = "Obese Class 2"
                color = R.color.ObeseClass2
            }
            bmi > 40.0 ->{
                resultText = "Obese Class 3"
                color = R.color.ObeseClass3
            }

        }

        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text = resultText
    }

    private fun agecal(age:Float,Bil:Float){
        when{
            (age in 2.0..7.0)&&(Bil in 16.0..17.0) ->{
                Toast.makeText(this,"The child is healthy",Toast.LENGTH_LONG).show()
            }
            (age in 13.0..18.0)&&(Bil in 21.9..24.6) ->{
                Toast.makeText(this,"The Teenager is healthy",Toast.LENGTH_LONG).show()
            }
            (age > 18.0)&&(Bil in 24.9..30.00) ->{
                Toast.makeText(this,"The Adult is healthy",Toast.LENGTH_LONG).show()
            }else -> {Toast.makeText(this,"You should work on health", Toast.LENGTH_LONG).show()}
        }
    }
}