package com.example.team31.authentication

import junit.framework.TestCase
import org.junit.Test

class registration_name_fragmentTest : TestCase() {

    @Test
    fun testCheckPassword() {
        val input1 = "hhhhhh"
        val input2 = "tryggest"
        val input3 = "hei"

        val output1 = checkPassword(input1)
        val output2 = checkPassword(input2)
        val output3 = checkPassword(input3)

        assertFalse(output1)
        assertTrue(output2)
        assertFalse(output3)
    }

    private fun checkPassword(input: String): Boolean {
        for (i in 0..input.length-1) {
            if(input[i] != input[0]){
                if(input.length > 5){
                    return true
                }
            }
        }
        return false
    }
}