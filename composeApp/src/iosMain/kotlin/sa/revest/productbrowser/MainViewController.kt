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
fun MainViewController(): UIViewController {
// 1. Initialize Koin (Dependency Injection)
    initKoin()

    // 2. Create the LifecycleRegistry for the component
    val lifecycle = LifecycleRegistry()

    // 3. Create the Root Component manually
    // We don't need 'retainedComponent' here because the UIViewController
    // itself retains this instance in memory.
    val root = DefaultRootComponent(
        componentContext = DefaultComponentContext(lifecycle = lifecycle)
    )

    // 4. Return the Compose UI
    return ComposeUIViewController {
        App(root = root)
    }
}