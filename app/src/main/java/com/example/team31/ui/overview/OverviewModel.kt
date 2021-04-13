package com.example.team31.ui.overview

import android.os.Handler
import java.util.*

class OverviewModel: OverviewContract.Model {

    private val arrayList =
            listOf(
                    "DSA Self Paced: Master the basics of Data Structures and Algorithms to solve complex problems efficiently. ",
                    "Placement 100: This course will guide you for placement with theory,lecture videos, weekly assignments " +
                            "contests and doubt assistance.",
                    "Amazon SDE Test Series: Test your skill & give the final touch to your preparation before applying for " +
                            "product based against like Amazon, Microsoft, etc.",
                    "Complete Interview Preparation: Cover all the important concepts and topics required for the interviews. " +
                            "Get placement ready before the interviews begin",
                    "Low Level Design for SDE 1 Interview: Learn Object-oriented Analysis and Design to prepare for " +
                            "SDE 1 Interviews in top companies"
            )
    /*
    override fun getNextCourse(OnFinishedListener: OverviewContract.Model.OnFinishedListener?) {
        Handler().postDelayed({ OnFinishedListener!!.onFinished(getRandomString) }, 1200)
    }*/

    private val getRandomString: String
        get() {
            val random = Random()
            val index = random.nextInt(arrayList.size)
            return arrayList[index]
        }

}