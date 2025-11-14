package sa.revest.productbrowser.domain.usecase


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sa.revest.productbrowser.data.model.Product
import sa.revest.productbrowser.data.repository.ProductRepository

class GetProductsByCategoryUseCase(private val repository: ProductRepository) {
    operator fun invoke(category: String): Flow<Result<List<Product>>> = flow {
        repository.getProductsByCategory(category)
            .onSuccess { emit(Result.success(it.products)) }
            .onFailure { emit(Result.failure(it)) }
    }
}