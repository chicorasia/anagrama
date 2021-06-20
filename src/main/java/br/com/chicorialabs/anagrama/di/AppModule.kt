package br.com.chicorialabs.anagrama.di

import br.com.chicorialabs.anagrama.dao.PalavraDao
import br.com.chicorialabs.anagrama.ui.main.GameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { GameViewModel(get()) }
}

val daoModules = module {
    single { PalavraDao() }
}
