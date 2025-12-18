package com.example.fortunecookie

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivityFrases : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_frases)

        val frase = findViewById<TextView>(R.id.frase)
        val frasesArray = resources.getStringArray(R.array.frases)
        frase.text = frasesArray.random()

        val compartir = findViewById<FloatingActionButton>(R.id.compartir)
        val copiar = findViewById<FloatingActionButton>(R.id.copiar)
        val recargar = findViewById<FloatingActionButton>(R.id.recargar)

        compartir.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Fixa't en aquesta frase!")
            startActivity(Intent.createChooser(intent, "Compartir amb ..."))
        }

        copiar.setOnClickListener {
            val portaretalls = getSystemService(CLIPBOARD_SERVICE ) as ClipboardManager
            val fraseCopiada = ClipData.newPlainText("text", frase.text)
            portaretalls.setPrimaryClip(fraseCopiada)
            Toast.makeText(this, "Text copiat al portaretalls", Toast.LENGTH_SHORT).show()
        }

        recargar.setOnClickListener {
            frase.text = frasesArray.random()
        }
    }
}