package com.github.comunidade.ativa.view

import android.content.Intent
import androidx.fragment.app.Fragment
import com.github.comunidade.ativa.model.Evento

open class BaseFragment: Fragment() {

    fun createIntentShare(evento: Evento): Intent {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Opa, blz? Venha comigo ao evento " + evento.title + " para aproveitarmos juntos.")
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        return shareIntent
    }

}