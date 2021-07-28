package com.github.comunidade.ativa.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.github.comunidade.ativa.R
import com.github.comunidade.ativa.interfaces.EventoListener
import com.github.comunidade.ativa.model.Evento
import com.squareup.picasso.Picasso

class EventosAdapter(var eventoListener: EventoListener) :
    RecyclerView.Adapter<EventoViewHolder>() {

    private var dataSet: List<Evento>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_eventos, parent, false)
        return EventoViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        if (dataSet.isNullOrEmpty()) {
            return
        }
        val evento = dataSet!!.get(position)
        holder.nomeEvento.text = evento.title
        evento.image?.let {
            Picasso.get().load(evento.image).error(R.drawable.img_evento_default)
                .into(holder.imgEvento)
        }
        if (evento.image.isNullOrEmpty()) {
            Picasso.get().load(R.drawable.img_evento_default).into(holder.imgEvento)
        }
        setListeners(holder, evento)
    }

    private fun setListeners(
        holder: EventoViewHolder,
        evento: Evento
    ) {
        holder.imgEvento.setOnClickListener {
            eventoListener.onClick(evento)
        }
        holder.imgCheck.setOnClickListener {
            eventoListener.onCheck(evento)
        }
        holder.imgCompartilhar.setOnClickListener {
            eventoListener.onShare(evento)
        }
    }

    fun getAppDrawable(@DrawableRes idRes: Int, mContext: Context?): Drawable? {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            if (mContext == null) {
                return null
            }
            return AppCompatResources.getDrawable(mContext, idRes)
        }
        return mContext?.getDrawable(idRes)
    }

    override fun getItemCount() = dataSet?.size ?: 0

    fun setData(data: List<Evento>) {
        dataSet = data
        notifyDataSetChanged()
    }
}

class EventoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imgEvento: ImageView
    var imgCheck: ImageView
    var imgCompartilhar: ImageView
    var nomeEvento: TextView

    init {
        imgEvento = itemView.findViewById(R.id.iv_evento)
        imgCheck = itemView.findViewById(R.id.iv_check_in)
        imgCompartilhar = itemView.findViewById(R.id.iv_compartilhar)
        nomeEvento = itemView.findViewById(R.id.tv_nome_evento)
    }

}