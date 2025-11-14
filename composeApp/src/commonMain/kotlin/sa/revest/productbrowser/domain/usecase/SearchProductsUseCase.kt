package sa.revest.productbrowser.domain.usecase


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sa.revest.productbrowser.data.model.Product
import sa.revest.productbrowser.data.repository.ProductRepository

class SearchProductsUseCase(private val repository: ProductRepository) {
    operator fun invoke(query: String): Flow<Result<List<Product>>> = flow {
        repository.searchProducts(query)
            .onSuccess { emit(Result.success(it.products)) }
            .onFailure { emit(Result.failure(it)) }
    }
}