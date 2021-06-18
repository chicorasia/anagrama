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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val view = binding.root
        binding.mainViewModel = mMainViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mMainViewModel.criaSegredo()

        mMainViewModel.navegaParaGameOver.observe(viewLifecycleOwner) {
            it?.let{
                if (it){
                    navegaParaGameOver()
                }
                mMainViewModel.navegouParaGameOver()
            }
        }

//        mMainViewModel.acerto.observe(viewLifecycleOwner, Observer<Boolean?> { ehAcerto ->
//            when (ehAcerto) {
//                true -> {
//                    mostraToast(getString(R.string.mensagem_acerto))
//                }
//                false -> {
//                    mostraToast(getString(R.string.mensagem_erro))
//                }
//                null -> {
//                }
//            }
//        })

    }

    private fun navegaParaGameOver() {
        val pontuacao: Int = mMainViewModel.score.value ?: 0
        val direcao = MainFragmentDirections.actionMainFragmentToGameOverFragment(pontuacao)
        findNavController().navigate(direcao)
    }

    fun mostraToast(mensagem: String) {
        Toast.makeText(activity, mensagem, Toast.LENGTH_LONG).show()
    }

}