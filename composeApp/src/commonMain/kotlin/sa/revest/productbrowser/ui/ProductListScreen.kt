package sa.revest.productbrowser.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sa.revest.productbrowser.data.model.Product
import sa.revest.productbrowser.presentation.list.ListDisplayMode
import sa.revest.productbrowser.presentation.list.ProductListComponent
import sa.revest.productbrowser.presentation.list.ProductListState

@Composable
fun ProductListScreen(component: ProductListComponent) {
    val state by component.state.collectAsState()

    //  logic to switch between screens ---
    when (state.displayMode) {
        ListDisplayMode.PRODUCTS -> {
            ProductListContent(
                component = component,
                state = state // Pass the state here
            )
        }
        ListDisplayMode.FILTERS -> {
            FilterScreen(
                categories = state.filterCategories,
                selectedCategory = state.selectedFilterCategory,
                onCategorySelected = component::onCategorySelected,
                onClose = component::onCloseFilterScreen
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductListContent(
    component: ProductListComponent,
    state: ProductListState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Browser") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            // --- Search Bar + Filter Button ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = state.searchQuery,
                    onValueChange = component::onSearchQueryChanged,
                    label = { Text("Search products...") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                IconButton(onClick = component::onFilterClicked) {
                    Icon(Icons.Default.Tune, contentDescription = "Filter")
                }
            }

            // --- Main Content Area ---
            when {
                state.isLoading -> ShimmerLoadingScreen()

                state.error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Error: ${state.error}", color = MaterialTheme.colorScheme.error)
                    }
                }

                // Show flat list for Search or Filter results
                state.products.isNotEmpty() -> {
                    SearchResultList(
                        products = state.products,
                        onProductClick = component::onProductClicked
                    )
                }

                // Show categorized view for main screen
                state.categorizedProducts.isNotEmpty() -> {
                    CategoryProductList(
                        categorizedProducts = state.categorizedProducts,
                        onProductClick = component::onProductClicked
                    )
                }

                // Empty state
                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No products found.")
                    }
                }
            }
        }
    }
}



@Composable
private fun CategoryProductList(
    categorizedProducts: Map<String, List<Product>>,
    onProductClick: (Product) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(categorizedProducts.entries.toList()) { (category, products) ->
            CategorySection(
                categoryName = category,
                products = products,
                onProductClick = onProductClick
            )
        }
    }
}

@Composable
private fun CategorySection(
    categoryName: String,
    products: List<Product>,
    onProductClick: (Product) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = categoryName.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products) { product ->
                ProductCard(
                    product = product,
                    onProductClick = { onProductClick(product) }
                )
            }
        }
    }
}

@Composable
private fun SearchResultList(
    products: List<Product>,
    onProductClick: (Product) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(products) { product ->
            ProductSearchItem(
                product = product,
                onProductClick = { onProductClick(product) }
            )
        }
    }
}