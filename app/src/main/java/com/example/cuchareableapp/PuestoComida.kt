package com.example.cuchareableapp

import com.google.android.gms.maps.model.LatLng

data class PuestoComida(
    val nombre: String,
    val descripcion: String,
    val tipo: String,
    val imagenUrl: String,
    val ubicacion: LatLng
)