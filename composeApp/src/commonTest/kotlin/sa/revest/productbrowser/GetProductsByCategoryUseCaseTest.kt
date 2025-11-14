package sa.revest.productbrowser

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import sa.revest.productbrowser.domain.usecase.GetProductsByCategoryUseCase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetProductsByCategoryUseCaseTest {

    @Test
    fun `invoke returns success with product list`() = runTest {
        // Arrange
        val mockRepository = MockProductRepository(
            byCategoryResult = Result.success(TestData.mockProductResponse)
        )
        val useCase = GetProductsByCategoryUseCase(mockRepository)

        // Act
        val result = useCase.invoke("beauty").first()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
        assertEquals("Test Product", result.getOrNull()?.first()?.title)
    }

    @Test
    fun `invoke returns failure on repository error`() = runTest {
        // Arrange
        val exception = RuntimeException("By Category Error")
        val mockRepository = MockProductRepository(
            byCategoryResult = Result.failure(exception)
        )
        val useCase = GetProductsByCategoryUseCase(mockRepository)

        // Act
        val result = useCase.invoke("beauty").first()

        // Assert
        assertTrue(result.isFailure)
        assertEquals("By Category Error", result.exceptionOrNull()?.message)
    }
}