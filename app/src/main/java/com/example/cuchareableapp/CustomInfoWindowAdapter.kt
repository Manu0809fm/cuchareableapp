package com.example.cuchareableapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class CustomInfoWindowAdapter(private val context: Context) : GoogleMap.InfoWindowAdapter {

    override fun getInfoWindow(marker: Marker): View? = null

    override fun getInfoContents(marker: Marker): View {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null)

        val nombre = view.findViewById<TextView>(R.id.tvNombre)
        val descripcion = view.findViewById<TextView>(R.id.tvDescripcion)
        val imagen = view.findViewById<ImageView>(R.id.ivFoto)

        nombre.text = marker.title
        descripcion.text = marker.snippet

        val url = marker.tag as? String
        Glide.with(context)
            .load(url)
            .placeholder(android.R.drawable.ic_menu_report_image) // ícono genérico de Android
            .into(imagen)


        return view
    }
}