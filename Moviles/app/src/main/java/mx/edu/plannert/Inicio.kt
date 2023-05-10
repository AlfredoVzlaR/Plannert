package mx.edu.plannert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        var menu1: ImageView = findViewById(R.id.menup)

        menu1.setOnClickListener {
            val intent = Intent(this,menu::class.java)
            startActivity(intent)
        }

        val tvname = findViewById<TextView>(R.id.tvname)

        val bundle = intent.extras
        if (bundle != null){
            val name = bundle.getString("name")
            tvname.setText(name)
        }

        val cerrar: TextView = findViewById(R.id.cerrar)

        cerrar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
            finish()
        }



    }
}