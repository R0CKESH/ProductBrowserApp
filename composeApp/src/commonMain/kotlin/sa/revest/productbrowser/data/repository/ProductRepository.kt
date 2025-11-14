package sa.revest.productbrowser.data.repository

import sa.revest.productbrowser.data.model.CategoryModel
import sa.revest.productbrowser.data.model.ProductListResponse

interface ProductRepository {
    suspend fun getProducts(): Result<ProductListResponse>
    suspend fun searchProducts(query: String): Result<ProductListResponse>

    suspend fun getCategories(): Result<List<CategoryModel>>
    suspend fun getProductsByCategory(category: String): Result<ProductListResponse>
}