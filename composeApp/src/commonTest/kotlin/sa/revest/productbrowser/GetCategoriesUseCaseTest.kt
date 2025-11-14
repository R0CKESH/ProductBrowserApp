package sa.revest.productbrowser


import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import sa.revest.productbrowser.domain.usecase.GetCategoriesUseCase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetCategoriesUseCaseTest {

    @Test
    fun `invoke returns success with category list`() = runTest {
        // Arrange
        val mockRepository = MockProductRepository(
            categoriesResult = Result.success(TestData.mockCategoriesResponse)
        )
        val useCase = GetCategoriesUseCase(mockRepository)

        // Act
        val result = useCase.invoke().first()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(2, result.getOrNull()?.size)
        assertEquals("beauty", result.getOrNull()?.first()?.slug)
        assertEquals("Beauty", result.getOrNull()?.first()?.name)
    }

    @Test
    fun `invoke returns failure on repository error`() = runTest {
        // Arrange
        val exception = RuntimeException("Category Error")
        val mockRepository = MockProductRepository(
            categoriesResult = Result.failure(exception)
        )
        val useCase = GetCategoriesUseCase(mockRepository)

        // Act
        val result = useCase.invoke().first()

        // Assert
        assertTrue(result.isFailure)
        assertEquals("Category Error", result.exceptionOrNull()?.message)
    }
}