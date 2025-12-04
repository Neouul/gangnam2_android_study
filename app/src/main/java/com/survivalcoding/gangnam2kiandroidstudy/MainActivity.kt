package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.MediumButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Tab
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.Gangnam2kiAndroidStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // 12월 4일 연습문제
            val ingredient = Ingredient(
                name = "Tomatos",
                imageUrl = "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
            )

            IngredientItem(
                ingredient = ingredient,
                quantity = "500g"
            )

        }
    }
}

@Composable
fun Exercise1204() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // button
        BigButton(
            text = "My Button",
            onClick = {
                println("클릭!!!!!!")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        MediumButton("Button")
        Spacer(modifier = Modifier.height(16.dp))
        SmallButton("Button")

        // input field
        Spacer(modifier = Modifier.height(32.dp))
        InputField(label = "Default")
        Spacer(modifier = Modifier.height(16.dp))
        InputField(
            label = "Focus",
            placeholder = {
                Text(
                    text = "클릭하면 focus 상태가 됩니다",
                    color = AppColors.gray4,
                    style = AppTextStyles.smallerTextRegular,
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        InputField(label = "Filled", value = "value")

        // tab
        Spacer(modifier = Modifier.height(32.dp))
        Tab(
            labels = listOf("label1", "label2"),
            selectedIndex = 0,
            onValueChange = {
                println("$it 번째 탭을 클릭했습니다")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Tab(
            labels = listOf("label1", "label2"),
            selectedIndex = 1,
            onValueChange = {
                println("$it 번째 탭을 클릭했습니다")
            }
        )
    }
}
