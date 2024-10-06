package com.example.forvagoapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import java.io.IOException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.squareup.picasso.Picasso


class DetalleDeHotel : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_de_hotel)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recibir el ID del com.example.forvagoapp.Hotel seleccionado en la pantalla del listado de Hoteles
        val hotelId = intent.getIntExtra("HOTEL_ID", -1)

        // Leer la lista de hoteles desde el JSON
        val hoteles = getHotelsFromJson()

        // Buscar el hotel por el ID
        val hotelSeleccionado = hoteles.find { it.id == hotelId }

        // Asignar datos a la pantalla
        hotelSeleccionado?.let {
            // Textos
            findViewById<TextView>(R.id.dhHotelName).text = it.nombre
            findViewById<TextView>(R.id.dhHotelDescription).text = it.descripcion
            findViewById<TextView>(R.id.dhHotelUbicacion).text = it.ubicacion
            findViewById<TextView>(R.id.dhHotelEstrellas).text = it.estrellas.toString()
            findViewById<TextView>(R.id.dhHotelContacto).text = it.contacto
            findViewById<TextView>(R.id.dhHotelTipoAlojamiento).text = it.tipoAlojamiento
            // Se agrega en un unico string el arreglo de servicios
            val serviciosText = it.servicios.joinToString(separator = ", ")
            findViewById<TextView>(R.id.dhHotelServicios).text = serviciosText
            findViewById<TextView>(R.id.dhHotelTarifa).text = it.tarifa

            // Imagen
            val imageViewHotel = findViewById<ImageView>(R.id.dhHotelImage)
            val hotelImageUrl = it.imagen
            Picasso.get().load(hotelImageUrl).into(imageViewHotel)

            // Video - Documentación recomienda usar un Obsevable para configurar las opciones del reproductor
            val youtubePlayerView = findViewById<com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView>(R.id.dhVideoHotel)
            lifecycle.addObserver(youtubePlayerView)
            youtubePlayerView.addYouTubePlayerListener(object: YouTubePlayerListener {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.cueVideo(it.video, 0F)
                }
                override fun onApiChange(youTubePlayer: YouTubePlayer) {}
                override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {}
                override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {}
                override fun onPlaybackQualityChange(youTubePlayer: YouTubePlayer, playbackQuality: PlayerConstants.PlaybackQuality) {}
                override fun onPlaybackRateChange(youTubePlayer: YouTubePlayer, playbackRate: PlayerConstants.PlaybackRate) {}
                override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {}
                override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {}
                override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {}
                override fun onVideoLoadedFraction(youTubePlayer: YouTubePlayer, loadedFraction: Float) {}
            })


            // Asignar pagina web
            val paginaWebHotel = it.web
            val abrirPaginaWeb = findViewById<Button>(R.id.dhHotelWebButton)

            abrirPaginaWeb.setOnClickListener {
                if (paginaWebHotel.isNotEmpty()) {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(paginaWebHotel)
                    startActivity(intent)
                }
            }
        }

        // Asignar pagina Listado de Hoteles
        val abrirListadoHoteles = findViewById<Button>(R.id.dhVolverListado)
        abrirListadoHoteles.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        drawerLayout = findViewById(R.id.drawer_layout)

        findViewById<View>(R.id.menu_button).setOnClickListener {
            toggleMenu()
        }

        loadMenuFragment()
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