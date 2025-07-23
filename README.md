# 🥄 CuchareableApp

**CuchareableApp** es una aplicación Android desarrollada en Kotlin que permite a los usuarios encontrar, visualizar y colaborar con puntos de venta de comida callejera como chocho, combinado, desayunos, etc., utilizando Google Maps y Firebase.

## 📱 Funcionalidades principales

- 🗺️ Mapa interactivo con marcadores personalizados.
- ➕ Los usuarios pueden agregar nuevos puntos de comida callejera.
- 🖼️ Visualización de fotos, descripciones y tipo de comida al seleccionar un marcador.
- 🔍 Filtros por tipo de comida.
- 🎨 Íconos personalizados para los marcadores.
- 🔒 Datos sincronizados en tiempo real con Firebase Realtime Database.

## 🛠️ Tecnologías utilizadas

- Kotlin
- Android SDK
- Google Maps SDK
- Firebase Realtime Database
- Firebase Analytics
- Glide (para cargar imágenes)

## 🧱 Estructura del proyecto

app/
├── src/
│ ├── main/
│ │ ├── java/com/example/cuchareableapp/
│ │ │ ├── MapsActivity.kt
│ │ │ ├── AddFoodPointDialog.kt
│ │ │ ├── CustomInfoWindowAdapter.kt
│ │ │ ├── models/
│ │ │ │ └── FoodPoint.kt
│ │ │ └── utils/
│ │ │ └── MarkerUtils.kt
│ │ ├── res/
│ │ │ ├── layout/
│ │ │ │ ├── activity_maps.xml
│ │ │ │ ├── dialog_add_point.xml
│ │ │ │ └── custom_info_window.xml
│ │ │ └── drawable/
│ │ │ └── ic_marker.png
│ │ └── AndroidManifest.xml
├── build.gradle.kts

<img width="1845" height="1079" alt="image" src="https://github.com/user-attachments/assets/473b7e97-b74d-4f6a-baab-b008aa7f828d" />

<img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/73ad985b-f857-4c65-a55b-3e79543ff1af" />

## 🔧 Configuración e instalación

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tuusuario/cuchareableapp.git
   cd cuchareableapp
Abre el proyecto en Android Studio.

Asegúrate de tener configurado Firebase en tu consola:

Crea un proyecto en Firebase Console

Descarga y coloca el archivo google-services.json en app/

Habilita Firebase Realtime Database en modo prueba:
{
  "rules": {
    ".read": true,
    ".write": true
  }
}
Sincroniza Gradle y ejecuta la app en un emulador o dispositivo físico.

🔍 Próximas mejoras
Autenticación de usuarios.

Agregar calificaciones y reseñas a los puntos.

Opción para reportar puntos inactivos o incorrectos.

Sincronización offline.

🧑‍💻 Autor
Manuel Flores

Desarrollado como parte de un proyecto de exploración de comida callejera en Perú.
