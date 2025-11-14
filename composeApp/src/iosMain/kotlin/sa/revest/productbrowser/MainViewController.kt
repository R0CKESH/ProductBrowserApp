package sa.revest.productbrowser

import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import sa.revest.productbrowser.presentation.root.DefaultRootComponent
import platform.UIKit.UIViewController
import sa.revest.productbrowser.App
import sa.revest.productbrowser.di.initKoin

@OptIn(ExperimentalDecomposeApi::class)
@Suppress("unused")
fun mainViewController(): UIViewController {

    initKoin()
    val lifecycle = LifecycleRegistry()

    val root = DefaultRootComponent(
        componentContext = DefaultComponentContext(lifecycle = lifecycle)
    )

    return ComposeUIViewController { App(root = root) }
}