package com.example.team31

import java.io.Serializable

data class Ansatt(val id: String?, val email: String?, val passord: String?, val navn: String?, val rolle: String?):Serializable{
    constructor() : this("1", "e", "1", "1", "e")
}