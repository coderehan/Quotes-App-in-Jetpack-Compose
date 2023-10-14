package com.example.quotesappinjetpackcompose

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.quotesappinjetpackcompose.model.Quote
import com.google.gson.Gson

// This class is responsible for reading the json data from the assets folder
object DataManager {

    // We need one variable to store data after reading file
    var data = emptyArray<Quote>()      // First we will initialize with empty array. Once data is read is from file, we will store all data in this variable.
    var currentQuote: Quote? = null
    var currentPage = mutableStateOf(MainActivity.Pages.LISTING)
    var isDataLoaded =
        mutableStateOf(false)        // Initially we will make it false which means our data is not loaded from file.

    fun loadAssetsFromFile(context: Context) {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()       // With the help of gson, we will convert json into kotlin objects
        data = gson.fromJson(json, Array<Quote>::class.java)
        isDataLoaded.value = true       // Now we will make it true which means our data is loaded
    }

    fun switchPages(quote: Quote?) {
        if (currentPage.value == MainActivity.Pages.LISTING) {    // If you are in listing page, you can switch to detail page
            currentQuote = quote
            currentPage.value == MainActivity.Pages.DETAIL
        } else {
            currentPage.value == MainActivity.Pages.LISTING     // If you are in detail page, you can switch to listing page
        }
    }
}