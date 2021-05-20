package com.example.team31.ui.employees.add_my_employee

object JobTitleConstants {

    //jobTitile constnats

    const val JOB_TITLE = "Rolle"

    //function to return type of jobtitle
    fun rolleType(): ArrayList<String>{
        val list = ArrayList<String>()
        list.add("Kokk")
        list.add("Servitør")
        list.add("Bartender")
        list.add("Vaktsjef")

        return list
    }
}