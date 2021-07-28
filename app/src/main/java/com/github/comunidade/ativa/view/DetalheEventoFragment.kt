package com.github.comunidade.ativa.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.comunidade.ativa.R
import com.github.comunidade.ativa.databinding.FragmentDetalheEventoBinding
import com.github.comunidade.ativa.extensions.balanceFormatted
import com.github.comunidade.ativa.model.Evento
import com.github.comunidade.ativa.viewmodel.ComunidadeAtivaViewModel
import java.text.SimpleDateFormat
import java.util.*

class DetalheEventoFragment : BaseFragment() {
    private lateinit var detalheEventoBinding: FragmentDetalheEventoBinding
    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(ComunidadeAtivaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detalheEventoBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detalhe_evento, container, false)
        viewModel.eventoSelecionadoObserver.observe(requireActivity(), Observer { evento ->
            evento?.let {
                val format = formatarDataDetalhada(it.date?.let { it1 -> Date(it1) })
                detalheEventoBinding.tvDescription.text = it.description
                detalheEventoBinding.appBar.tvNomeEvento.text = it.title
                detalheEventoBinding.dataQuando = format
                detalheEventoBinding.quantoCusta = it.price.balanceFormatted()
            }
        })
        return detalheEventoBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.check_in -> {
                Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> {
                viewModel.eventoSelecionadoResponse.value?.let { onShare(it) }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun onShare(evento: Evento) {
        startActivity(createIntentShare(evento))
    }

    fun formatarDataDetalhada(data: Date?): String? {
        val dateFormat =
            SimpleDateFormat("dd 'de' MMMM 'de' yyyy 'às' HH:mm:ss", Locale("pt", "BR"))
        return if (data == null) {
            ""
        } else dateFormat.format(data)
    }

}