package com.example.team31

import java.io.Serializable

data class Ansatt(var ansattId: String?, val email: String?, val passord: String?, val navn: String?, val rolle: String?, val adminId:String?):Serializable{
    constructor() : this("1", "e", "1", "1", "e", "e")
}