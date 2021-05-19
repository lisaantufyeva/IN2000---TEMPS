package com.example.team31.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
//import com.bumptech.glide.Glide
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.team31.*
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

//import com.example.team31.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment() {

    /*
    companion object {
        fun newInstance() = OverviewFragment()
    }*/

    //private var _binding: ProfileFragmentBinding? = null

    private lateinit var profileViewModel: ProfileViewModel
    private var user: Bruker = Bruker()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val userId = (activity as AdminActivity?)!!.getUserId()
        profileViewModel =
                ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.profile_fragment, container, false)

        GlobalScope.launch(Dispatchers.IO) {
            val mainUser = profileViewModel.getUser(userId)
            Log.i("CO-return: ", mainUser.toString())
            //delay(30)
            withContext(Dispatchers.Main){
                user = mainUser
                Log.i("Coroutines: ", user.toString())
                Log.i("message",user.toString())

                val navn: TextView = root.findViewById(R.id.navn)
                navn.text = "Navn: " + user.navn

                val posisjon: TextView = root.findViewById(R.id.position)
                posisjon.text = "Posisjon: " + user.stedNavn

                val mail: TextView = root.findViewById(R.id.mail)
                mail.text = "Mail: " + user.email

                val normal = root.findViewById<TextView>(R.id.normal_bemanning)
                normal.text = "Normal bemanning: " + user.normalBemanning

                val max = root.findViewById<TextView>(R.id.max_bemanning)
                max.text = "Maksimal bemanning: " + user.maxBemanning

                val trigger = root.findViewById<TextView>(R.id.trigger)
                trigger.text = "Temperatur over: " + user.triggerTemp

                val nedbor = root.findViewById<TextView>(R.id.nedbor)

                if(user.nedbor){
                    nedbor.text = "Nedbør: Ja"
                }
                else{
                    nedbor.text = "Nedbør: Nei"
                }

                val aapning = root.findViewById<TextView>(R.id.aapning)
                aapning.text = "Åpningstider: fra " + user.aapenFra + " til " + user.aapenTil

                Log.i("Varsel: ", user.varselListe.toString())

                (activity as AdminActivity?)!!.updateUser(user)
            }
        }






        val edit_name = root.findViewById<ImageButton>(R.id.edit_name)
        edit_name.setOnClickListener{
            Navigation.findNavController(root).navigate(R.id.action_navigation_profile_to_edit_name)
        }

        val edit_pos = root.findViewById<ImageButton>(R.id.edit_pos)
        edit_pos.setOnClickListener{
            Navigation.findNavController(root).navigate(R.id.action_navigation_profile_to_edit_pos)
        }

        val edit_work = root.findViewById<ImageButton>(R.id.edit_work)
        edit_work.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_navigation_profile_to_edit_work)
        }

        val edit_weather = root.findViewById<ImageButton>(R.id.edit_weather)
        edit_weather.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_navigation_profile_to_edit_weather)
        }

        val edit_opening = root.findViewById<ImageButton>(R.id.edit_opening)
        edit_opening.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_navigation_profile_to_edit_opening)
        }

        val edit_password = root.findViewById<Button>(R.id.edit_passord)
        edit_password.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_navigation_profile_to_edit_password)
        }

        val logOut = root.findViewById<Button>(R.id.logg_ut)
        logOut.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }

        //val user = profileViewModel.getUser("-MZsDmQd0-ZhRrUNdbzn")

        /*
        val bilde  = root.findViewById<CircleImageView>(R.id.image)
        Glide.with(this).load(user.bilde).into(bilde)
         */
        //val textView: TextView = root.findViewById(R.id.text_profile)
        //profileViewModel.text.observe(viewLifecycleOwner, Observer {
        //  textView.text = it
        //})
        return root
    }
    /*
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)

    }*/

}