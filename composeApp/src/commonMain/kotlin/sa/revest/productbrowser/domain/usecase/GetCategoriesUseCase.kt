package sa.revest.productbrowser.domain.usecase


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sa.revest.productbrowser.data.model.CategoryModel
import sa.revest.productbrowser.data.repository.ProductRepository

class GetCategoriesUseCase(private val repository: ProductRepository) {
    operator fun invoke(): Flow<Result<List<CategoryModel>>> = flow {
        repository.getCategories()
            .onSuccess { emit(Result.success(it)) }
            .onFailure { emit(Result.failure(it)) }
    }
}