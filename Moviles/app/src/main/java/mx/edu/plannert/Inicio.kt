package mx.edu.plannert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        var menu1: ImageView = findViewById(R.id.menup)




        menu1.setOnClickListener {
            val intent = Intent(this,menu::class.java)
            startActivity(intent)
        }



    }
}