package com.example.forvagoapp


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.ImageView
import android.widget.Button

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // obtain data at XML
        val ivLogo = findViewById<ImageView>(R.id.ivLogo)
        val ivMenu = findViewById<ImageView>(R.id.ivMenu)
        val tvNombreUsuario = findViewById<TextView>(R.id.tvNombreUsuario)
        val ivImagenPerfil = findViewById<ImageView>(R.id.ivImagenPerfil)
        val tvInformacionUsuario = findViewById<TextView>(R.id.tvInformacionUsuario)
        val tvNombre = findViewById<TextView>(R.id.tvNombre)
        val tvCorreo = findViewById<TextView>(R.id.tvCorreo)
        val tvTelefono = findViewById<TextView>(R.id.tvTelefono)
        val tvFechaNacimiento = findViewById<TextView>(R.id.tvFechaNacimiento)
        val tvPreferenciasViaje = findViewById<TextView>(R.id.tvPreferenciasViaje)
        val tvDestinosFavoritos = findViewById<TextView>(R.id.tvDestinosFavoritos)
        val tvIdiomas = findViewById<TextView>(R.id.tvIdiomas)
        val tvInteresesEspeciales = findViewById<TextView>(R.id.tvInteresesEspeciales)
        val btnVolverInicio = findViewById<Button>(R.id.btnVolverInicio)

        // Simulación de datos del usuario (estos serían obtenidos de una base de datos o API)
        val nombreUsuario = "Juan Pablo Gutiérrez"
        val correoUsuario = "juanpablo@example.com"
        val telefonoUsuario = "+57 123 456 7890"
        val fechaNacimientoUsuario = "15 de enero de 1990"
        val destinosFavoritosUsuario = "París, Londres, Nueva York"
        val idiomasUsuario = "Español, Inglés, Francés"
        val interesesEspecialesUsuario = "Fotografía, Comida Gourmet, Trekking"

        // Asignar los valores a las vistas
        tvNombreUsuario.text = nombreUsuario
        tvNombre.text = "Nombre: $nombreUsuario"
        tvCorreo.text = "Correo: $correoUsuario"
        tvTelefono.text = "Teléfono: $telefonoUsuario"
        tvFechaNacimiento.text = "Fecha de nacimiento: $fechaNacimientoUsuario"
        tvDestinosFavoritos.text = "Destinos favoritos: $destinosFavoritosUsuario"
        tvIdiomas.text = "Idiomas: $idiomasUsuario"
        tvInteresesEspeciales.text = "Intereses especiales: $interesesEspecialesUsuario"

        //  botón Volver al Inicio
        btnVolverInicio.setOnClickListener {

            finish() // Esto cierra la actividad actual y vuelve a la anterior
        }
    }
}
