package sa.revest.productbrowser.data.repository


import sa.revest.productbrowser.data.api.ProductApiService
import sa.revest.productbrowser.data.model.CategoryModel
import sa.revest.productbrowser.data.model.ProductListResponse

class ProductRepositoryImpl(
    private val apiService: ProductApiService
) : ProductRepository {

    override suspend fun getProducts(): Result<ProductListResponse> {
        return try {
            val response = apiService.getProducts()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun searchProducts(query: String): Result<ProductListResponse> {
        return try {
            val response = apiService.searchProducts(query)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCategories(): Result<List<CategoryModel>> {
        return try {
            val response = apiService.getCategories()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getProductsByCategory(category: String): Result<ProductListResponse> {
        return try {
            val response = apiService.getProductsByCategory(category)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}