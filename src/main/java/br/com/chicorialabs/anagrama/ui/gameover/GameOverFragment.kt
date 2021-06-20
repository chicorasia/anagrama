package br.com.chicorialabs.anagrama.ui.gameover

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.chicorialabs.anagrama.R
import br.com.chicorialabs.anagrama.databinding.GameOverFragmentBinding
import br.com.chicorialabs.anagrama.ui.main.GameViewModel
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GameOverFragment : Fragment() {

    companion object {
        fun newInstance() = GameOverFragment()
    }

    private val binding: GameOverFragmentBinding by lazy {
        GameOverFragmentBinding.inflate(layoutInflater)
    }

    private lateinit var novoJogoBtn: MaterialButton

    private val mGameViewModel: GameViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Substituir a view inflada pelo binding.root
        val view = binding.root

        novoJogoBtn = view.findViewById(R.id.novoJogoBtn)

//        Vincular o viewModel e o lifecycleowner do binding
        binding.gameViewModel = mGameViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        novoJogoBtn.setOnClickListener {
            findNavController().navigate(R.id.action_gameOverFragment_to_mainFragment)
            mGameViewModel.novoJogo()
        }

    }

}