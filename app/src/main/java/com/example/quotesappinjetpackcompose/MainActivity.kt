package com.example.quotesappinjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quotesappinjetpackcompose.screens.QuoteDetail
import com.example.quotesappinjetpackcompose.screens.QuoteListScreen
import com.example.quotesappinjetpackcompose.ui.theme.QuotesAppInJetpackComposeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // When app is launched, first we will load data from DataManager and display in this main activity.
        // We will execute this in background thread of coroutine.
        CoroutineScope(Dispatchers.IO).launch {
            delay(5000)     // Data will come after 5 seconds of delay
            DataManager.loadAssetsFromFile(applicationContext)
        }

        setContent {
            QuotesAppInJetpackComposeTheme {
                App()
            }
        }
    }

    @Composable
    fun App() {
        if (DataManager.isDataLoaded.value) {    // If data loaded is true, we will show data in our composable view

            if(DataManager.currentPage.value == Pages.LISTING){
                QuoteListScreen(data = DataManager.data) {
                    DataManager.switchPages(it)
                }
            } else{
                DataManager.currentQuote?.let { QuoteDetail(quote = it) }
            }

        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize(1f)
            ) { // Box start
                Text(
                    text = "Loading...",
                    style = MaterialTheme.typography.bodyMedium
                )
            }       // Box end
        }
    }

    enum class Pages{
        LISTING,
        DETAIL
    }
}
