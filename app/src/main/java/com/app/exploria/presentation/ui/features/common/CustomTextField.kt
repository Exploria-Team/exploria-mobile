package com.app.exploria.presentation.ui.features.common

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.app.exploria.R

@Composable
fun CustomTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String,
    icon: ImageVector,
    isPassword: Boolean = false
) {
    val color = MaterialTheme.colorScheme
    val passwordVisible = remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "Textfield Icon",
                tint = Color.Gray
            )
        },
        label = { Text(text = label) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = color.background,
            unfocusedContainerColor = color.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        visualTransformation = if (isPassword && !passwordVisible.value) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                val visibilityIcon = if (passwordVisible.value) R.drawable.visibility else R.drawable.visibility_off
                val description = if (passwordVisible.value) "Hide password" else "Show password"
                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(
                        painter = painterResource(id = visibilityIcon),
                        contentDescription = description,
                        tint = Color.Gray
                    )
                }
            }
        },
        modifier = Modifier
            .border(
                width = 1.dp,
                color = color.onSurfaceVariant,
                shape = RoundedCornerShape(8.dp)
            )
    )
}
