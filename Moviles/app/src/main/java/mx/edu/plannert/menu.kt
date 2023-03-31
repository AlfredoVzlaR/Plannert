package mx.edu.plannert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val opcionPerfil= findViewById<Button>(R.id.opcionPerfil)
        val opcionConfiguracion= findViewById<Button>(R.id.opcionConfiguracion)
        val opcionNosotros= findViewById<Button>(R.id.opcionNosotros)
        val opcionAyuda= findViewById<Button>(R.id.opcionAyuda)


        opcionPerfil.setOnClickListener{

            val intent = Intent(this, OpcionesMenu::class.java)
            startActivity(intent)


        }

        opcionAyuda.setOnClickListener{
            val intent = Intent(this, Ayuda::class.java)
            startActivity(intent)
        }

        opcionNosotros.setOnClickListener{
            val intent = Intent(this, Nosotros::class.java)
            startActivity(intent)
        }


    }
}