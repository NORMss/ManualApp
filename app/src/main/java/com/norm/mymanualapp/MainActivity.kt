package com.norm.mymanualapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.norm.mymanualapp.ui.theme.MyManualAppTheme
import com.norm.mymanualapp.ui_components.MainTopBar

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val topBarTitle = remember {
                mutableStateOf("Jetpack Compose")
            }
            MyManualAppTheme {
                MainTopBar(topBarTitle.value)
            }
        }
    }
}