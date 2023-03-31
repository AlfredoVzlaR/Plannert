package mx.edu.plannert

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.TextView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Interes.newInstance] factory method to
 * create an instance of this fragment.
 */
class Interes : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var gridView: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }




    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_interes, container, false)
        val imagenes = arguments?.getIntegerArrayList("imagenes")
        val sub = arguments?.getString("subtitulo")
        val ocultar= arguments?.getBoolean("ocultar")
      //  return inflater.inflate(R.layout.fragment_interes, container, false)
        val view = inflater.inflate(R.layout.fragment_interes, container, false)
        val gridView = view.findViewById<GridView>(R.id.grid_interes)
        val subtitulo= view.findViewById<TextView>(R.id.txtSubtiulo)
        val boton1= view.findViewById<Button>(R.id.btn1)
        val boton2= view.findViewById<Button>(R.id.btn2)
        val boton3= view.findViewById<Button>(R.id.btn3)
        val boton4= view.findViewById<Button>(R.id.btn4)
        val mensaje= view.findViewById<TextView>(R.id.txtMensaje)

        subtitulo.setText(sub)
        if(ocultar!=null)
        {
            if(ocultar==true){
                boton1.visibility=View.INVISIBLE
                boton2.visibility=View.INVISIBLE
                boton3.visibility=View.INVISIBLE
                boton4.visibility=View.INVISIBLE
                mensaje.visibility=View.INVISIBLE

            }
        }


        if(sub=="Peliculas"){
            mensaje.setText("Selecciona 5 intereses")
        }else if(sub=="Plataformas"){
            mensaje.setText("¿Qué plataformas sueles utilizar?")
        }
       // val images = listOf(R.drawable.prodigy, R.drawable.alien, R.drawable.ironman,R.drawable.shanchi,R.drawable.quantumania,R.drawable.lightyear,R.drawable.shrek,R.drawable.elvis,R.drawable.fightclub,R.drawable.tres,R.drawable.blackswan,R.drawable.hollywood)
        val adapter = ImageAdapter(requireContext(), imagenes as ArrayList<Int>)
        gridView.adapter = adapter

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gridView = view.findViewById(R.id.grid_interes)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Interes.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Interes().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }



        fun newInstance(imagenes: List<Int>,subtitulo:String): Interes {
            val fragment = Interes()
            val args = Bundle()
            args.putIntegerArrayList("imagenes", ArrayList(imagenes))
            args.putString("subtitulo",subtitulo)
            fragment.arguments = args
            return fragment
        }

        fun newInstance(imagenes: List<Int>,subtitulo:String,ocultar: Boolean): Interes {
            val fragment = Interes()
            val args = Bundle()
            args.putIntegerArrayList("imagenes", ArrayList(imagenes))
            args.putString("subtitulo",subtitulo)
            args.putBoolean("ocultar",ocultar)
            fragment.arguments = args
            return fragment
        }
    }


}