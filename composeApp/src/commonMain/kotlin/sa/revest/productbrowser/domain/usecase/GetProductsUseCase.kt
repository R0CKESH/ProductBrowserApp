package sa.revest.productbrowser.domain.usecase


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sa.revest.productbrowser.data.model.Product
import sa.revest.productbrowser.data.repository.ProductRepository

class GetProductsUseCase(private val repository: ProductRepository) {
    operator fun invoke(): Flow<Result<List<Product>>> = flow {
        repository.getProducts()
            .onSuccess { emit(Result.success(it.products)) }
            .onFailure { emit(Result.failure(it)) }
    }
}