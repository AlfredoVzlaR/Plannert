package mx.edu.plannert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registro: TextView = findViewById(R.id.tv_registrate)

        registro.setOnClickListener{
            val intent = Intent(this,Registro::class.java)
            startActivity(intent)
        }
    }
}