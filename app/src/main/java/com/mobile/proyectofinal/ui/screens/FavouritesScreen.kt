package com.mobile.proyectofinal.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.mobile.proyectofinal.R
import com.mobile.proyectofinal.data.enitty.News
import com.mobile.proyectofinal.ui.ViewModelProvider
import com.mobile.proyectofinal.viewmodel.FavouritesViewModel

@Composable
fun FavouritesScreen(
    viewModel: FavouritesViewModel = viewModel(factory = ViewModelProvider.Factory)
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
    ) { innerPadding ->
        val favouriteNews by viewModel.favouritesNews.collectAsState(initial = emptyList())
        FavouritesContent(innerPadding, favouriteNews)
    }
}

@Composable
fun FavouritesContent(innerPadding: PaddingValues, favouriteNews: List<News>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding)
    ) {
        items(favouriteNews) { news ->
            FavouritesNewsItem(news)
        }
    }
}

@Composable
fun FavouritesNewsItem(news: News) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                //navController.navigate("${Destinations.DETAILS_SCREEN}/${new.title}")
            },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomStart
        ) {
            AsyncImage(
                model = news.urlToImage,
                placeholder = painterResource(id = R.drawable.placeholder),
                contentDescription = "new image URL",
                modifier = Modifier
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black.copy(alpha = 0.8f))
            )
            NewsTitle(news)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Image(
                painter = painterResource(id = R.drawable.top_bar_font),
                contentDescription = "Local Image/Title/Dodgers Font",
                modifier = Modifier.size(180.dp)
            )
        }
    )
}

@Composable
@Preview(showBackground = true)
fun FavouritesPreview() {
    FavouritesScreen()
}