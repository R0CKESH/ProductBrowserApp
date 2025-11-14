package sa.revest.productbrowser.presentation.detail


import com.arkivanov.decompose.ComponentContext
import sa.revest.productbrowser.data.model.Product

interface ProductDetailComponent {
    val product: Product
    fun onBackClicked()
}

class DefaultProductDetailComponent(
    private val componentContext: ComponentContext,
    override val product: Product,
    private val onBack: () -> Unit
) : ProductDetailComponent, ComponentContext by componentContext {

    override fun onBackClicked() {
        onBack()
    }
}