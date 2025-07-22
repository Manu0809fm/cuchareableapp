package com.example.cuchareableapp

import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import android.widget.ArrayAdapter
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapa: GoogleMap
    private val database = FirebaseDatabase.getInstance()
    private val referencia = database.getReference("puntos")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val fabAgregarPunto = findViewById<FloatingActionButton>(R.id.fabAgregarPunto)
        fabAgregarPunto.setOnClickListener {
            mostrarDialogAgregarPunto()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mapa = googleMap
        mapa.setInfoWindowAdapter(CustomInfoWindowAdapter(this))

        val puestos = listOf(
            PuestoComida("Churros Don Pepe", "Los mejores churros rellenos con manjar blanco", "Churros", "https://i.imgur.com/4UKfvQz.jpg", LatLng(-9.1290, -78.5235)),
            PuestoComida("Chochos Plaza Norte", "Chocho fresco con cebolla, cancha y zarza", "Chocho", "https://i.imgur.com/7f1xN4F.jpg", LatLng(-9.1282, -78.5257)),
            PuestoComida("El Combinado del Gordo", "Salchipapa + hamburguesa + huevo + chorizo", "Combinado", "https://i.imgur.com/3D9t1MC.jpg", LatLng(-9.1275, -78.5221))
        )

        for (puesto in puestos) {
            val marker = mapa.addMarker(
                MarkerOptions()
                    .position(puesto.ubicacion)
                    .title(puesto.nombre)
                    .snippet(puesto.descripcion)
            )
            marker?.tag = puesto.imagenUrl
        }

        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(puestos[0].ubicacion, 16f))
        cargarMarcadoresDesdeFirebase()
    }

    private fun mostrarDialogAgregarPunto() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_agregar_punto, null)
        val etNombre = dialogView.findViewById<EditText>(R.id.etNombre)
        val etDescripcion = dialogView.findViewById<EditText>(R.id.etDescripcion)
        val spTipo = dialogView.findViewById<Spinner>(R.id.spTipo)

        val tipos = listOf("Chocho", "Combinado", "Desayuno", "Otro")
        spTipo.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tipos)

        AlertDialog.Builder(this)
            .setTitle("Agregar punto")
            .setView(dialogView)
            .setPositiveButton("Guardar") { _, _ ->
                val nombre = etNombre.text.toString()
                val descripcion = etDescripcion.text.toString()
                val tipo = spTipo.selectedItem.toString()
                val posicion = mapa.cameraPosition.target
                guardarPuntoEnFirebase(nombre, descripcion, tipo, posicion)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun guardarPuntoEnFirebase(nombre: String, descripcion: String, tipo: String, posicion: LatLng) {
        val punto = Punto(nombre, descripcion, tipo, posicion.latitude, posicion.longitude, "") // "" para imagenUrl
        referencia.push().setValue(punto)
            .addOnSuccessListener {
                Toast.makeText(this, "Punto agregado", Toast.LENGTH_SHORT).show()
                cargarMarcadoresDesdeFirebase()
            }
    }

    private fun cargarMarcadoresDesdeFirebase() {
        referencia.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dato in snapshot.children) {
                    val punto = dato.getValue(Punto::class.java) ?: continue
                    val marker = mapa.addMarker(
                        MarkerOptions()
                            .position(LatLng(punto.latitud, punto.longitud))
                            .title(punto.nombre)
                            .snippet(punto.descripcion)
                    )
                    marker?.tag = punto.imagenUrl
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}
