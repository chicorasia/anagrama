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

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mGameViewModel: GameViewModel
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

        mGameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mGameViewModel.criaSegredo()

        mGameViewModel.desafio.observe(viewLifecycleOwner) { desafio ->
            desafioTv.setText(desafio)

        }


        enviarBtn.setOnClickListener {
            if (mGameViewModel.round.value == 10) {
                Toast.makeText(activity, "Jogo encerrado!", Toast.LENGTH_LONG).show()
                enviarBtn.text = getText(R.string.fim_de_jogo)
                // TODO: Navegar para o GameOverFragment e encerrar esse fragmento
                jogoEncerrado()
            }
            if (mGameViewModel.round.value!! <= 9) {
                val palpite = palpiteInputEdt.text.toString().toUpperCase()
                mGameViewModel.enviar(palpite)
                mGameViewModel.avancaRound()
                mGameViewModel.criaSegredo()
            }

        }

        mGameViewModel.score.observe(viewLifecycleOwner) {
            escoreTv.text = it.toString()
        }

        mGameViewModel.acerto.observe(viewLifecycleOwner, Observer<Boolean?> { ehAcerto ->
            when (ehAcerto) {
                true -> {
                    mostraToast("Parabéns, você acertou!")
                }
                false -> {
                    mostraToast("Lamento, você errou! A palavra era: ${mGameViewModel.segredo.value}")
                }
                null -> {
                }
            }
        })

    }

    private fun jogoEncerrado() {
        findNavController().navigate(R.id.action_mainFragment_to_gameOverFragment)
    }

    fun mostraToast(mensagem: String) {
        Toast.makeText(activity, mensagem, Toast.LENGTH_LONG).show()
    }

}