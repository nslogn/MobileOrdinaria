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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.proyectofinal.R
import com.mobile.proyectofinal.data.enitty.News
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

import coil.compose.AsyncImage
import com.mobile.proyectofinal.AppViewModelProvider
import com.mobile.proyectofinal.viewmodel.HomeNewsViewModel
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun HomeScreen(
    viewModel: HomeNewsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarWithMenu() },
    ) { innerPadding ->
        val newsList by viewModel.getNews("US", 100).observeAsState(initial = emptyList())
        HomeContent(
            innerPadding,
            newsList,
            viewModel
        )
    }
}

@Composable
fun HomeContent(
    innerPadding: PaddingValues,
    news: List<News>,
    viewModel: HomeNewsViewModel
) {
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding)
    ) {
        items(news) { newsItem ->
            @Suppress("SENSELESS_COMPARISON")
            if (newsItem.title != null && newsItem.title != "[Removed]" && newsItem.urlToImage != null) {
                NewsItem(
                    newsItem,
                    onFavouritesClick = {
                        coroutineScope.launch {
                            viewModel.insertFavouriteNew(newsItem)
                        }
                    })
            }
        }
    }
}

@Composable
fun NewsItem(newsItem: News, onFavouritesClick: () -> Unit) {
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
                model = newsItem.urlToImage,
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
            @Suppress("SENSELESS_COMPARISON")
            if (newsItem.url != null && newsItem.urlToImage != null) {
                IconButton(
                    modifier = Modifier.align(Alignment.TopEnd),
                    onClick = onFavouritesClick
                ) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Favourites Icon",
                        tint = Color.White
                    )
                }
            }
            NewsTitle(newsItem)
        }
    }
}

@Composable
fun NewsTitle(newsItem: News) {
    Text(
        maxLines = 3,
        text = newsItem.title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        style = TextStyle(
            shadow = Shadow(
                color = Color.Gray,
                blurRadius = 4f,
                offset = Offset(2f, 2f)
            ),
            fontFamily = FontFamily.Serif,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithMenu() {
    CenterAlignedTopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Image(
                painter = painterResource(id = R.drawable.top_bar_font),
                contentDescription = "Local Image/Title/Dodgers Font",
                modifier = Modifier.size(180.dp)
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { /* do something */ }
            ) {
                Icon(Icons.Filled.Menu, contentDescription = "Localized description")
            }
        },
        actions = {
            IconButton(
                onClick = { /* do something */ }
            ) {
                Icon(Icons.Filled.Settings, contentDescription = "Localized description")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CardContentPreview() {
    NewsItem(
        newsItem = News(
            1, "Home", "Content", "yo", "no", "none"
        ),
        onFavouritesClick = {}
    )
}
