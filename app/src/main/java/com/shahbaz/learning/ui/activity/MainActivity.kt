package com.shahbaz.learning.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.shahbaz.learning.ui.components.RoundImage
import com.shahbaz.learning.ui.viewmodel.MainViewModel
import com.shahbaz.learning.utils.formatToTwoDecimalPlaces
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.shahbaz.learning.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.getKoin


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }

}


@Composable
fun MainScreen() {

    val mainViewModel: MainViewModel = getKoin().get()

    val scrollState = rememberScrollState()
    val snackBarHostState = remember { SnackbarHostState() }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.White, darkIcons = true)

    LaunchedEffect(Dispatchers.IO) {
        mainViewModel.getData()
    }

    Scaffold(
        topBar = { Toolbar(snackBarHostState, mainViewModel) },
        bottomBar = {
            if (mainViewModel.isLoading.value == false)
                BottomView(snackBarHostState)
        },
        containerColor = Color.White,
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            if (mainViewModel.isLoading.value == true) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalSliderWithIndicator(mainViewModel)
                    Spacer(modifier = Modifier.height(16.dp))
                    LearnMore()
                    Spacer(modifier = Modifier.height(16.dp))
                    Quantity()
                    Spacer(modifier = Modifier.height(16.dp))
                    Information(mainViewModel)
                }
            }

        }
    }
}


@Composable
fun Toolbar(snackBarHostState: SnackbarHostState, mainViewModel: MainViewModel) {

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(start = 20.dp, end = 20.dp),
        contentAlignment = Alignment.Center
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_main_arrow_back),
                contentDescription = "VectorDrawable",
                Modifier.clickable {
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar("Back button tapped")
                    }
                }
            )

            val title = mainViewModel.data.value?.data?.name ?: "Please wait..."

            Text(
                text = title, textAlign = TextAlign.Center, style = TextStyle(
                    color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Medium
                ), modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(5.dp))

            Icon(
                painter = painterResource(id = R.drawable.ic_main_favorite),
                contentDescription = "VectorDrawable",
                Modifier.clickable {
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar("Favorite button tapped")
                    }
                }
            )

            Spacer(modifier = Modifier.width(5.dp))

            Icon(
                painter = painterResource(id = R.drawable.ic_main_share),
                contentDescription = "VectorDrawable",
                Modifier.clickable {
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar("Share button tapped")
                    }
                }
            )

            Spacer(modifier = Modifier.width(5.dp))

            Icon(
                painter = painterResource(id = R.drawable.ic_main_cart),
                contentDescription = "VectorDrawable",
                Modifier.clickable {
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar("Cart button tapped")
                    }
                }
            )

        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalSliderWithIndicator(mainViewModel: MainViewModel) {

    val totalPages = 5

    val pagerState = rememberPagerState()

    val scrollState = rememberScrollState()


    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            count = totalPages, state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .padding()
        ) { page ->

            //top image
            AsyncImage(
                model = mainViewModel.data.value?.data?.images?.get(page),
                contentDescription = "Image $page",
                modifier = Modifier.fillMaxWidth(),
                placeholder = rememberAsyncImagePainter(model = Color.White),
                error = rememberAsyncImagePainter("https://example.com/error.jpg"),
                contentScale = ContentScale.Crop
            )

        }

        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(totalPages) { pageIndex ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(12.dp)
                        .background(
                            if (pagerState.currentPage == pageIndex) Color.Black else Color(
                                0xFFD4BE85
                            ), shape = CircleShape
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            val brandName = mainViewModel.data.value?.data?.brandName.toString()

            //branch name
            Text(
                text = brandName.ifEmpty { "ANESTHESIA" }, style = TextStyle(
                    fontSize = 12.sp, color = Color.Black, fontWeight = FontWeight.Bold
                )
            )

            val price = mainViewModel.data.value?.data?.price?.formatToTwoDecimalPlaces() + " KWD"

            //price
            Text(
                text = price, style = TextStyle(
                    fontSize = 14.sp, color = Color.Black, fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        //name
        Text(
            text = mainViewModel.data.value?.data?.name.toString(),
            style = TextStyle(fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Medium),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        //sku
        Text(
            text = "SKU: " + mainViewModel.data.value?.data?.sku.toString(),
            style = TextStyle(fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Normal),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        //color label
        Text(
            text = "Color:",
            style = TextStyle(fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Medium),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        //indicator
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState), horizontalArrangement = Arrangement.Start
        ) {
            repeat(totalPages) { pageIndex ->
                mainViewModel.data.value?.data?.images?.let {
                    RoundImage(
                        pagerState = pagerState,
                        imageUrls = it
                    )
                }
            }
        }

    }
}

@Composable
fun LearnMore() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(start = 16.dp, end = 16.dp)
            .border(
                BorderStroke(width = 2.dp, color = Color.LightGray),
                shape = RoundedCornerShape(8.dp)
            )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp, end = 8.dp)
                    .align(Alignment.CenterVertically), verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "or 4 interest-free payments",
                    style = TextStyle(color = Color.Black, fontWeight = FontWeight.Medium)
                )

                Row {

                    Text(
                        text = "0.88KW",
                        style = TextStyle(color = Color.Black, fontWeight = FontWeight.Medium)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "Learn More",
                        textDecoration = TextDecoration.Underline,
                        style = TextStyle(color = Color.Black, fontWeight = FontWeight.Normal)
                    )

                }

            }

            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(25.dp)
                    .padding(end = 8.dp)
                    .background(Color(0xFF67E5BD), shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "tabby",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
            }

        }

    }

}

@Composable
fun Quantity() {

    val quantity = remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {

        Text(
            text = "Quantity",
            style = TextStyle(fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {

            Box(
                Modifier
                    .size(35.dp)
                    .background(color = Color.Gray)
                    .clickable {
                        if (quantity.intValue > 0) {
                            quantity.intValue -= 1
                        }
                    }, contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "-",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                Modifier
                    .size(35.dp)
                    .border(
                        BorderStroke(2.dp, color = Color.Gray),
                        shape = RoundedCornerShape(2.dp)
                    ), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "" + quantity.intValue,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                Modifier
                    .size(35.dp)
                    .background(color = Color.Black)
                    .clickable {
                        quantity.intValue += 1
                    }, contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }

        }

    }

}

@Composable
fun Information(mainViewModel: MainViewModel) {

    val informationVisibility = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    informationVisibility.value = informationVisibility.value.not()
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "PRODUCT INFORMATION",
                style = TextStyle(fontSize = 14.sp, color = Color.Black)
            )
            Icon(
                painter = painterResource(id = if (informationVisibility.value) R.drawable.ic_main_arrow_up else R.drawable.ic_main_arrow_down),
                contentDescription = "VectorDrawable",
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        if (informationVisibility.value) {

            val htmlText = HtmlCompat.fromHtml(
                mainViewModel.data.value?.data?.description.toString(),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )

            val annotatedString = buildAnnotatedString {
                append(htmlText.toString())
            }

            Text(
                style = TextStyle(fontSize = 14.sp, color = Color.Gray),
                text = annotatedString
            )
        }

    }

}

@Composable
fun BottomView(snackbarHostState: SnackbarHostState) {

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White)
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .background(color = Color.Black), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Add to bag",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ), modifier = Modifier.clickable {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Add to bag button tapped")
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .border(BorderStroke(2.dp, color = Color.Black)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Share",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.clickable {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Share button tapped")
                        }
                    }
                )
            }

        }

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}