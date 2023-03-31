package mx.edu.plannert

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction

class Introductorio : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introductorio)

        val siguiente: TextView=findViewById(R.id.tv_siguientePaso)
        val myLinearLayout = findViewById<LinearLayout>(R.id.layoutI)
        var fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        var Rbtn1:RadioButton=findViewById(R.id.Rbtn1)
        var Rbtn2:RadioButton=findViewById(R.id.Rbtn2)
        var Rbtn3:RadioButton=findViewById(R.id.Rbtn3)
        var Rbtn4:RadioButton=findViewById(R.id.Rbtn4)
        var Rbtn5:RadioButton=findViewById(R.id.Rbtn5)
        var tarde: TextView = findViewById(R.id.tv_siguientePaso)
        var actual: String? =null
        Rbtn1.setBackgroundResource(R.drawable.circuloseleccionado)



        siguiente.setOnClickListener {
            fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
            if(fragment is Bienvenida) {
                actual="peliculas"

                val imagenes = listOf(R.drawable.prodigy, R.drawable.alien, R.drawable.ironman,R.drawable.shanchi,R.drawable.quantumania,R.drawable.lightyear,R.drawable.shrek,R.drawable.elvis,R.drawable.fightclub,R.drawable.tres,R.drawable.blackswan,R.drawable.hollywood)
               val interes=Interes.newInstance(imagenes,"Peliculas")


                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, interes)
                    .commit()

                Rbtn2.setBackgroundResource(R.drawable.circuloseleccionado)
                Rbtn3.setBackgroundResource(R.drawable.circulo)
                Rbtn1.setBackgroundResource(R.drawable.circulo)
                Rbtn4.setBackgroundResource(R.drawable.circulo)
                Rbtn5.setBackgroundResource(R.drawable.circulo)

            }else if(fragment is Interes){
                actual="plataformas"
                val imagenes = listOf(R.drawable.pluto, R.drawable.netflix, R.drawable.primevideo,R.drawable.cuevana,
                    R.drawable.hbo,R.drawable.diney,R.drawable.star,R.drawable.tubi,R.drawable.vix,
                    R.drawable.appletv,R.drawable.paramount,R.drawable.hulu)
                val interes2=Interes.newInstance(imagenes,"Plataformas")



                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, interes2)
                    .commit()
                Rbtn1.setBackgroundResource(R.drawable.circulo)
                Rbtn2.setBackgroundResource(R.drawable.circulo)
                Rbtn3.setBackgroundResource(R.drawable.circuloseleccionado)
            }else if(fragment is Interes){
                actual="elige un avatar"
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, elegirAvatar())

                Rbtn1.setBackgroundResource(R.drawable.circulo)
                Rbtn2.setBackgroundResource(R.drawable.circulo)
                Rbtn3.setBackgroundResource(R.drawable.circuloseleccionado)
                Rbtn4.setBackgroundResource(R.drawable.circulo)
                Rbtn5.setBackgroundResource(R.drawable.circulo)
            }
            else if(fragment is Interes){
                actual="elige un avatar"
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, elegirAvatar())

                Rbtn1.setBackgroundResource(R.drawable.circulo)
                Rbtn2.setBackgroundResource(R.drawable.circulo)
                Rbtn3.setBackgroundResource(R.drawable.circuloseleccionado)
                Rbtn4.setBackgroundResource(R.drawable.circulo)
                Rbtn5.setBackgroundResource(R.drawable.circulo)
            }
            myLinearLayout.setBackgroundResource(R.drawable.fondomorado)

        }

        Rbtn1.setOnClickListener{

            fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, Bienvenida())
                .commit()

            Rbtn1.setBackgroundResource(R.drawable.circuloseleccionado)
            Rbtn2.setBackgroundResource(R.drawable.circulo)
            Rbtn3.setBackgroundResource(R.drawable.circulo)
            Rbtn4.setBackgroundResource(R.drawable.circulo)
            Rbtn5.setBackgroundResource(R.drawable.circulo)
            myLinearLayout.setBackgroundResource(R.drawable.fondotransparente)


        }

        Rbtn2.setOnClickListener{

                val imagenes = listOf(R.drawable.prodigy, R.drawable.alien, R.drawable.ironman,R.drawable.shanchi,R.drawable.quantumania,R.drawable.lightyear,R.drawable.shrek,R.drawable.elvis,R.drawable.fightclub,R.drawable.tres,R.drawable.blackswan,R.drawable.hollywood)
                val interes=Interes.newInstance(imagenes,"Peliculas")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, interes)
                    .commit()
                Rbtn1.setBackgroundResource(R.drawable.circulo)
                Rbtn3.setBackgroundResource(R.drawable.circulo)
            Rbtn4.setBackgroundResource(R.drawable.circulo)
            Rbtn5.setBackgroundResource(R.drawable.circulo)

                Rbtn2.setBackgroundResource(R.drawable.circuloseleccionado)
            myLinearLayout.setBackgroundResource(R.drawable.fondomorado)




        }

        Rbtn3.setOnClickListener{

                val imagenes = listOf(R.drawable.pluto, R.drawable.netflix, R.drawable.primevideo,R.drawable.cuevana,
                    R.drawable.hbo,R.drawable.diney,R.drawable.star,R.drawable.tubi,R.drawable.vix,
                    R.drawable.appletv,R.drawable.paramount,R.drawable.hulu)
                val interes2=Interes.newInstance(imagenes,"Plataformas")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, interes2)
                    .commit()
                Rbtn1.setBackgroundResource(R.drawable.circulo)
            Rbtn2.setBackgroundResource(R.drawable.circulo)
            Rbtn4.setBackgroundResource(R.drawable.circulo)
                Rbtn3.setBackgroundResource(R.drawable.circuloseleccionado)
            Rbtn5.setBackgroundResource(R.drawable.circulo)

            myLinearLayout.setBackgroundResource(R.drawable.fondomorado)
        }

        Rbtn4.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, elegirAvatar())
                .commit()
            Rbtn1.setBackgroundResource(R.drawable.circulo)
            Rbtn2.setBackgroundResource(R.drawable.circulo)
            Rbtn3.setBackgroundResource(R.drawable.circulo)
            Rbtn4.setBackgroundResource(R.drawable.circuloseleccionado)
            Rbtn5.setBackgroundResource(R.drawable.circulo)

            myLinearLayout.setBackgroundResource(R.drawable.fondomorado)
        }
        Rbtn5.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView,nombrarAvatar())
                .commit()
            Rbtn1.setBackgroundResource(R.drawable.circulo)
            Rbtn2.setBackgroundResource(R.drawable.circulo)
            Rbtn3.setBackgroundResource(R.drawable.circulo)
            Rbtn4.setBackgroundResource(R.drawable.circulo)
            Rbtn5.setBackgroundResource(R.drawable.circuloseleccionado)
            myLinearLayout.setBackgroundResource(R.drawable.fondomorado)
        }



    }


}

private fun FragmentTransaction.replace(fragmentContainerView: Int, elegirAvatar: elegirAvatar.Companion) {

}
