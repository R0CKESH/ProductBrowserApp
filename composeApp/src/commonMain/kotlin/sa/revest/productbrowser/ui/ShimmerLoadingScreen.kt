package sa.revest.productbrowser.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerLoadingScreen() {
    val shimmerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)

    LazyColumn(
        modifier = Modifier.shimmer(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(3) { // Show 3 shimmer category blocks
            Column {
                // Shimmer for Category Title
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .height(24.dp)
                        .width(150.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(shimmerColor)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Shimmer for Product Row
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(3) { // Show 3 shimmer cards per row
                        Column {
                            Box(
                                modifier = Modifier
                                    .width(180.dp)
                                    .height(180.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(shimmerColor)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Box(
                                modifier = Modifier
                                    .height(20.dp)
                                    .width(160.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(shimmerColor)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Box(
                                modifier = Modifier
                                    .height(18.dp)
                                    .width(100.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(shimmerColor)
                            )
                        }
                    }
                }
            }
        }
    }
}