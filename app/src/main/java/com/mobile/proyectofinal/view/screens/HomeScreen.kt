package com.mobile.proyectofinal.view.screens

import android.annotation.SuppressLint
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
import com.mobile.proyectofinal.model.enitty.News
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
import com.mobile.proyectofinal.view.ViewModelProvider
import com.mobile.proyectofinal.viewmodel.HomeNewsViewModel
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeNewsViewModel = viewModel(factory = ViewModelProvider.Factory)
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
    ) { innerPadding ->
        val newsList by viewModel.getNews("US", 100).observeAsState(initial = emptyList())
        HomeContent(
            innerPadding,
            newsList,
            viewModel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
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
        items(news) { news ->
            NewsItem(
                news,
                onFavouritesClick = {
                    coroutineScope.launch {
                        viewModel.insertFavouriteNew(news)
                    }
                })
        }
    }
}

@Composable
fun NewsItem(news: News, onFavouritesClick: () -> Unit) {
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
            NewsTitle(news)
        }
    }
}

@Composable
fun NewsTitle(news: News) {
    Text(
        maxLines = 3,
        text = news.title,
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

@Preview(showBackground = true)
@Composable
fun CardContentPreview() {
    NewsItem(
        news = News(
            1, "Home", "Content", "yo", "no", "none"
        ),
        onFavouritesClick = {}
    )
}
