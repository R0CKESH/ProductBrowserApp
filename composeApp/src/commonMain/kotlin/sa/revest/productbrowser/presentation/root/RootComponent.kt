package sa.revest.productbrowser.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import sa.revest.productbrowser.data.model.Product
import sa.revest.productbrowser.domain.usecase.GetCategoriesUseCase
import sa.revest.productbrowser.domain.usecase.GetProductsByCategoryUseCase
import sa.revest.productbrowser.domain.usecase.GetProductsUseCase
import sa.revest.productbrowser.domain.usecase.SearchProductsUseCase
import sa.revest.productbrowser.presentation.detail.DefaultProductDetailComponent
import sa.revest.productbrowser.presentation.detail.ProductDetailComponent
import sa.revest.productbrowser.presentation.list.DefaultProductListComponent
import sa.revest.productbrowser.presentation.list.ProductListComponent

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    @Serializable
    sealed interface Config {
        @Serializable
        data object ProductList : Config

        @Serializable
        data class ProductDetail(val product: Product) : Config
    }

    sealed interface Child {
        data class ProductList(val component: ProductListComponent) : Child
        data class ProductDetail(val component: ProductDetailComponent) : Child
    }
}

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext, KoinComponent {

    // Inject use cases directly from Koin
    private val getProductsUseCase: GetProductsUseCase by inject()
    private val searchProductsUseCase: SearchProductsUseCase by inject()
    private val getCategoriesUseCase: GetCategoriesUseCase by inject()
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase by inject()

    private val navigation = StackNavigation<RootComponent.Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            serializer = RootComponent.Config.serializer(),
            initialConfiguration = RootComponent.Config.ProductList,
            handleBackButton = true,
            childFactory = ::createChild
        )

    private fun createChild(
        config: RootComponent.Config,
        context: ComponentContext
    ): RootComponent.Child {
        return when (config) {
            is RootComponent.Config.ProductList -> RootComponent.Child.ProductList(
                DefaultProductListComponent(
                    componentContext = context,
                    getProductsUseCase = getProductsUseCase,
                    searchProductsUseCase = searchProductsUseCase,
                    getCategoriesUseCase = getCategoriesUseCase,
                    getProductsByCategoryUseCase = getProductsByCategoryUseCase,
                    onProductSelected = { product ->
                        navigation.push(RootComponent.Config.ProductDetail(product))
                    }
                )
            )

            is RootComponent.Config.ProductDetail -> RootComponent.Child.ProductDetail(
                DefaultProductDetailComponent(
                    componentContext = context,
                    product = config.product,
                    onBack = { navigation.pop() }
                )
            )
        }
    }
}