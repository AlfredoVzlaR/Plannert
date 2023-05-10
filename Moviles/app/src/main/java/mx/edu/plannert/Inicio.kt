package mx.edu.plannert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Inicio : AppCompatActivity() {
    private lateinit var usuarioActual: Usuarios

    private lateinit var client:GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        var menu1: ImageView = findViewById(R.id.menup)


        menu1.setOnClickListener {
            val intent = Intent(this,menu::class.java)
            startActivity(intent)
        }

        val tvname = findViewById<TextView>(R.id.tvname)

        obtenerUsuarioActual { usuario ->
            if (usuario != null) {
                usuarioActual = usuario


                val nombreUsuario = usuario.usuario ?: "Nombre de usuario desconocido"

                tvname.setText("Bienvenido "+nombreUsuario)
            } else {
                // Manejar el caso en que no se encontró el usuario actual
                val bundle = intent.extras
                if (bundle != null){
                    val name = bundle.getString("name")
                    tvname.setText(name)
                }

            }
        }

        val bundle = intent.extras
        if (bundle != null){
            val name = bundle.getString("name")
         //  tvname.setText(name)
        }

        val options = GoogleSignInOptions.Builder().requestEmail().build()
        client = GoogleSignIn.getClient(this,options)

        val cerrar: TextView = findViewById(R.id.cerrar)

        cerrar.setOnClickListener {

            client.signOut()
            finish()
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