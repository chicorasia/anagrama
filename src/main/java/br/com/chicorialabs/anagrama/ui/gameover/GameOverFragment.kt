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
import com.google.android.material.button.MaterialButton

class GameOverFragment : Fragment() {

    companion object {
        fun newInstance() = GameOverFragment()
    }

    private val argumentos by navArgs<GameOverFragmentArgs>()
    private lateinit var novoJogoBtn: MaterialButton
    private lateinit var gameOverScore: TextView
    private lateinit var mGameOverViewModel: GameOverViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.game_over_fragment, container, false)


        novoJogoBtn = view.findViewById(R.id.novoJogoBtn)
        gameOverScore = view.findViewById(R.id.gameOverScoreTv)

        mGameOverViewModel = ViewModelProvider(this).get(GameOverViewModel::class.java)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        novoJogoBtn.setOnClickListener {
            findNavController().navigate(R.id.action_gameOverFragment_to_mainFragment)
        }


        val pontuacaoFinal = argumentos.pontuacaoFinal
        mGameOverViewModel.setScore(pontuacaoFinal)

        mGameOverViewModel.score.observe(viewLifecycleOwner) {
            gameOverScore.text = it.toString()
        }
    }

}