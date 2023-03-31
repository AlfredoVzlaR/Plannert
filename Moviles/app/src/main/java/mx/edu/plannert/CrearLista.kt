package mx.edu.plannert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class CrearLista : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_lista)
        val siguiente: TextView = findViewById(R.id.tv_siguientePasoListas)
        val titulo: TextView = findViewById(R.id.titulo)
        val layoutC: LinearLayout= findViewById(R.id.layoutCrearListas)

        val btn1: Button = findViewById(R.id.Rbtn1)
        val btn2: Button = findViewById(R.id.Rbtn2)
        val btn3: Button = findViewById(R.id.Rbtn3)
        val btn4: Button = findViewById(R.id.Rbtn4)

        btn1.setBackgroundResource(R.drawable.circuloseleccionado)

        var fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewCuerpo)

        siguiente.setOnClickListener {
            fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewCuerpo)
            if (fragment is TipoLista) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerViewCuerpo, Categorias())
                    .commit()
                titulo.setText("Categorias")
                btn1.setBackgroundResource(R.drawable.circulo)
                btn2.setBackgroundResource(R.drawable.circuloseleccionado)
                btn1.setBackgroundResource(R.drawable.circulo)
                btn1.setBackgroundResource(R.drawable.circulo)

            }else if(fragment is Categorias) {
                val imagenes = listOf(
                    R.drawable.pluto,
                    R.drawable.netflix,
                    R.drawable.primevideo,
                    R.drawable.cuevana,
                    R.drawable.hbo,
                    R.drawable.diney,
                    R.drawable.star,
                    R.drawable.tubi,
                    R.drawable.vix,
                    R.drawable.appletv,
                    R.drawable.paramount,
                    R.drawable.hulu
                )
               val titlesd = Interes.newInstance(imagenes,"Romance",true)
                titulo.setText("Escoge los titulos de tu interés ")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerViewCuerpo,titlesd )
                    .commit()

                btn1.setBackgroundResource(R.drawable.circulo)
                btn2.setBackgroundResource(R.drawable.circulo)
                btn3.setBackgroundResource(R.drawable.circuloseleccionado)
                btn4.setBackgroundResource(R.drawable.circulo)
            }else if (fragment is Interes) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerViewCuerpo, IdentificacionLista())
                    .commit()
                titulo.setText("¡Ya casi terminas!")
                siguiente.setText("Terminar")
                layoutC.setBackgroundResource(R.drawable.fondotransparente)
                btn1.setBackgroundResource(R.drawable.circulo)
                btn2.setBackgroundResource(R.drawable.circulo)
                btn3.setBackgroundResource(R.drawable.circulo)
                btn4.setBackgroundResource(R.drawable.circuloseleccionado)

            }else if (fragment is IdentificacionLista) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerViewCuerpo, TerminacionCreacionLista())
                    .commit()
                titulo.setText("¡Ya casi terminas!")
                siguiente.setText("Terminar")
                layoutC.setBackgroundResource(R.drawable.fondotransparente)
                btn1.visibility= View.INVISIBLE
                btn2.visibility= View.INVISIBLE
                btn3.visibility= View.INVISIBLE
                btn4.visibility= View.INVISIBLE

                siguiente.setText("Ir a inicio")
                titulo.setText("¡Felicidades!")


            }

            btn1.setOnClickListener{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerViewCuerpo, TipoLista())
                    .commit()
                titulo.setText("¿De qué será tu lista?")
                siguiente.setText("Siguiente")
                btn2.setBackgroundResource(R.drawable.circulo)
                btn1.setBackgroundResource(R.drawable.circuloseleccionado)
                btn3.setBackgroundResource(R.drawable.circulo)
                btn4.setBackgroundResource(R.drawable.circulo)

            }

            btn2.setOnClickListener{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerViewCuerpo, Categorias())
                    .commit()
                titulo.setText("Categorias")
                siguiente.setText("Siguiente")
                btn1.setBackgroundResource(R.drawable.circulo)
                btn2.setBackgroundResource(R.drawable.circuloseleccionado)
                btn3.setBackgroundResource(R.drawable.circulo)
                btn4.setBackgroundResource(R.drawable.circulo)

            }

            btn3.setOnClickListener{
                val imagenes = listOf(
                    R.drawable.pluto,
                    R.drawable.netflix,
                    R.drawable.primevideo,
                    R.drawable.cuevana,
                    R.drawable.hbo,
                    R.drawable.diney,
                    R.drawable.star,
                    R.drawable.tubi,
                    R.drawable.vix,
                    R.drawable.appletv,
                    R.drawable.paramount,
                    R.drawable.hulu
                )
                val titlesd = Interes.newInstance(imagenes,"Romance",true)
                titulo.setText("Escoge los titulos de tu interés ")
                siguiente.setText("Siguiente")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerViewCuerpo,titlesd )
                    .commit()

                btn1.setBackgroundResource(R.drawable.circulo)
                btn2.setBackgroundResource(R.drawable.circulo)
                btn3.setBackgroundResource(R.drawable.circuloseleccionado)
                btn4.setBackgroundResource(R.drawable.circulo)

            }

            btn4.setOnClickListener{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerViewCuerpo, IdentificacionLista())
                    .commit()
                titulo.setText("Categorias")
                siguiente.setText("Terminar")
                btn2.setBackgroundResource(R.drawable.circulo)
                btn4.setBackgroundResource(R.drawable.circuloseleccionado)
                btn3.setBackgroundResource(R.drawable.circulo)
                btn1.setBackgroundResource(R.drawable.circulo)

            }



        }
    }
}