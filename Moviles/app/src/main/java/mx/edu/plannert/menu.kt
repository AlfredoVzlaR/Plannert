package mx.edu.plannert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class menu : AppCompatActivity() {
    private lateinit var usuarioActual: Usuarios
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val opcionPerfil= findViewById<Button>(R.id.opcionPerfil)
        val opcionConfiguracion= findViewById<Button>(R.id.opcionConfiguracion)
        val opcionNosotros= findViewById<Button>(R.id.opcionNosotros)
        val opcionAyuda= findViewById<Button>(R.id.opcionAyuda)
        val regresaInicio = findViewById<ImageView>(R.id.menuu)
        val etiquetaNombreUsuario= findViewById<TextView>(R.id.tv_nombreUsuarioMenu)

        obtenerUsuarioActual { usuario ->
            if (usuario != null) {
                usuarioActual = usuario


                val nombreUsuario = usuario.usuario ?: "Nombre de usuario desconocido"

                etiquetaNombreUsuario.setText(nombreUsuario)
            } else {
                // Manejar el caso en que no se encontró el usuario actual
                Toast.makeText(
                    this,
                    "¡No hay usuario actual!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        opcionPerfil.setOnClickListener{
            val intent = Intent(this, OpcionesMenu::class.java)
            startActivity(intent)
        }

        opcionAyuda.setOnClickListener{
            val intent = Intent(this, help::class.java)
            startActivity(intent)
        }

        opcionNosotros.setOnClickListener{
            val intent = Intent(this, us::class.java)
            startActivity(intent)
        }

        opcionConfiguracion.setOnClickListener {
            val intent = Intent(this, ConfigIdioma::class.java)
            startActivity(intent)
        }
        regresaInicio.setOnClickListener {
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
        }
    }



    private fun obtenerUsuarioActual(callback: (Usuarios?) -> Unit) {
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val email = currentUser.email
            consultarUsuarioPorCorreo() { usuario ->
                callback(usuario)
            }
        } else {
            callback(null)
        }
    }

    private fun consultarUsuarioPorCorreo(callback: (Usuarios?) -> Unit) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()

        val usuariosRef: DatabaseReference = database.getReference("usuarios")
        val auth: FirebaseAuth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            val email = currentUser.email
            usuariosRef.orderByChild("email").equalTo(email)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (usuarioSnapshot in dataSnapshot.children) {
                            val usuarioKey = usuarioSnapshot.key
                            val usuario = usuarioSnapshot.getValue(Usuarios::class.java)
                            callback(usuario)
                            return
                        }
                        callback(null)
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        callback(null)
                    }
                })
        }
    }
}