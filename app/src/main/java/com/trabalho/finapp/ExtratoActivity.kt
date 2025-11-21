package com.trabalho.finapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button






class ExtratoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extrato)

        val listView = findViewById<ListView>(R.id.listViewExtrato)

        val adapter = object : BaseAdapter() {
            override fun getCount(): Int = FinAppData.transacoes.size
            override fun getItem(position: Int): Transacao = FinAppData.transacoes[position]
            override fun getItemId(position: Int): Long = position.toLong()

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: layoutInflater.inflate(R.layout.item_transacao, parent, false)

                val transacao = getItem(position)
                view.findViewById<TextView>(R.id.tvTipo).text = transacao.tipo
                view.findViewById<TextView>(R.id.tvDescricao).text = transacao.descricao
                view.findViewById<TextView>(R.id.tvValor).text = "R$ ${"%.2f".format(transacao.valor)}"

                val cor = if (transacao.tipo == "Débito") Color.RED else Color.GREEN
                view.findViewById<TextView>(R.id.tvTipo).setTextColor(cor)

                return view
            }
        }

        listView.adapter = adapter

        findViewById<Button>(R.id.btnVoltar).setOnClickListener {
            finish()
        }
    }
}