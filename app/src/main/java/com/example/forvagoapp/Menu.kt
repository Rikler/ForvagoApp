package com.example.forvagoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.Picasso

class Menu : Fragment() {

    private lateinit var textViewProfileName: TextView
    private lateinit var imageViewProfileImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        // Inicializa el TextView
        textViewProfileName = view.findViewById(R.id.username) // Cambia esto por el ID de tu TextView
        imageViewProfileImage = view.findViewById(R.id.account_image)

        // Cargar el nombre del usuario desde SharedPreferences
        loadUserName()

        val closeMenu = view.findViewById<ImageView>(R.id.close_icon)
        closeMenu.setOnClickListener {
            val activity = activity
            if (activity is MainActivity) {
                activity.toggleMenu()
            } else if (activity is DetalleDeHotel) {
                activity.toggleMenu()
            } else if (activity is ProfileActivity) {
                activity.toggleMenu()
            } else if (activity is MainPage) {
                activity.toggleMenu()
            } else if (activity is RegisterActivity) {
                activity.toggleMenu()
            }
        }

        val btnOption1 = view.findViewById<LinearLayout>(R.id.btn_option_1)
        btnOption1.setOnClickListener {
            val intent = Intent(activity, MainPage::class.java)
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

    private fun loadUserName() {
        // Cargar el nombre del usuario desde SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("profileData", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "Anónimo") // "Anónimo" como valor por defecto
        val lastname = sharedPreferences.getString("lastName", "") // "Anónimo" como valor por defecto
        val image = sharedPreferences.getString("image", null)

        // Mostrar el nombre en el TextView
        textViewProfileName.text = name + " " + lastname
        // Picasso.get().load(image).into(imageViewProfileImage)
        if(image != null) {
            Glide.with(this).load(image).apply(RequestOptions.circleCropTransform()).into(imageViewProfileImage)
        }
    }
}