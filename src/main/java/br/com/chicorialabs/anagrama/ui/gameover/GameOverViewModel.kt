package br.com.chicorialabs.anagrama.ui.gameover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameOverViewModel : ViewModel() {

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    fun setScore(pontos: Int) { _score.value = pontos }

}