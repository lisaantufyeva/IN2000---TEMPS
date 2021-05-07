package com.example.team31

import java.io.Serializable


data class Bruker(
    var id: String?,
    var email: String?,
    var passord: String?,
    var bilde: String?,
    var navn: String?,
    var latitude: String?,
    var longitude: String?,
    var stedNavn: String?,
    var normalBemanning: String?,
    var maxBemanning: String?,
    var triggerTemp: String?,
    var nedbor: Boolean,
    var aapenFra: String?,
    var aapenTil: String?,
    var varselListe: MutableList<Varsel>


):Serializable{
    constructor() : this("1", "e", "1", "1", "e", "e", "e", "e", "e", "e", "e", false, "e", "e", mutableListOf<Varsel>())
}


