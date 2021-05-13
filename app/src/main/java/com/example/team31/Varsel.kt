package com.example.team31

import java.io.Serializable

data class Varsel(var varselId: String?, var date:String?,var tatt:Boolean, var ansattId: String?, var adminId:String?): Serializable {
    constructor() : this("1", "1",false, "e","e")
}
