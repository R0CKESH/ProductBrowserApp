package sa.revest.productbrowser

import sa.revest.productbrowser.data.model.*

object TestData {

    val mockDimensions = Dimensions(1.0, 1.0, 1.0)
    val mockReview = Review(5, "Great!", "2025-01-01T00:00:00Z", "Test User", "test@user.com")
    val mockMeta = Meta("2025-01-01T00:00:00Z", "2025-01-01T00:00:00Z", "barcode", "qrcode")

    val mockProduct = Product(
        id = 1,
        title = "Test Product",
        description = "Desc",
        category = "Cat",
        price = 10.0,
        discountPercentage = 0.0,
        rating = 5.0,
        stock = 1,
        tags = listOf("tag1"),
        brand = "Brand",
        sku = "SKU123",
        weight = 1,
        dimensions = mockDimensions,
        warrantyInformation = "1 year",
        shippingInformation = "1 day",
        availabilityStatus = "In Stock",
        reviews = listOf(mockReview),
        returnPolicy = "30 days",
        minimumOrderQuantity = 1,
        meta = mockMeta,
        images = emptyList(),
        thumbnail = ""
    )

    val mockProductResponse = ProductListResponse(
        products = listOf(mockProduct),
        total = 1,
        skip = 0,
        limit = 1
    )

    val mockCategoriesResponse: List<CategoryModel> = listOf(
        CategoryModel(slug = "beauty", name = "Beauty", url = "url-1"),
        CategoryModel(slug = "fragrances", name = "Fragrances", url = "url-2")
    )
}