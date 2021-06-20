package br.com.chicorialabs.anagrama.ui.gameover

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.chicorialabs.anagrama.R
import br.com.chicorialabs.anagrama.databinding.GameOverFragmentBinding
import com.google.android.material.button.MaterialButton

class GameOverFragment : Fragment() {

    companion object {
        fun newInstance() = GameOverFragment()
    }

    private val binding: GameOverFragmentBinding by lazy {
        GameOverFragmentBinding.inflate(layoutInflater)
    }

//    TODO 007: instanciar o MainViewModel por meio do delegate sharedViewModel
//    TODO 008: eliminar referências ao GameOverViewModel e navArgs
    private val argumentos by navArgs<GameOverFragmentArgs>()
    private lateinit var novoJogoBtn: MaterialButton
    private lateinit var mGameOverViewModel: GameOverViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Substituir a view inflada pelo binding.root
        val view = binding.root

        novoJogoBtn = view.findViewById(R.id.novoJogoBtn)

//        Vincular o viewModel e o lifecycleowner do binding
        mGameOverViewModel = ViewModelProvider(this).get(GameOverViewModel::class.java)
        binding.gameOverViewModel = mGameOverViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        novoJogoBtn.setOnClickListener {
            findNavController().navigate(R.id.action_gameOverFragment_to_mainFragment)
        }

        val pontuacaoFinal = argumentos.pontuacaoFinal
        mGameOverViewModel.setScore(pontuacaoFinal)

    }

}