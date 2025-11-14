package sa.revest.productbrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.retainedComponent
import sa.revest.productbrowser.presentation.root.DefaultRootComponent
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val root = retainedComponent { componentContext: ComponentContext ->
            DefaultRootComponent(componentContext)
        }
        setContent {
            App(root = root)        }
    }
}

