package sa.revest.productbrowser

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import sa.revest.productbrowser.di.commonModule

class ProductBrowserApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ProductBrowserApp)
            modules(commonModule)
        }
    }
}