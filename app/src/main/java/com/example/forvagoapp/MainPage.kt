package com.example.forvagoapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.squareup.picasso.Picasso
import java.io.IOException

class MainPage : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_page)
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

        // Leer la lista de hoteles desde el JSON
        val hoteles = getHotelsFromJson()

        // Buscar el primer hotel por el ID
        val firstHotel = hoteles.find { it.id == 10 }

        firstHotel?.let {
            // Textos
            findViewById<TextView>(R.id.mpHotelDescription1).text = it.descripcion

            // Imagen
            val imageViewHotel = findViewById<ImageView>(R.id.mpHotelImg1)
            val hotelImageUrl = it.imagen
            Picasso.get().load(hotelImageUrl).into(imageViewHotel)

            // Asignar pagina web
            val abrirPaginaWeb = findViewById<TextView>(R.id.mpVerHotel1)

            abrirPaginaWeb.setOnClickListener {
                // Se añade el redireccionamiento a la pantalla Detalle Hotel
                val intent = Intent(this, DetalleDeHotel::class.java)
                // Se envia el ID del hotel seleccionado
                intent.putExtra("HOTEL_ID", 10)
                startActivity(intent)
            }
        }

        // Buscar el segundo hotel por el ID
        val secondHotel = hoteles.find { it.id == 11 }

        secondHotel?.let {
            // Textos
            findViewById<TextView>(R.id.mpHotelDescription2).text = it.descripcion

            // Imagen
            val imageViewHotel = findViewById<ImageView>(R.id.mpHotelImg2)
            val hotelImageUrl = it.imagen
            Picasso.get().load(hotelImageUrl).into(imageViewHotel)

            // Asignar pagina web
            val abrirPaginaWeb = findViewById<TextView>(R.id.mpVerHotel2)

            abrirPaginaWeb.setOnClickListener {
                // Se añade el redireccionamiento a la pantalla Detalle Hotel
                val intent = Intent(this, DetalleDeHotel::class.java)
                // Se envia el ID del hotel seleccionado
                intent.putExtra("HOTEL_ID", 11)
                startActivity(intent)
            }
        }

        // Asignar pagina Listado de Hoteles
        val abrirListadoHoteles = findViewById<Button>(R.id.mpListadoHotelButton)
        abrirListadoHoteles.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    // Función para cargar los hoteles desde el archivo JSON
    private fun getHotelsFromJson(): List<Hotel> {
        val jsonString: String
        try {
            jsonString = assets.open("hoteles.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }

        val listHotelType = object : TypeToken<List<Hotel>>() {}.type
        return Gson().fromJson(jsonString, listHotelType)
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