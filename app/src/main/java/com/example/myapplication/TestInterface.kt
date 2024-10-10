package com.example.myapplication

interface TestInterface {
    fun job1()
    fun job2() {

    }
}

class TestClass {
    init {
        val interfaceOb = object : TestInterface {
            override fun job1() {
                TODO("Not yet implemented")
            }

        }
    }
}