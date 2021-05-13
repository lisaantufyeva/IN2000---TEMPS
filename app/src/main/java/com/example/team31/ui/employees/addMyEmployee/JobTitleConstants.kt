package com.example.team31.ui.employees.addMyEmployee

object JobTitleConstants {

    const val JOB_TITLE = "Rolle"


    fun rolleType(): ArrayList<String>{
        val list = ArrayList<String>()
        list.add("Kokk")
        list.add("Servirør")
        list.add("Bartender")
        list.add("Vaktsjef")

        return list
    }
}