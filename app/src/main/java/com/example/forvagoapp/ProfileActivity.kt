package com.example.forvagoapp


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.ImageView
import android.widget.Button
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.Picasso

class ProfileActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        loadProfileData()

        val registerButton = findViewById<Button>(R.id.btnRegister)
        registerButton.setOnClickListener {
            goToRegisterActivity()
        }

        drawerLayout = findViewById(R.id.drawer_layout)

        findViewById<View>(R.id.menu_button).setOnClickListener {
            toggleMenu()
        }

        loadMenuFragment()
    }

    private fun loadProfileData() {
        val sharedPreferences = getSharedPreferences("profileData", MODE_PRIVATE)

        val name = sharedPreferences.getString("name", null)
        Log.d("Nommbre guardado: ", name.toString())
        val lastName = sharedPreferences.getString("lastName", null)
        val age = sharedPreferences.getString("age", null)
        val email = sharedPreferences.getString("email", null)
        val favorites = sharedPreferences.getString("favorites", null)
        val image = sharedPreferences.getString("image", null)
        val languages = sharedPreferences.getString("languages", null)
        val special = sharedPreferences.getString("special", null)

        if (name != null && lastName != null && age != null && email != null && favorites != null && image != null && languages != null && special != null) {
            findViewById<TextView>(R.id.tvNoInfo).visibility = View.GONE
            findViewById<TextView>(R.id.btnRegister).visibility = View.GONE

            findViewById<TextView>(R.id.tvNombreUsuario).apply {
                text = "$name $lastName"
                visibility = View.VISIBLE
            }
            findViewById<TextView>(R.id.tvInformacionUsuario).apply {
                visibility = View.VISIBLE
            }
            findViewById<TextView>(R.id.tvNombre).apply {
                visibility = View.VISIBLE
            }
            findViewById<TextView>(R.id.tvProfileName).apply {
                text = "$name"
                visibility = View.VISIBLE
            }
            findViewById<TextView>(R.id.tvApellido).apply {
                visibility = View.VISIBLE
            }
            findViewById<TextView>(R.id.tvProfileLastname).apply {
                text = "$lastName"
                visibility = View.VISIBLE
            }
            findViewById<TextView>(R.id.tvCorreo).apply {
                visibility = View.VISIBLE
            }
            findViewById<TextView>(R.id.tvProfileEmail).apply {
                text = "$email"
                visibility = View.VISIBLE
            }
            findViewById<TextView>(R.id.tvEdad).apply {
                visibility = View.VISIBLE
            }
            findViewById<TextView>(R.id.tvProfileAge).apply {
                text = "$age"
                visibility = View.VISIBLE
            }
            findViewById<TextView>(R.id.tvFavorites).apply {
                visibility = View.VISIBLE
            }
            findViewById<TextView>(R.id.tvProfileFavorites).apply {
                text = "$favorites"
                visibility = View.VISIBLE
            }
            findViewById<TextView>(R.id.tvLanguages).apply {
                visibility = View.VISIBLE
            }
            findViewById<TextView>(R.id.tvProfileLanguages).apply {
                text = "$languages"
                visibility = View.VISIBLE
            }
            findViewById<TextView>(R.id.tvSpecial).apply {
                visibility = View.VISIBLE
            }
            findViewById<TextView>(R.id.tvProfileSpecial).apply {
                text = "$special"
                visibility = View.VISIBLE
            }
            findViewById<Button>(R.id.btnVolverInicio).apply {
                visibility = View.VISIBLE
            }

            val imageProfile = findViewById<ImageView>(R.id.ivImagenPerfil)
            //Picasso.get().load(image).into(imageProfile)
            Glide.with(this).load(image).apply(RequestOptions.circleCropTransform()).into(imageProfile)

            val homeButton = findViewById<Button>(R.id.btnVolverInicio)
            homeButton.setOnClickListener {
                val intent = Intent(this, MainPage::class.java)
                startActivity(intent)
            }
        }
    }

    private fun goToRegisterActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun toggleMenu() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END)
        } else {
            drawerLayout.openDrawer(GravityCompat.END)
        }
    }

    private fun loadMenuFragment() {
        val menuFragment = Menu()
        supportFragmentManager.beginTransaction()
            .replace(R.id.menu_container, menuFragment)
            .commit()
    }
}
