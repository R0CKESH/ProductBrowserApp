package sa.revest.productbrowser


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import sa.revest.productbrowser.presentation.root.RootComponent
import sa.revest.productbrowser.ui.ProductDetailScreen
import sa.revest.productbrowser.ui.ProductListScreen

@Composable
fun App(root: RootComponent) {
    MaterialTheme {
        val stack by root.stack.subscribeAsState()

        Children(
            stack = stack,
            animation = stackAnimation(slide())
        ) { child ->
            when (val instance = child.instance) {
                is RootComponent.Child.ProductList -> {
                    ProductListScreen(instance.component)
                }
                is RootComponent.Child.ProductDetail -> {
                    ProductDetailScreen(instance.component)
                }
            }
        }
    }
}