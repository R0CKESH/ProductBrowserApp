package sa.revest.productbrowser

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import sa.revest.productbrowser.domain.usecase.GetProductsUseCase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetProductsUseCaseTest {

    @Test
    fun `invoke returns success with product list`() = runTest {
        // Arrange
        val mockRepository = MockProductRepository(
            productsResult = Result.success(TestData.mockProductResponse)
        )
        val useCase = GetProductsUseCase(mockRepository)

        // Act
        val result = useCase.invoke().first()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
        assertEquals("Test Product", result.getOrNull()?.first()?.title)
    }

    @Test
    fun `invoke returns failure on repository error`() = runTest {
        // Arrange
        val exception = RuntimeException("Network Error")
        val mockRepository = MockProductRepository(
            productsResult = Result.failure(exception)
        )
        val useCase = GetProductsUseCase(mockRepository)

        // Act
        val result = useCase.invoke().first()

        // Assert
        assertTrue(result.isFailure)
        assertEquals("Network Error", result.exceptionOrNull()?.message)
    }
}