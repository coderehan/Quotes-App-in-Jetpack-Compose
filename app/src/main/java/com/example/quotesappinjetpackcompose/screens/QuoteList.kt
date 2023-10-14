package com.example.quotesappinjetpackcompose.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.quotesappinjetpackcompose.model.Quote


@Composable
fun QuoteList(data: Array<Quote>, onClick: (quote: Quote)-> Unit) {
    LazyColumn(content = {
        items(data){
            QuoteListItem(quote = it, onClick)     // QuoteListItem is the item layout which we created for LazyColumn

        }
    })
}