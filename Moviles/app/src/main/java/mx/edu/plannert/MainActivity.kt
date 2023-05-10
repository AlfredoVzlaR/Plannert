package mx.edu.plannert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    //private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Plannert)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //auth = FirebaseAuth.getInstance()

        val registro: TextView = findViewById(R.id.tv_registrate)
        val botonIS: Button = findViewById(R.id.btnIniciarSesion)
        val necesitoAyuda: TextView = findViewById(R.id.necesitoAyuda)

        val emailET: EditText = findViewById(R.id.txt_emailInicio)
        val pswET: EditText = findViewById(R.id.txt_contraseñaInicio)

        registro.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }

        botonIS.setOnClickListener {

            if(emailET.text.isNotEmpty() && pswET.text.isNotEmpty() ){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailET.text.toString(),
                    pswET.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        showHome()
                    }else{
                        showAlert("Se produjo un error autenticando al usuario.")
                    }
                }
            }else{
                showAlert("Debes llenar todos los campos")
            }
            /*if (emailET.text.isEmpty() || pswET.text.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa tu correo electrónico y contraseña", Toast.LENGTH_SHORT).show()
            } else {
                if (emailET.text.isNotEmpty() && pswET.text.isNotEmpty()) {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(
                        emailET.text.toString().trim(),
                        pswET.text.toString().trim()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, Introductorio::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // El inicio de sesión falló, mostrar mensaje de error
                            Toast.makeText(
                                this,
                                "Inicio de sesión fallido, verifica la contraseña",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }*/

            /*
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
            }*/
        }

        necesitoAyuda.setOnClickListener {
            val intent = Intent(this, help::class.java)
            startActivity(intent)
        }
    }

    private fun showAlert (msg:String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(msg)
        builder.setPositiveButton( "Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(){
        val homeIntent= Intent(this, Introductorio::class.java)
        startActivity(homeIntent)
    }
}
