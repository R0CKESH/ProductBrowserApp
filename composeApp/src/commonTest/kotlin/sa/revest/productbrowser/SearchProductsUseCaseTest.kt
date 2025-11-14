package sa.revest.productbrowser


import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import sa.revest.productbrowser.domain.usecase.SearchProductsUseCase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SearchProductsUseCaseTest {

    @Test
    fun `invoke returns success with search results`() = runTest {
        // Arrange
        val mockRepository = MockProductRepository(
            searchResult = Result.success(TestData.mockProductResponse)
        )
        val useCase = SearchProductsUseCase(mockRepository)

        // Act
        val result = useCase.invoke("test").first()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
    }

    @Test
    fun `invoke returns failure on repository error`() = runTest {
        // Arrange
        val exception = RuntimeException("Search Error")
        val mockRepository = MockProductRepository(
            searchResult = Result.failure(exception)
        )
        val useCase = SearchProductsUseCase(mockRepository)

        // Act
        val result = useCase.invoke("test").first()

        // Assert
        assertTrue(result.isFailure)
        assertEquals("Search Error", result.exceptionOrNull()?.message)
    }
}