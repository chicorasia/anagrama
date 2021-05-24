package br.com.chicorialabs.anagrama.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.chicorialabs.anagrama.R
import com.google.android.material.button.MaterialButton

//TODO 003: Adicionar um argumento ao GameOverFragment no mobile_navigation.xml
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mMainViewModel: MainViewModel
    private lateinit var desafioTv: TextView
    private lateinit var palpiteInputEdt: EditText
    private lateinit var enviarBtn: MaterialButton
    private lateinit var resultadoTv: TextView
    private lateinit var escoreTv: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.main_fragment, container, false)
        desafioTv = view.findViewById(R.id.desafioTv)
        palpiteInputEdt = view.findViewById(R.id.palpiteInputEdt)
        enviarBtn = view.findViewById(R.id.enviarBtn)
        resultadoTv = view.findViewById(R.id.resultadoTv)
        escoreTv = view.findViewById(R.id.escoreTv)

        mMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mMainViewModel.criaSegredo()

        mMainViewModel.desafio.observe(viewLifecycleOwner) { desafio ->
            desafioTv.setText(desafio)

        }


        enviarBtn.setOnClickListener {
            if (mMainViewModel.round.value == 10) {
                Toast.makeText(activity, "Jogo encerrado!", Toast.LENGTH_LONG).show()
                enviarBtn.text = getText(R.string.fim_de_jogo)
                jogoEncerrado()
            }
            if (mMainViewModel.round.value!! <= 9) {
                val palpite = palpiteInputEdt.text.toString().toUpperCase()
                mMainViewModel.enviar(palpite)
                mMainViewModel.avancaRound()
                mMainViewModel.criaSegredo()
            }

        }

        mMainViewModel.score.observe(viewLifecycleOwner) {
            escoreTv.text = it.toString()
        }

        mMainViewModel.acerto.observe(viewLifecycleOwner, Observer<Boolean?> { ehAcerto ->
            when (ehAcerto) {
                true -> {
                    mostraToast("Parabéns, você acertou!")
                }
                false -> {
                    mostraToast("Lamento, você errou! A palavra era: ${mMainViewModel.segredo.value}")
                }
                null -> {
                }
            }
        })

    }

    private fun jogoEncerrado() {
//        TODO 004: Reescrever o método `jogoEncerrado()` para passar a pontuação
        findNavController().navigate(R.id.action_mainFragment_to_gameOverFragment)
    }

    fun mostraToast(mensagem: String) {
        Toast.makeText(activity, mensagem, Toast.LENGTH_LONG).show()
    }

}