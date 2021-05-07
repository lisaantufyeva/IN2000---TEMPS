package com.example.team31.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.team31.Authentication
import com.example.team31.Bruker
import com.example.team31.R
import com.example.team31.Varsel


class aapningstider_fragment : Fragment() {
    lateinit var user: Bruker

    private val model: AuthenticationViewModel by activityViewModels()

    private val args  by navArgs<aapningstider_fragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user = args.user
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.aapningstider_fragment, container, false)

        val start = root.findViewById<EditText>(R.id.start)
        val slutt = root.findViewById<EditText>(R.id.slutt)

        val knapp = root.findViewById<Button>(R.id.save)
        knapp.setOnClickListener{
            if (start.text.isBlank() || slutt.text.isBlank() ){
                Toast.makeText(activity, "Ugyldig input!", Toast.LENGTH_SHORT).show()
                (activity as Authentication?)!!.hideKeyboard()
                return@setOnClickListener
            }
            user.aapenFra = start.text.toString()
            user.aapenTil = slutt.text.toString()

            val liste = mutableListOf<Varsel>()
            val varsel1 = Varsel("24.04.20",true)
            val varsel2 = Varsel("02.04.20",false)
            val varsel3 = Varsel("25.06.21", false)

            liste.add(varsel1)
            liste.add(varsel2)
            liste.add(varsel3)
            user.varselListe = liste

            model.saveUser(user)

            Navigation.findNavController(root).navigate(R.id.action_aapningstider_fragment_to_login_fragment)
        }
        return root
    }
}