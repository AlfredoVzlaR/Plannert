package mx.edu.plannert

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MisListas : AppCompatActivity() {

    lateinit var favoritas: RecyclerView
    lateinit var recientes: RecyclerView
    lateinit var listas : RecyclerView
    private var portadaAdapter1: PortadaAdapter? = null
    private var portadas=ArrayList<Portada>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_listas)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Mostrar el botón de volver atrás en la barra de herramientas

        // Mostrar el botón de volver atrás en la barra de herramientas
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);

        getSupportActionBar()?.setTitle(Html.fromHtml("<font color='#ffffff' size='5sp' font-family='@font/intermedium'>Inicio / Mis listas</font>"))

        // Inicializar RecyclerViews
        favoritas = findViewById(R.id.listasFavoritas)
        recientes= findViewById(R.id.listasRecientes)
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
        portadaAdapter1 = PortadaAdapter(portadas )

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
        return portadas
    }
}

class PortadaAdapter(private val portadas: List<Portada>) : RecyclerView.Adapter<PortadaAdapter.PortadaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortadaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_listas, parent, false)
        return PortadaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PortadaViewHolder, position: Int) {
        val prod = portadas[position]
        holder.bind(prod)
    }

    override fun getItemCount(): Int {
        return portadas.size
    }

    inner class PortadaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imagen: ImageView = itemView.findViewById(R.id.iv_portada)

        fun bind(portada: Portada) {
            // Cargar la imagen desde el recurso local utilizando el ID del recurso
            imagen.setImageResource(portada.Imagen)
        }
    }
}



