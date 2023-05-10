package mx.edu.plannert

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView

class ListasFav2Activity : AppCompatActivity() {
    var adapter: TitulosAdapter? = null
    var titulosR = ArrayList<Titulos>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listas_fav2)
        ////EN PROCESO, LO TUVE QUE BORRAR
        val gridView: GridView = findViewById(R.id.grid_titulos)
        titulosR.add(Titulos(R.drawable.shrek, "Shrek"))
        titulosR.add(Titulos(R.drawable.fightclub, "Fight club"))
        titulosR.add(Titulos(R.drawable.blackswan, "El cisne negro"))
        titulosR.add(Titulos(R.drawable.lightyear, "Lightyear"))
        titulosR.add(Titulos(R.drawable.serie3, "Shang-chi"))
        adapter = TitulosAdapter(this, titulosR)
        gridView.adapter = adapter

    }

    data class Titulos(var image: Int, var nombre: String) {
    }
    class TitulosAdapter : BaseAdapter {

        var titulos = ArrayList<Titulos>()
        var contexto: Context? = null

        constructor(contexto: Context, tit: ArrayList<Titulos>) {
            this.titulos = tit
            this.contexto = contexto
        }

        override fun getCount(): Int {
            return titulos.size
        }

        override fun getItem(p0: Int): Any {
            return titulos[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var prod = titulos[p0]
            var inflator = contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.activity_listas_fav2, null)
            /*
            var imagen = vista.findViewById(R.id.iv_regalo_imagen) as ImageView
            var precio = vista.findViewById(R.id.tv_regalo_precio) as TextView

            imagen.setImageResource(prod.image)
            precio.setText(prod.precio)

            imagen.setOnClickListener() {
                val intento = Intent(contexto, DetalleRegalos::class.java)
                intento.putExtra("imagen", prod.image)
                intento.putExtra("precio", prod.precio)
                contexto!!.startActivity(intento)
            }*/
            return vista
        }

    }
}