package com.example.team31.ui.overview.week_overview

import com.example.team31.Bruker
import junit.framework.TestCase

import org.junit.Test


class OverviewViewModelKtTest: TestCase() {

    lateinit var testForecast: RefinedForecast
    lateinit var testUser: Bruker

    @Test
    fun testCheckStaffingDemand() {
        testForecast = RefinedForecast("", "16","","")
        //testForecast.temp = 16.toString() //changed RefinedForecast temp to var

        testUser = Bruker()
        testUser.normalBemanning = 10.toString()
        testUser.maxBemanning = 20.toString()
        testUser.triggerTemp = 10.toString()

        var expected = 3.0
        var delta = 0.0
        var output = checkStaffingDemand(testForecast, testUser).toDouble()
        assertEquals(expected, output, delta)

        //Test at det blir en ansatt ved lik trigger temp som meldt temp
        testForecast = RefinedForecast("", "20","","")
        //testForecast.temp = 16.toString() //changed RefinedForecast temp to var

        testUser = Bruker()
        testUser.normalBemanning = 10.toString()
        testUser.maxBemanning = 20.toString()
        testUser.triggerTemp = 20.toString()

        expected = 1.0
        delta = 0.0
        output = checkStaffingDemand(testForecast, testUser).toDouble()
        assertEquals(expected, output, delta)

        //Test for triggertemp storre enn maxtemp
        testForecast = RefinedForecast("", "40","","")
        //testForecast.temp = 16.toString() //changed RefinedForecast temp to var

        testUser = Bruker()
        testUser.normalBemanning = 10.toString()
        testUser.maxBemanning = 20.toString()
        testUser.triggerTemp = 35.toString()

        expected = 7.0
        delta = 0.0
        output = checkStaffingDemand(testForecast, testUser).toDouble()
        assertEquals(expected, output, delta)
    }

    @Test
    fun testCheckLowStaffing() {
        testForecast = RefinedForecast("","16","","0.2")

        testUser = Bruker()
        testUser.triggerTemp = 15.toString()
        testUser.nedbor = true

        var output = checkLowStaffing(testForecast, testUser.triggerTemp, testUser.nedbor)

        assertTrue(output)

        //Tester at ved mer nedbor enn 0.5mm saa skal det ikke gis varsel paa tross av lavere trigger temp enn meldt temp
        testForecast = RefinedForecast("","16","","0.6")
        output = checkLowStaffing(testForecast, testUser.triggerTemp, testUser.nedbor)

        assertFalse(output)

    }
}

/*
fun checkLowStaffing(forecast: RefinedForecast, max: String?, precipitation: Boolean):Boolean{
    println("check low staffing:" +  forecast.temp.toDouble())
    return if(precipitation){
        (forecast.temp.toDouble() > max!!.toDouble() && forecast.precipitation!!.toDouble() <= 0.5)
    }else{
        (forecast.temp.toDouble() > max!!.toDouble())
    }

}
 */

/*
    @Test
    fun testCheckTesting() {

        val x = 20
        val y = 10

        val expected = 10.0
        val output = checkTesting(x,y).toDouble()
        val delta = 0.0
        assertEquals(expected,output,delta)
    }
}
*/


/*fun checkStaffingDemand(forecast: RefinedForecast, user: Bruker): Int{
    var manko = 0.0
    val maxTemp = 30 //assumption
    if (forecast.temp.toDouble() < maxTemp.toDouble()) {
        val x = user.maxBemanning!!.toDouble() - user.normalBemanning!!.toDouble()
        val y = forecast.temp.toDouble() - user.triggerTemp!!.toDouble()
        val z = maxTemp.toDouble() - user.triggerTemp!!.toDouble()
        val xy = x*y
        manko = xy / z
        if (0.0< manko && manko <1.0){
            manko = 1.0
        }
    } else {
        manko = user.maxBemanning!!.toDouble() - user.normalBemanning!!.toDouble()
    }
    return manko.toInt()
}*/