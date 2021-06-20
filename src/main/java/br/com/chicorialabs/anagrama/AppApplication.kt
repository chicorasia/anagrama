package br.com.chicorialabs.anagrama

import android.app.Application
import br.com.chicorialabs.anagrama.di.daoModules
import br.com.chicorialabs.anagrama.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@AppApplication)
            modules(viewModelModules)
            modules(daoModules)

        }
    }

}