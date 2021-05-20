package com.example.team31.ui.employees.my_employees

import java.io.Serializable

data class Ansatt(
        var ansattId:String?,
        val navn:String?,
        val email:String?,
        val passord: String?,
        val rolle:String?,
        val adminId: String?)
    :Serializable {
    constructor() : this("","1","e","e","e", "e")
}
