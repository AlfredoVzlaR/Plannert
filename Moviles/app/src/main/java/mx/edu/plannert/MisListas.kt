package mx.edu.plannert

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.*
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        obtenerListaPortadas()

        // Configurar GridLayoutManager para los RecyclerViews
        val gridLayoutManager1 = GridLayoutManager(this, 4)
        val gridLayoutManager2 = GridLayoutManager(this, 4)
        val gridLayoutManager5 = GridLayoutManager(this, 3)

        favoritas.layoutManager = gridLayoutManager1
        recientes.layoutManager = gridLayoutManager2
        listas.layoutManager = gridLayoutManager5

        // Inicializar y asignar adaptadores a los RecyclerViews
        portadaAdapter1 = PortadaAdapter(portadas)

        favoritas.adapter = portadaAdapter1
        recientes.adapter = portadaAdapter1
        listas.adapter = portadaAdapter1

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun obtenerListaPortadas(): ArrayList<Portada> {

        portadas.add(Portada(R.drawable.portadalistauno))
        portadas.add(Portada(R.drawable.portadalistados))
        portadas.add(Portada(R.drawable.recuadro_favoritos_conlinea))
        portadas.add(Portada(R.drawable.recuadro_favoritos_conlinea))
        portadas.add(Portada(R.drawable.portadalistados))
        return portadas
    }
}

class PortadaAdapter(private val listas: List<Portada>) : RecyclerView.Adapter<PortadaAdapter.PortadaViewHolder>() {

    private lateinit var context: Context

    inner class PortadaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener {
        val imagenPortada: ImageView = itemView.findViewById(R.id.iv_portada)

        init {
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
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





