package mx.edu.plannert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InicioListas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_listas)

        val button = findViewById<Button>(R.id.btnInicioLista)
        button.setOnClickListener {
            val intent = Intent(this, CrearLista::class.java)
            startActivity(intent)
        }
    }
}