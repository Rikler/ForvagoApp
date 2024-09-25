package com.example.forvagoapp

import HotelAdapter
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerViewHoteles)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HotelAdapter(loadHotelsFromAssets(), this)

        drawerLayout = findViewById(R.id.drawer_layout)

        findViewById<View>(R.id.btn_menu).setOnClickListener {
            toggleMenu()
        }

        loadMenuFragment()
    }

    private fun loadHotelsFromAssets(): List<Hotel> {
        val json: String = try {
            val inputStream = assets.open("hoteles.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }
        return Gson().fromJson(json, object : TypeToken<List<Hotel>>() {}.type)
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