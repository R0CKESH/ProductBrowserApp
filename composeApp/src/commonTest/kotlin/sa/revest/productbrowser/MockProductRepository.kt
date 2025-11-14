package sa.revest.productbrowser


import sa.revest.productbrowser.data.model.ProductListResponse
import sa.revest.productbrowser.data.repository.ProductRepository

class MockProductRepository(
    private val productsResult: Result<ProductListResponse>? = null,
    private val searchResult: Result<ProductListResponse>? = null,
    private val categoriesResult: Result<List<String>>? = null,
    private val byCategoryResult: Result<ProductListResponse>? = null
) : ProductRepository {

    override suspend fun getProducts(): Result<ProductListResponse> {
        return productsResult ?: error("getProducts() result not set in mock")
    }

    override suspend fun searchProducts(query: String): Result<ProductListResponse> {
        return searchResult ?: error("searchProducts() result not set in mock")
    }

    override suspend fun getCategories(): Result<List<String>> {
        return categoriesResult ?: error("getCategories() result not set in mock")
    }

    override suspend fun getProductsByCategory(category: String): Result<ProductListResponse> {
        return byCategoryResult ?: error("getProductsByCategory() result not set in mock")
    }
}