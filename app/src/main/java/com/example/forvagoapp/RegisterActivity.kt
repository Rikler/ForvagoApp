package com.example.forvagoapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout

class RegisterActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        drawerLayout = findViewById(R.id.drawer_layout)

        findViewById<View>(R.id.menu_button).setOnClickListener {
            toggleMenu()
        }

        loadMenuFragment()

        val saveButton = findViewById<Button>(R.id.registerButtonSave)
        saveButton.setOnClickListener {
            saveProfileData() // Llamar al método que guarda los datos
        }
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

    private fun saveProfileData() {
        // Capturar los campos de los inputs
        val name = findViewById<EditText>(R.id.registerInputName).text.toString()
        val lastName = findViewById<EditText>(R.id.registerInputLastname).text.toString()
        val age = findViewById<EditText>(R.id.registerInputAge).text.toString()
        val email = findViewById<EditText>(R.id.registerInputEmail).text.toString()
        val favorites = findViewById<EditText>(R.id.registerInputFavorite).text.toString()
        val image = findViewById<EditText>(R.id.registerInputImage).text.toString()
        val languages = findViewById<EditText>(R.id.registerInputLanguages).text.toString()
        val special = findViewById<EditText>(R.id.registerInputSpecial).text.toString()

        // Guardar los datos en SharedPreferences
        val sharedPreferences = getSharedPreferences("profileData", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("name", name)
        editor.putString("lastName", lastName)
        editor.putString("age", age)
        editor.putString("email", email)
        editor.putString("favorites", favorites)
        editor.putString("image", image)
        editor.putString("languages", languages)
        editor.putString("special", special)
        editor.apply()

        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }
}