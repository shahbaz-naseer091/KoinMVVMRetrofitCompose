package com.shahbaz.test.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shahbaz.test.ui.viewmodel.TestViewModel
import org.koin.java.KoinJavaComponent.getKoin

class TestActivity : ComponentActivity() {

    val testViewModel : TestViewModel = getKoin().get()
    
    companion object{
        const val TAG = "TestActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NameInputScreen()
        }
        
        
        testViewModel.userNames.observe(this@TestActivity){
            Log.d(TAG, "onCreate: User names are $it")
        }
        
        testViewModel.currentTemperature.observe(this@TestActivity){
            Log.d(TAG, "onCreate: Current Temperature is $it")
        }
        

        getSecondLargestNo()


    }


    fun getSecondLargestNo(){

        val integersList = listOf(4,7,1,9,3,9,5)
        val sortedList = integersList.toSet().sortedDescending()

        val secondLargestNo = sortedList[1]

        Log.d(TAG, "Integers list: $integersList")
        Log.d(TAG, "Second Largest Number: $secondLargestNo")

    }


    @Composable
    fun NameInputScreen() {
 

        val name = remember { mutableStateOf("") }
        val displayedName = remember { mutableStateOf("") }

        Column(modifier = Modifier.padding(16.dp)) {
            TextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Enter your name") },
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Button(
                onClick = { displayedName.value = name.value },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Submit")
            }

            Text("Hello, ${displayedName.value}")
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        NameInputScreen()
    }
}

