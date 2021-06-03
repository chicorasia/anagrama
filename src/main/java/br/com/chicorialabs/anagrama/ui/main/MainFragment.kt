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
import br.com.chicorialabs.anagrama.databinding.MainFragmentBinding
import com.google.android.material.button.MaterialButton

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val binding: MainFragmentBinding by lazy {
        MainFragmentBinding.inflate(layoutInflater)
    }

    private lateinit var mMainViewModel: MainViewModel
    private lateinit var palpiteInputEdt: EditText
    private lateinit var enviarBtn: MaterialButton
    private lateinit var resultadoTv: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val view = binding.root
        binding.mainViewModel = mMainViewModel
        binding.lifecycleOwner = viewLifecycleOwner

//        TODO 006: eliminar Edt e Btn
        palpiteInputEdt = view.findViewById(R.id.palpiteInputEdt)
        enviarBtn = view.findViewById(R.id.enviarBtn)
        resultadoTv = view.findViewById(R.id.resultadoTv)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mMainViewModel.criaSegredo()


// TODO 003: Mover esses comportamentos para para função enviaPalpite() do ViewModel
        enviarBtn.setOnClickListener {
            if (mMainViewModel.round.value == 10) {
                mMainViewModel.jogoEncerrado()
            }
            if (mMainViewModel.round.value!! <= 9) {
                val palpite = palpiteInputEdt.text.toString().toUpperCase()
                mMainViewModel.enviar(palpite)
                mMainViewModel.avancaRound()
                mMainViewModel.criaSegredo()
            }

        }

        mMainViewModel.navegaParaGameOver.observe(viewLifecycleOwner) {
            it?.let{
                if (it == true){
                    navegaParaGameOver()
                }
                mMainViewModel.navegouParaGameOver()
            }
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

    private fun navegaParaGameOver() {
        val pontuacao: Int = mMainViewModel.score.value ?: 0
        val direcao = MainFragmentDirections.actionMainFragmentToGameOverFragment()
        direcao.pontuacaoFinal = pontuacao
        findNavController().navigate(direcao)
    }

    fun mostraToast(mensagem: String) {
        Toast.makeText(activity, mensagem, Toast.LENGTH_LONG).show()
    }

}