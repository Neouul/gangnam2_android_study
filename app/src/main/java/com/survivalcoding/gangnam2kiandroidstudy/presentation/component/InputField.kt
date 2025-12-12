package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun InputField(
    label: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    placeholder: String = "Placeholder",
    value: String = "",
    onValueChange: (String) -> Unit = {},
) {
    Column(
        modifier = modifier
            .size(width = 315.dp, height = 81.dp)
            .background(
                color = AppColors.white,
            )
    ) {
        Column {
            Text(
                text = label,
                style = AppTextStyles.smallTextRegular
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                modifier = Modifier
                    .size(width = 315.dp, height = 55.dp)
                    .background(color = AppColors.white),
                value = value,
                placeholder = {
                    Text(
                        text = placeholder,
                        color = AppColors.gray4,
                        style = AppTextStyles.smallerTextRegular,
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AppColors.primary80,
                    unfocusedBorderColor = AppColors.gray4,
                ),
                shape = RoundedCornerShape(10.dp),
                onValueChange = onValueChange,
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            )
        }
    }
}

@Preview
@Composable
fun PreviewInputField() {
    InputField(
        label = "Label",
    )
}