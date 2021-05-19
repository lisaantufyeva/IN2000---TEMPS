package com.example.team31.ui.employees.add_my_employee

object JobTitleConstants {

    const val JOB_TITLE = "Rolle"


    fun rolleType(): ArrayList<String>{
        val list = ArrayList<String>()
        list.add("Kokk")
        list.add("Servitør")
        list.add("Bartender")
        list.add("Vaktsjef")

        return list
    }
}