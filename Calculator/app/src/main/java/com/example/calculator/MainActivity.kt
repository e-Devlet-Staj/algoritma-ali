package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    lateinit var etUser: EditText
    lateinit var etPwd: EditText
    lateinit var etUserName: EditText
    lateinit var  realm: Realm
    val historyList = ArrayList<String>()
    var expression = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Realm
        //realm = Realm.getDefaultInstance()
        /* Realm.init(this)
         val config = RealmConfiguration.Builder()
             .name("History.realm").build()
         val realm = Realm.getInstance(config)

         realm.beginTransaction()
         val history = realm.createObject(History::class.java, 1)
         history.history = "dfzf"
         realm.commitTransaction()*/



        //adapter
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        var adapter =  RecyclerAdapter(historyList)
        recyclerView.adapter=adapter

        //Numbers
        tvZero.setOnClickListener{
            tvExpression.append("0")
            appendOnPreExpression("0")
        }
        tvOne.setOnClickListener{
            tvExpression.append("1")
            appendOnPreExpression("1")
        }
        tvTwo.setOnClickListener{
            tvExpression.append("2")
            appendOnPreExpression("2")
        }
        tvThree.setOnClickListener{
            tvExpression.append("3")
            appendOnPreExpression("3")
        }
        tvFour.setOnClickListener{
            tvExpression.append("4")
            appendOnPreExpression("4")
        }
        tvFive.setOnClickListener{
            tvExpression.append("5")
            appendOnPreExpression("5")
        }
        tvSix.setOnClickListener{
            tvExpression.append("6")
            appendOnPreExpression("6")
        }
        tvSeven.setOnClickListener{
            tvExpression.append("7")
            appendOnPreExpression("7")
        }
        tvEight.setOnClickListener{
            tvExpression.append("8")
            appendOnPreExpression("8")
        }
        tvNine.setOnClickListener{
            tvExpression.append("9")
            appendOnPreExpression("9")
        }
        tvPi.setOnClickListener{
            tvExpression.append("PI")
            appendOnPreExpression("3.14159")
        }
        tvEuler.setOnClickListener{
            tvExpression.append("e")
            appendOnPreExpression("2.71828")
        }


        //Operators
        tvMinus.setOnClickListener{
            if(canAddOperator(this.expression)){
                tvExpression.append("-")
                appendOnPreExpression("-")
            }
        }
        tvPlus.setOnClickListener{
            if(canAddOperator(this.expression)){
                tvExpression.append("+")
                appendOnPreExpression("+")
            }

        }
        tvDivide.setOnClickListener{
            if(canAddOperator(this.expression)){
                tvExpression.append("/")
                appendOnPreExpression("/")
            }
        }
        tvMul.setOnClickListener{
            if(canAddOperator(this.expression)){
                tvExpression.append("x")
                appendOnPreExpression("*")
            }
        }
        tvPercentage.setOnClickListener{
            if(canAddOperator(this.expression)){
                tvExpression.append("%")
                appendOnPreExpression("/100")
                tvEquals.performClick()
            }
        }
        tvEquals.setOnClickListener {
            if(canAddOperator(this.expression)){
                try {
                    updateHistoryList()
                    tvExpression.text = tvResult.text
                    tvResult.text  =""
                    this.expression = tvExpression.text.toString()
                    val layoutManager = LinearLayoutManager(this)
                    recyclerView.layoutManager = layoutManager
                    var adapter =  RecyclerAdapter(historyList)
                    recyclerView.adapter=adapter
                }catch (e:Exception){
                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT ).show()
                }
            }
        }
        tvFactorial.setOnClickListener{
            try {
                if (canAddOperator(this.expression)) {
                    if(!expression.contains(".")){
                        tvExpression.append("!")
                        expression = factorial(expression.toInt()).toString()
                        tryToEvaluate(this.expression)
                        tvEquals.performClick()
                    }else{
                        Toast.makeText(this,"Factorial defined on integers", Toast.LENGTH_SHORT ).show()
                    }
                }
            }catch (e: Exception){
                println(e.message)}
        }
        tvDot.setOnClickListener {
            if(canAddOperator(this.expression)) {
                tvExpression.append(".")
                appendOnPreExpression(".")
            }
        }
        tvOpen.setOnClickListener {
            if(canAddOperator(this.expression)) {
                tvExpression.append("(")
                appendOnPreExpression("(")
            }
        }
        tvClose.setOnClickListener {
            if(canAddOperator(this.expression)) {
                tvExpression.append(")")
                appendOnPreExpression(")")
            }
        }
        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
            this.expression=""
        }
        tvBack.setOnClickListener {
            val string = tvExpression.text.toString()
            if(string.isNotEmpty()){
                tvExpression.text = string.substring(0,string.length-1)
                expression=expression.substring(0, expression.length-1)
                tryToEvaluate(expression)
            }
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

    //to avoid arbitrary operator ("--" , "+x" , ".." , "+." , ...)
    fun canAddOperator(tvExpression: String): Boolean{
        if(tvExpression.endsWith("+") || tvExpression.endsWith("-") ||
            tvExpression.endsWith("*") || tvExpression.endsWith("/") || tvExpression.endsWith("x") ||
            tvExpression.isEmpty() || tvExpression.endsWith(".")){
            return false
        }else{
            return true
        }
    }
    fun tryToEvaluate(expression: String){
        try {
            var expressionObject = ExpressionBuilder(expression).build()
            val result = expressionObject.evaluate()
            val longResult = result.toLong()
            if(result == longResult.toDouble()){
                tvResult.text = longResult.toString()}
            else{
                tvResult.text = result.toString()
            }
            tvResult.visibility = VISIBLE
        }catch (e: Exception) {tvResult.visibility = INVISIBLE}
    }
    private fun previousNumberString(expression: String): String{
        var beginningIndex = 0
        var temp = ' '
        for(x in expression.length-1..0){

            if(expression.get(x)=='+' || expression.get(x)=='-' ||
                expression.get(x)=='*' || expression.get(x)=='/'){
                val substring = expression.substring(x+1)
                println(substring)
                return substring
            }
        }

        return ""

    }
    fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.GONE
        } else{
            View.VISIBLE
        }
    }
    fun factorial(number: Int):Int{
        var hold : Int = 1
        for (i in 1..number) {
            // hold = hold * i
            hold *= i
        }
        return hold
    }
    fun appendOnPreExpression(newString: String){
        this.expression += newString
        tryToEvaluate(this.expression)
    }
    fun updateHistoryList(){
        historyList.add(tvExpression.text.toString())
        historyList.add("="+tvResult.text.toString())
    }
}

