package com.example.forvagoapp

import java.io.Serializable

data class Hotel(
    val id: Int,
    val nombre: String,
    val imagen: String,
    val descripcion: String,
    val ubicacion: String,
    val estrellas: Int,
    val contacto: String,
    val tipoAlojamiento: String,
    val video: String,
    val servicios: List<String>,
    val tarifa: String,
    val web: String
): Serializable