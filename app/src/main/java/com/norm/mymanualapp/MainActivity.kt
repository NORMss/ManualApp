package com.norm.mymanualapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.norm.mymanualapp.ui.theme.MyManualAppTheme
import com.norm.mymanualapp.ui_components.MainTopBar

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyManualAppTheme {
                MainTopBar()
            }
        }
    }
}