package com.example.calculator

class Operators {
    fun add(num1: Int, num2: Int): Int{
        return (num1+num2)
    }
    fun divide(num1: Int, num2: Int): Int{
        if(num2 == 0){
            println("Division by zero is undefined")
            return 0
        }else {
            return (num1 / num2)
        }
    }

    fun multiply(num1: Int, num2: Int): Int{
        return (num1.times(num2))
    }
    fun subtract(num1: Int, num2: Int): Int{
        return (num1-num2)
    }
}