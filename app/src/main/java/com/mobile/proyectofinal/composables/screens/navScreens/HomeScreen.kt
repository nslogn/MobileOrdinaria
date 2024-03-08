package com.mobile.proyectofinal.composables.screens.navScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import com.mobile.proyectofinal.model.News
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.Preview

import coil.compose.AsyncImage
import com.mobile.proyectofinal.viewmodel.NewsViewModel

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: NewsViewModel = NewsViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
    ) { innerPadding ->
        val newsList by viewModel.getNews("US", 50).observeAsState(initial = emptyList())
        HomeContent(innerPadding, newsList)
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
                contentDescription = "Image Description",
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
    news: List<News>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding)
    ) {
        items(news) { new ->
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                //.clickable {
                //navController.navigate("${Destinations.DETAILS_SCREEN}/${new.title}")
                //},
            ) {
                Column {
                    AsyncImage(
                        model = new.urlToImage,
                        placeholder = painterResource(id = R.drawable.placeholder),
                        contentDescription = "new image URL",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    Column(
                        Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = new.title,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = new.content ?: "",
                            maxLines = 3
                        )
                    }
                }
            }
        }
    }
}
