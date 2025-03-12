package com.shahbaz.learning.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RoundImage(pagerState: PagerState, imageUrls: List<String>) {
    Box(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .border(border = BorderStroke(2.dp, color = Color.Black), shape = CircleShape)
            .size(60.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = imageUrls[pagerState.currentPage],
            contentDescription = "Image ${pagerState.currentPage}",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.Gray, shape = CircleShape),
            placeholder = rememberAsyncImagePainter("https://example.com/placeholder.jpg"),
            error = rememberAsyncImagePainter("https://example.com/error.jpg")
        )
    }
}