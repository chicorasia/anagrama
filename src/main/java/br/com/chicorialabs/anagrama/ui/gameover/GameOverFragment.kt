package br.com.chicorialabs.anagrama.ui.gameover

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.chicorialabs.anagrama.R
import com.google.android.material.button.MaterialButton

class GameOverFragment : Fragment() {

    companion object {
        fun newInstance() = GameOverFragment()
    }

    private lateinit var novoJogoBtn: MaterialButton

    private lateinit var viewModel: GameOverViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.game_over_fragment, container, false)

        novoJogoBtn = view.findViewById(R.id.novoJogoBtn)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        novoJogoBtn.setOnClickListener {
            findNavController().navigate(R.id.action_gameOverFragment_to_mainFragment)
        }


    }

}