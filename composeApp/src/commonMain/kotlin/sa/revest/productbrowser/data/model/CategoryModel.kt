package sa.revest.productbrowser.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryModel(
    val slug: String,
    val name: String,
    val url: String
)