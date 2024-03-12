package com.mobile.proyectofinal.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobile.proyectofinal.AppViewModelProvider
import com.mobile.proyectofinal.R
import com.mobile.proyectofinal.viewmodel.ReadViewModel
import kotlinx.coroutines.launch

@Composable
fun ReadNewsScreen(
    viewModel: ReadViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarCommon(
                imageResId = R.drawable.top_bar_font,
                contentDescription = "News Wev View",
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        },
    ) { innerPadding ->
        val readNews by viewModel.readNews.collectAsState(initial = emptyList())
        val context = LocalContext.current
        NewsContent(
            innerPadding = innerPadding,
            favouriteNews = readNews,
            onDeleteNews = { newsItem ->
                viewModel.viewModelScope.launch {
                    viewModel.deleteNews(newsItem)
                }
            },
            onComposeEmail = { newsItem ->
                composeEmail(context, newsItem.title, newsItem.url)
            }
        )
    }
}

@Composable
@Preview
fun SearchPreview() {
    ReadNewsScreen()
}