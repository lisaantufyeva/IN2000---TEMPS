package com.example.team31

object Constants {

    const val ROLLE_TYPE = "Rolle"


    fun rolleType(): ArrayList<String>{
        val list = ArrayList<String>()
        list.add("Kokk")
        list.add("Servirør")
        list.add("Bartender")
        list.add("Vaktsjef")

        return list
    }
}