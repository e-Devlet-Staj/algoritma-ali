package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numbers
        tvOne.setOnClickListener { appendOnExpression("1") }
        tvTwo.setOnClickListener { appendOnExpression("2") }
        tvThree.setOnClickListener { appendOnExpression("3") }
        tvFour.setOnClickListener { appendOnExpression("4") }
        tvFive.setOnClickListener { appendOnExpression("5") }
        tvSix.setOnClickListener { appendOnExpression("6") }
        tvSeven.setOnClickListener { appendOnExpression("7") }
        tvEight.setOnClickListener { appendOnExpression("8") }
        tvNine.setOnClickListener { appendOnExpression("9") }
        tvZero.setOnClickListener { appendOnExpression("0") }
        tvDot.setOnClickListener { appendOnExpression(".") }
        tvPi.setOnClickListener{appendOnExpression("3.141592") }
        tvEuler.setOnClickListener{appendOnExpression("2.718281")}

        //Operators
        tvPlus.setOnClickListener { appendOnExpression("+") }
        tvMinus.setOnClickListener { appendOnExpression("-") }
        tvMul.setOnClickListener { appendOnExpression("*") }
        tvDivide.setOnClickListener { appendOnExpression("/") }
        tvOpen.setOnClickListener { appendOnExpression("(") }
        tvClose.setOnClickListener { appendOnExpression(")") }
        tvPercentage.setOnClickListener {
            appendOnExpression("/100")
            tvEquals.performClick()
        }
        tvEquals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble())
                    tvResult.text = longResult.toString()
                else
                    tvResult.text = result.toString()
                if(tvResult.text.isNotEmpty()){
                    history.append(tvExpression.text)
                    history.append("\n")
                    history.append("=")
                    history.append(tvResult.text)
                    history.append("\n")
                    tvExpression.text = ""
                    tvExpression.text = tvResult.text
                    tvResult.text = ""
                }
            }catch (e:Exception){
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT ).show()
            }
        }
        tvFactorial.setOnClickListener{
            tvResult.text = factorialString(tvExpression.text.toString()).toString()
            appendOnExpression("!")}



        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }
        tvBack.setOnClickListener {
            val string = tvExpression.text.toString()
            if(string.isNotEmpty()){
                tvExpression.text = string.substring(0,string.length-1)
            }
            tvResult.text = ""
        }
        tvExpand.setOnClickListener{
            showHide(tvRow1)
            showHide(tvRow2)
            showHide(tvSqrt)
            showHide(tvFactorial)
            showHide(tvOneOverX)
            showHide(tvPi)
            showHide(tvEuler)
        }
    }

    fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.GONE
        } else{
            View.VISIBLE
        }
    }

    fun factorialString(numString: String):Int{
        var number = numString.toInt()
        var hold : Int = 1
        for (i in 1..number) {
            // hold = hold * i
            hold = i.times(hold)
        }
        return hold
    }

    fun appendOnExpression(string: String) {
        //to avoid arbitrary operator usage such as "--" , "++" , ".." , "+." , ...
        if((tvExpression.text.endsWith("+") || tvExpression.text.endsWith("-") ||
                    tvExpression.text.endsWith("*") || tvExpression.text.endsWith("/") ||
                    tvExpression.text.isEmpty() || tvExpression.text.endsWith("."))
            &&(string == "+" || string == "-" || string == "/" || string == "*" || string == ".")){
            return
        }
        if(tvResult.text.isNotEmpty()){
            history.append(tvExpression.text)
            history.append("\n")
            tvExpression.text = ""
            tvExpression.text = tvResult.text
            tvResult.text = ""
        }
        tvExpression.append(string)
    }
}