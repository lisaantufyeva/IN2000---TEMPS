package com.example.team31

import java.io.Serializable

data class Bruker(val id: String?, val email: String?, val passord: String?, val bilde: String?, val navn:String?, val latlng:String?, val stedNavn:String?):Serializable{
    constructor() : this("1", "e", "1", "1", "e", "e", "e")
}


