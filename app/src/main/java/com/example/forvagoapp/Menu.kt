package com.example.forvagoapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

class Menu : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        val closeMenu = view.findViewById<ImageView>(R.id.close_icon)
        closeMenu.setOnClickListener {
            val activity = activity
            if (activity is MainActivity) {
                activity.toggleMenu()
            } else if (activity is DetalleDeHotel) {
                activity.toggleMenu()
            } else if (activity is ProfileActivity) {
                activity.toggleMenu()
            }
        }

        val btnOption1 = view.findViewById<LinearLayout>(R.id.btn_option_1)
        btnOption1.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        val btnOption2 = view.findViewById<LinearLayout>(R.id.btn_option_2)
        btnOption2.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        val btnOption3 = view.findViewById<LinearLayout>(R.id.btn_option_3)
        btnOption3.setOnClickListener {
            val intent = Intent(activity, ProfileActivity::class.java)
            startActivity(intent)
        }

        return view
    }

}