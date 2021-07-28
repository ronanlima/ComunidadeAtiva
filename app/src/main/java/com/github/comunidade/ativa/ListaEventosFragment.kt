package com.github.comunidade.ativa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.github.comunidade.ativa.adapter.EventosAdapter
import com.github.comunidade.ativa.databinding.FragmentListaEventosBinding
import com.github.comunidade.ativa.interfaces.EventoListener
import com.github.comunidade.ativa.model.Evento
import com.github.comunidade.ativa.view.DetalheEventoFragment
import com.github.comunidade.ativa.viewmodel.ComunidadeAtivaViewModel

class ListaEventosFragment : Fragment(), EventoListener {
    private lateinit var listaEventosBinding: FragmentListaEventosBinding
    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(ComunidadeAtivaViewModel::class.java)
    }
    private val eventoAdapter by lazy {
        EventosAdapter(this@ListaEventosFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.consultaEventos()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listaEventosBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_lista_eventos, container, false)

        initObservers()
        configAdapter()
        return listaEventosBinding.root
    }

    private fun initObservers() {
        viewModel.eventoObserver.observe(this, Observer { result ->
            result.onSuccess {
                eventoAdapter.setData(it)
            }
        })
    }

    private fun configAdapter() {
        listaEventosBinding.rvEventos.adapter = eventoAdapter
        listaEventosBinding.rvEventos.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onClick(evento: Evento) {
        viewModel.eventoSelecionadoResponse.postValue(evento)
        findNavController().navigate(R.id.action_listaEventosFragment_to_detalheEventoFragment)
    }
}