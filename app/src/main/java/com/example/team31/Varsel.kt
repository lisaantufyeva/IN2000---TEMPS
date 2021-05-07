package com.example.team31

import java.io.Serializable

data class Varsel(var date:String?,var tatt:Boolean): Serializable {
    constructor() : this("1",false)
}
