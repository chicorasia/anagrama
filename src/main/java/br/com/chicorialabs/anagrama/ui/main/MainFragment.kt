package br.com.chicorialabs.anagrama.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mGameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        mGameViewModel.criaSegredo()

        mGameViewModel.desafio.observe(viewLifecycleOwner) { desafio ->
            desafioTv.setText(desafio)

        }

        enviarBtn.setOnClickListener {
            val palpite = palpiteInputEdt.text.toString().toUpperCase()
            mGameViewModel.enviar(palpite)
            mGameViewModel.avancaRound()
            mGameViewModel.criaSegredo()
            if (mGameViewModel.round.value == 10){
               mGameViewModel.novoJogo()
               mGameViewModel.criaSegredo()
            }
        }

        mGameViewModel.score.observe(viewLifecycleOwner) {
            escoreTv.text = it.toString()
        }

        mGameViewModel.round.observe(viewLifecycleOwner) {
            if (it == 10) {
                resultadoTv.text = "Fim de jogo!"
            }
        }

        mGameViewModel.acerto.observe(viewLifecycleOwner, Observer<Boolean> { ehAcerto ->
            when (ehAcerto) {
                true -> {
                    resultadoTv.text = "Parabéns, você acertou!"
                    resultadoTv.visibility = View.VISIBLE
                }
                false -> {
                    resultadoTv.text = "Lamento, você errou!"
                    resultadoTv.visibility = View.VISIBLE
                }
                null -> {
                    resultadoTv.visibility = View.INVISIBLE
                }
            }
        })

    }

}