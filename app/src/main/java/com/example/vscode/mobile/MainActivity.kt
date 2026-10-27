package com.example.vscode.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vscode.mobile.ui.VSCodeMobileApp
import com.example.vscode.mobile.ui.theme.VSCodeMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VSCodeMobileTheme {
                VSCodeMobileApp()
            }
        }
    }
}