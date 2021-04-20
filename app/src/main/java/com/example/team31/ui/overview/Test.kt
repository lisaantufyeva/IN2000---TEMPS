package com.example.team31.ui.overview

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date


data class RefinedTest(val date: Date, val temp: String)

fun main() {
    val test0 = Test("Apr 17 16:00:00 GMT+02:00 2021", "6.8")
    val test1 = Test("Apr 17 16:00:00 GMT+02:00 2021", "6.8")
    val test2 = Test("Apr 17 16:00:00 GMT+02:00 2021", "5.1")
    val test3 = Test("Apr 17 16:00:00 GMT+02:00 2021", "6.8")

    val ilist = listOf(test0, test1, test2, test3)

    val formatedList = ilist.map { test -> RefinedTest(parseDate1(test.time)!!, test.temp) }

    val filteredList = formatedList.filter { it.date.hours == 12 }

    val first = formatedList.first()

    val result =
            if (first.date.hours > 12)
                listOf(formatedList.first()) + filteredList
            else filteredList

    println(result)
}

@SuppressLint("SimpleDateFormat")
fun formatDate1(time: String): String {
    val parser = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy'")
    val formatter = SimpleDateFormat("dd. MMMM")

    return formatter.format(parser.parse(time))
}

@SuppressLint("SimpleDateFormat")
fun parseDate1(time: String): Date? {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    return parser.parse(time)
}


fun filterList(list: MutableList<Test>): MutableList<Test> {

    val filteredList = list.distinctBy { it -> it.time }

    return filteredList.toMutableList()

}

data class Test(val time: String, val temp: String)
