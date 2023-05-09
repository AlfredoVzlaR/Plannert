package mx.edu.plannert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Plannert)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val registro: TextView = findViewById(R.id.tv_registrate)
        val botonIS: Button = findViewById(R.id.btnIniciarSesion)
        val necesitoAyuda: TextView = findViewById(R.id.necesitoAyuda)

        registro.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }

        botonIS.setOnClickListener {
            val email_txt: EditText = findViewById(R.id.txt_emailInicio)
            val contraseña_txt: EditText = findViewById(R.id.txt_contraseñaInicio)

            val email = email_txt.text.toString().trim()
            val contraseña = contraseña_txt.text.toString().trim()

            if (email.isEmpty() || contraseña.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa tu correo electrónico y contraseña", Toast.LENGTH_SHORT).show()
            } else {
                // Iniciar sesión con el usuario y la contraseña
                auth.signInWithEmailAndPassword(email, contraseña)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // El inicio de sesión fue exitoso, redirigir a la página Introductorio
                            val intent = Intent(this, Introductorio::class.java)
                            startActivity(intent)
                            finish() // Evitar que el usuario regrese a la pantalla de inicio de sesión
                        } else {
                            // El inicio de sesión falló, mostrar mensaje de error
                            Toast.makeText(this, "Inicio de sesión fallido, verifica la contraseña", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        necesitoAyuda.setOnClickListener {
            val intent = Intent(this, help::class.java)
            startActivity(intent)
        }
    }
}
