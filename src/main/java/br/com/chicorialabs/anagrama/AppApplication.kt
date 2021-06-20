package br.com.chicorialabs.anagrama

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@AppApplication)
//            TODO 005: adicionar os módulos

        }
    }

}