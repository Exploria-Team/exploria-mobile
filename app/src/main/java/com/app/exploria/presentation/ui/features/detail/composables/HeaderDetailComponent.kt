import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.exploria.presentation.ui.features.common.CustomButtonNavigation

@Composable
fun Toolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { /* TODO: Handle back action */ }) {
            CustomButtonNavigation(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
            )
        }

        Row {
            IconButton(onClick = { /* TODO: Handle favorite action */ }) {
                CustomButtonNavigation(
                    icon = Icons.Default.Favorite,
                )
            }

            IconButton(onClick = { /* TODO: Handle add action */ }) {
                CustomButtonNavigation(
                    icon = Icons.Default.AddCircle,
                )
            }
        }
    }
}
