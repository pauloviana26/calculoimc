package br.com.crud.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var peso = 45000
    var altura = 130

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listaSexo = arrayListOf<String>()

        skPeso.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                peso = i
                txvPeso.text = "$peso"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                //
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                //
            }
        })
        skAltura.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, j: Int, c: Boolean) {
                altura = j
                txvAltura.text = "$altura"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                //
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                //
            }
        })
        listaSexo.add("Selecione uma opção...")
        listaSexo.add("Masculino")
        listaSexo.add("Feminino")

        var sexoAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, listaSexo)
        spnSexo.adapter = sexoAdapter

        btnCalcular.setOnClickListener {
            var alturaM:Double
            alturaM = altura / 100.0
            var pesoKG:Double
            pesoKG = peso / 1000.0

            var imc = 0.0

            imc = (pesoKG/(alturaM*alturaM))

            val builder = AlertDialog.Builder(this@MainActivity)

            builder.setIcon(R.drawable.imc)
            builder.setTitle("Calculado com sucesso")
            builder.setMessage(java.lang.String.format("IMC: %.2f", imc))
            builder.setCancelable(false)
            builder.setPositiveButton("OK", null)
            builder.create()
            builder.show()
        }
        btnTabela.setOnClickListener {
            var sexoSelecionado = spnSexo.selectedItem as String
            val builder2 = AlertDialog.Builder(this@MainActivity)

            if(sexoSelecionado == "Masculino") {
                builder2.setIcon(R.drawable.imc)
                builder2.setTitle("TABELA IMC Masculino:")
                builder2.setMessage("Obesidade Mórbita: + de 43\nObesidade Moderada: 30 a 39,9\nObesidade Leve: 25 a 29,9\nNormal: 20 a 24,9\nAbaixo do normal: menos de 20")
                builder2.setCancelable(false)
                builder2.setPositiveButton("OK", null)
                builder2.create()
                builder2.show()
            }else if(sexoSelecionado == "Feminino"){
                builder2.setIcon(R.drawable.imc)
                builder2.setTitle("TABELA IMC FEMININO:")
                builder2.setMessage("Obesidade Mórbita: + de 39\nObesidade Moderada: 29 a 38,9\nObesidade Leve: 24 a 28,9\nNormal: 19 a 23,9\nAbaixo do normal: menos de 19")
                builder2.setCancelable(false)
                builder2.setPositiveButton("OK", null)
                builder2.create()
                builder2.show()
            }else if(sexoSelecionado == "Selecione uma opção..."){
                Toast.makeText(this, "Por favor, selecione um sexo!", Toast.LENGTH_LONG ).show()
            }
        }
    }
}
