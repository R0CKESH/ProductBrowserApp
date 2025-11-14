package sa.revest.productbrowser.di

import org.koin.dsl.module
import sa.revest.productbrowser.data.api.ProductApiService
import sa.revest.productbrowser.data.repository.ProductRepository
import sa.revest.productbrowser.data.repository.ProductRepositoryImpl
import sa.revest.productbrowser.domain.usecase.GetCategoriesUseCase
import sa.revest.productbrowser.domain.usecase.GetProductsByCategoryUseCase
import sa.revest.productbrowser.domain.usecase.GetProductsUseCase
import sa.revest.productbrowser.domain.usecase.SearchProductsUseCase

val apiModule = module {
    single { ProductApiService() }
}

val repositoryModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
}

val useCaseModule = module {
    factory { GetProductsUseCase(get()) }
    factory { SearchProductsUseCase(get()) }
    factory { GetCategoriesUseCase(get()) }
    factory { GetProductsByCategoryUseCase(get()) }
}

val commonModule = module {
    includes(apiModule, repositoryModule, useCaseModule)
}