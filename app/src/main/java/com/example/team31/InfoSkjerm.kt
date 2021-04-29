package com.example.team31


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide.*
import de.hdodenhof.circleimageview.CircleImageView

class InfoSkjerm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_skjerm)

        val bruker1 = intent.extras!!.get("User") as Bruker

        val velkomst = findViewById<TextView>(R.id.info)
        velkomst.text = "Velkommen til TEMPS, " + bruker1.navn + "!"

        val bilde = findViewById<CircleImageView>(R.id.image)
        with(this).load(bruker1.bilde).into(bilde)
    }

}