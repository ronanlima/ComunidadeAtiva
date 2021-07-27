package com.github.comunidade.ativa

import android.os.Bundle
import android.util.Log
import android.util.Log.INFO
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.github.comunidade.ativa.adapter.EventosAdapter
import com.github.comunidade.ativa.databinding.FragmentListaEventosBinding
import com.github.comunidade.ativa.repository.ComunidadeRepository
import com.github.comunidade.ativa.viewmodel.ComunidadeAtivaViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListaEventosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListaEventosFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var listaEventosBinding: FragmentListaEventosBinding
    private val repository by lazy {
        ComunidadeRepository()
    }
    private val viewModel by lazy {
        ViewModelProvider(this).get(ComunidadeAtivaViewModel::class.java)
    }
    private val eventoAdapter by lazy {
        EventosAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        viewModel.consultaEventos(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listaEventosBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_lista_eventos, container, false)
        listaEventosBinding.helloEvento = "Ronan mlk doido"

        viewModel.eventoObserver.observe(this, Observer { result ->
            result.onSuccess {
                eventoAdapter.setData(it)
                it.forEach {
                    it.toJson()?.let { it1 -> Log.i("resultado", it1) }
                }
            }
        })
        listaEventosBinding.rvEventos.adapter = eventoAdapter
        listaEventosBinding.rvEventos.layoutManager = GridLayoutManager(requireContext(), 2)
        return listaEventosBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListaEventosFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListaEventosFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}