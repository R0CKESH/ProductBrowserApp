package sa.revest.productbrowser.presentation.list

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import sa.revest.productbrowser.data.model.CategoryModel
import sa.revest.productbrowser.data.model.Product
import sa.revest.productbrowser.domain.usecase.GetCategoriesUseCase
import sa.revest.productbrowser.domain.usecase.GetProductsByCategoryUseCase
import sa.revest.productbrowser.domain.usecase.GetProductsUseCase
import sa.revest.productbrowser.domain.usecase.SearchProductsUseCase

// Enum to manage which screen is visible
enum class ListDisplayMode {
    PRODUCTS,
    FILTERS
}

interface ProductListComponent {
    val state: StateFlow<ProductListState>
    fun onProductClicked(product: Product)
    fun onSearchQueryChanged(query: String)
    fun onCategorySelected(categorySlug: String)
    fun onFilterClicked()
    fun onCloseFilterScreen()
}

data class ProductListState(
    val displayMode: ListDisplayMode = ListDisplayMode.PRODUCTS,
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val filterCategories: List<CategoryModel> = emptyList(),
    val selectedFilterCategory: String = "All",
    val products: List<Product> = emptyList(),
    val categorizedProducts: Map<String, List<Product>> = emptyMap(),
    val error: String? = null
)

class DefaultProductListComponent(
    private val componentContext: ComponentContext,
    private val getProductsUseCase: GetProductsUseCase,
    private val searchProductsUseCase: SearchProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
    private val onProductSelected: (Product) -> Unit
) : ProductListComponent, ComponentContext by componentContext {

    private val _state = MutableStateFlow(ProductListState())
    override val state: StateFlow<ProductListState> = _state.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    init {
        loadCategories()
        loadProducts()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            getCategoriesUseCase()
                .onEach { result ->
                    result.onSuccess { cats ->
                        _state.update { it.copy(filterCategories = cats) }
                    }
                }
                .launchIn(this)
        }
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null, selectedFilterCategory = "All") }
            getProductsUseCase()
                .onEach { result ->
                    result.onSuccess { products ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                categorizedProducts = products.groupBy { it.category },
                                products = emptyList()
                            )
                        }
                    }
                    result.onFailure { error ->
                        _state.update { it.copy(isLoading = false, error = error.message) }
                    }
                }
                .launchIn(this)
        }
    }

    override fun onSearchQueryChanged(query: String) {
        _state.update {
            it.copy(
                isLoading = true,
                searchQuery = query,
                selectedFilterCategory = "All", // Reset filter
                error = null,
                displayMode = ListDisplayMode.PRODUCTS // Ensure we are on product list
            )
        }
        viewModelScope.launch {
            searchProductsUseCase(query)
                .onEach { result ->
                    result.onSuccess { products ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                products = products,
                                categorizedProducts = emptyMap()
                            )
                        }
                    }
                    result.onFailure { error ->
                        _state.update { it.copy(isLoading = false, error = error.message) }
                    }
                }
                .launchIn(this)
        }
    }

    override fun onCategorySelected(category: String) {
        // When a category is selected, update state and go back to product list
        _state.update {
            it.copy(
                isLoading = true,
                selectedFilterCategory = category,
                searchQuery = "",
                error = null,
                displayMode = ListDisplayMode.PRODUCTS // <-- Go back to product list
            )
        }
        if (category == "All") {
            loadProducts()
        } else {
            loadProductsByCategory(category)
        }
    }

    private fun loadProductsByCategory(category: String) {
        viewModelScope.launch {
            getProductsByCategoryUseCase(category)
                .onEach { result ->
                    result.onSuccess { products ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                products = products,
                                categorizedProducts = emptyMap()
                            )
                        }
                    }
                    result.onFailure { error ->
                        _state.update { it.copy(isLoading = false, error = error.message) }
                    }
                }
                .launchIn(this)
        }
    }

    override fun onProductClicked(product: Product) {
        onProductSelected(product)
    }

    override fun onFilterClicked() {
        _state.update { it.copy(displayMode = ListDisplayMode.FILTERS) }
    }

    override fun onCloseFilterScreen() {
        _state.update { it.copy(displayMode = ListDisplayMode.PRODUCTS) }
    }
}