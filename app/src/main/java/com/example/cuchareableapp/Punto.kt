package com.example.cuchareableapp

data class Punto(
    var nombre: String = "",
    var descripcion: String = "",
    var tipo: String = "",
    var latitud: Double = 0.0,
    var longitud: Double = 0.0,
    var imagenUrl: String = ""
)
