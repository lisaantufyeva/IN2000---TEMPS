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
import com.bumptech.glide.Glide
import com.example.team31.AdminActivity
import com.example.team31.R
import de.hdodenhof.circleimageview.CircleImageView
import org.w3c.dom.Text

//import com.example.team31.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment() {

    /*
    companion object {
        fun newInstance() = OverviewFragment()
    }*/

    //private var _binding: ProfileFragmentBinding? = null

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val user = (activity as AdminActivity?)!!.getUser()
        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.profile_fragment, container, false)

        val navn: TextView = root.findViewById(R.id.navn)
        navn.text = "Navn: " + user.navn

        val posisjon: TextView = root.findViewById(R.id.position)
        posisjon.text = "Posisjon: " + user.stedNavn

        val mail: TextView = root.findViewById(R.id.mail)
        mail.text = "Mail: " + user.email

        val bilde  = root.findViewById<CircleImageView>(R.id.image)
        Glide.with(this).load(user.bilde).into(bilde)

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