package mx.edu.plannert

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import com.google.android.material.appbar.MaterialToolbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TolBarMenu.newInstance] factory method to
 * create an instance of this fragment.
 */
class TolBarMenu : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var etiqueta: TextView

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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tol_bar_menu, container, false)

        etiqueta = view.findViewById(R.id.etiquetaNavegacion)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar) // Utilizando MaterialToolbar



        // Configurar el OnClickListener en el Toolbar
        toolbar.setNavigationOnClickListener {
            // Aquí puedes definir la acción que deseas realizar al hacer clic en el botón de navegación
            // Por ejemplo, puedes cerrar el fragment actual o volver al fragment anterior
            activity?.onBackPressed()

        }


        return view

    }


    fun actualizarTexto(nuevoTexto: String) {
        etiqueta.text = nuevoTexto
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TolBarMenu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TolBarMenu().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}