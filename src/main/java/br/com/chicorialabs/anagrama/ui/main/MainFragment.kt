package br.com.chicorialabs.anagrama.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.chicorialabs.anagrama.databinding.MainFragmentBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val binding: MainFragmentBinding by lazy {
        MainFragmentBinding.inflate(layoutInflater)
    }

    private val mGameViewModel: GameViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = binding.root
        binding.gameViewModel = mGameViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mGameViewModel.criaSegredo()

        mGameViewModel.navegaParaGameOver.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    navegaParaGameOver()
                }
                mGameViewModel.navegouParaGameOver()
            }
        }

    }

    private fun navegaParaGameOver() {
        val direcao = MainFragmentDirections.actionMainFragmentToGameOverFragment()
        findNavController().navigate(direcao)
    }

}