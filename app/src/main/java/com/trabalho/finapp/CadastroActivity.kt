package com.trabalho.finapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CadastroActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val rbCredito = findViewById<RadioButton>(R.id.rbCredito)
        val rbDebito = findViewById<RadioButton>(R.id.rbDebito)

        val etDescricao = findViewById<EditText>(R.id.etDescricao)
        val etValor = findViewById<EditText>(R.id.etValor)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)

        btnSalvar.setOnClickListener {
            val tipo =
                if (rbCredito.isChecked) "Crédito" else if (rbDebito.isChecked) "Débito" else null
            val descricao = etDescricao.text.toString().trim()

            val valorStr = etValor.text.toString().trim()

            if (tipo == null) {
                Toast.makeText(this, "Selecione um tipo de operação", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            if (descricao.isEmpty()) {
                Toast.makeText(this, "Informe uma descrição", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val valor = valorStr.toDoubleOrNull()
            if (valorStr.isEmpty()) {
                Toast.makeText(this, "Informe um valor", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (valor == null || valor <= 0) {
                Toast.makeText(this, "Valor inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            FinAppData.transacoes.add(Transacao(tipo, descricao, valor))
            Toast.makeText(this, "Transação cadastrada com sucesso", Toast.LENGTH_SHORT).show()
            finish()



        }
    }
}