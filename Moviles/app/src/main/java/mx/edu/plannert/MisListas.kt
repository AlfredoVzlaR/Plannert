package mx.edu.plannert

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MisListas : AppCompatActivity() {

    lateinit var favoritas: RecyclerView
    lateinit var recientes: RecyclerView
    lateinit var listas: RecyclerView
    private var portadaAdapter1: PortadaAdapter? = null
    private var portadas = ArrayList<Portada>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_listas)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Mostrar el botón de volver atrás en la barra de herramientas
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        supportActionBar?.title =
            Html.fromHtml("<font color='#ffffff' size='5sp' font-family='@font/intermedium'>Inicio / Mis listas</font>")

        // Inicializar RecyclerViews
        favoritas = findViewById(R.id.listasFavoritas)
        recientes = findViewById(R.id.listasRecientes)
        listas = findViewById(R.id.listas)
        //  obtenerListaPortadas()
        val gridLayoutManager1 = GridLayoutManager(this, 4)
        val gridLayoutManager2 = GridLayoutManager(this, 4)
        val gridLayoutManager5 = GridLayoutManager(this, 3)

        favoritas.layoutManager = gridLayoutManager1
        recientes.layoutManager = gridLayoutManager2
        listas.layoutManager = gridLayoutManager5

        obtenerListaPortadas(object : ObtenerListaPortadasCallback {
            override fun onListaPortadasObtenida(portadas: ArrayList<Portada>) {
                // Aquí puedes hacer algo con la lista de portadas
                // Configurar GridLayoutManager para los RecyclerViews

                // Inicializar y asignar adaptadores a los RecyclerViews
                portadaAdapter1 = PortadaAdapter(portadas)

                favoritas.adapter = portadaAdapter1
                recientes.adapter = portadaAdapter1
                listas.adapter = portadaAdapter1
            }
        })


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    interface ObtenerListaPortadasCallback {
        fun onListaPortadasObtenida(portadas: ArrayList<Portada>)
    }


    fun obtenerListaPortadas(callback: ObtenerListaPortadasCallback) {
        val database = FirebaseDatabase.getInstance()
        val databaseReference = database.getReference("listas")

        val portadas = ArrayList<Portada>()

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Manejar los datos obtenidos de los registros
                for (registroSnapshot in dataSnapshot.children) {
                    // Obtener los datos del registro
                    val categoria = registroSnapshot.child("icono").getValue(String::class.java)
                    //    val contenidos = registroSnapshot.child("contenidos").getValue(String::class.java)
                    val favorita = registroSnapshot.child("favorita").getValue(Boolean::class.java)
                    val icono = registroSnapshot.child("icono").getValue(String::class.java)
                    val nombre = registroSnapshot.child("nombre").getValue(String::class.java)
                    val tipo = registroSnapshot.child("tipo").getValue(String::class.java)
                    val usuario = registroSnapshot.child("usuario").getValue(String::class.java)

                    if (icono == "icono1") {
                        portadas.add(Portada(R.drawable.portadalistados))
                    }
                    if (icono == "icono2") {
                        portadas.add(Portada(R.drawable.portadalistatres))
                    }
                    if (icono == "icono3") {
                        portadas.add(Portada(R.drawable.portadalistauno))
                    }
                    if (icono == "icono4") {
                        portadas.add(Portada(R.drawable.portadalistacuatro))
                    }

                    // Manejar los datos obtenidos del registro
                }

                callback.onListaPortadasObtenida(portadas)
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error si lo hay
            }
        })
    }

    class PortadaAdapter(private val listas: List<Portada>) :
        RecyclerView.Adapter<PortadaAdapter.PortadaViewHolder>() {

        private lateinit var context: Context

        inner class PortadaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnCreateContextMenuListener {
            val imagenPortada: ImageView = itemView.findViewById(R.id.iv_portada)

            init {
                itemView.setOnCreateContextMenuListener(this)
            }

            override fun onCreateContextMenu(
                menu: ContextMenu?,
                v: View?,
                menuInfo: ContextMenu.ContextMenuInfo?
            ) {
                menu?.setHeaderTitle("Nombre de la lista")
                val inflater = MenuInflater(context)
                inflater.inflate(R.menu.menu_portada, menu)
                setMenuBackground()
                menu?.findItem(R.id.opcion_favoritos)?.setOnMenuItemClickListener {
                    // Lógica para la opción "Agregar a favoritos"
                    true
                }
                menu?.findItem(R.id.opcion_cambiarNombre)?.setOnMenuItemClickListener {
                    // Lógica para la opción "Cambiar el nombre"
                    true
                }
                menu?.findItem(R.id.opcion_eliminar)?.setOnMenuItemClickListener {
                    //Ejemplo
                    val intent = Intent(itemView.context, help::class.java)
                    itemView.context.startActivity(intent)
                    true
                }
            }

            private fun setMenuBackground() {
                //Sin funcionar
                // val container = itemView.parent as View
                // container.setBackgroundResource(R.drawable.background_menu)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortadaViewHolder {
            context = parent.context
            val itemView = LayoutInflater.from(context).inflate(R.layout.item_listas, parent, false)
            return PortadaViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: PortadaViewHolder, position: Int) {
            val lista = listas[position]
            holder.imagenPortada.setImageResource(lista.Imagen)
        }

        override fun getItemCount(): Int {
            return listas.size
        }
    }
}





