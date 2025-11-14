package sa.revest.productbrowser.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

fun getIconForCategory(category: String): ImageVector {
    return when (category) {
        "beauty" -> Icons.Default.Face
        "fragrances" -> Icons.Default.Spa
        "furniture" -> Icons.Default.Chair
        "groceries" -> Icons.Default.LocalGroceryStore
        "smartphones" -> Icons.Default.PhoneAndroid
        "laptops" -> Icons.Default.Laptop
        "skin-care" -> Icons.Default.Face
        "home-decoration" -> Icons.Default.Blender
        "tops", "womens-dresses", "mens-shirts" -> Icons.Default.Checkroom
        else -> Icons.Default.Category
    }
}