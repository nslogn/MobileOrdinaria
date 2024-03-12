package com.mobile.proyectofinal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.mobile.proyectofinal.AppViewModelProvider
import com.mobile.proyectofinal.R
import com.mobile.proyectofinal.data.enitty.News
import com.mobile.proyectofinal.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    //TODO: ViewMOdel News Observe
    val newsList = emptyList<News>()
    Scaffold(
        topBar = {
            TopBarCommon(
                imageResId = R.drawable.top_bar_font,
                contentDescription = "News WebView",
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /**TextField(
            value = searchQuery.value,
            onValueChange = { value -> searchQuery.value = value },
            label = { Text("Search") }
            )**/
            Button(onClick = {
                //TODO:
            }) {
                Text("Search")
            }
            LazyColumn {
                items(newsList) { newsItem ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            AsyncImage(
                                model = newsItem.urlToImage,
                                placeholder = painterResource(id = R.drawable.placeholder),
                                contentDescription = "new image URL",
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            NewsTitle(newsItem)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchContent(innerPadding: PaddingValues, viewModel: SearchViewModel) {

}

@Composable
fun SearchTextField(viewModel: SearchViewModel) {

}

@Composable
@Preview
fun SearchPreview() {
    SearchScreen()
}