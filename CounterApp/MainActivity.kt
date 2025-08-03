package com.example.countermvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.countermvvm.ui.theme.CounterMvvMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CounterMvvMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface (
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier.padding(innerPadding)
                    ){
                      CounterApp()
                    }
                }
            }
        }
    }
}

@Composable
fun CounterApp(){
    val counter = remember { mutableStateOf(0) }

    fun increement(){
        counter.value++
    }

    fun decreement(){
        counter.value--
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       Text(text = "Count: ${counter.value}",
           fontSize = 24.sp,
           fontWeight = FontWeight.Bold
           )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(
                onClick = {increement()}
            ) {
                Text(text ="Increment" )
            }
            Button(
                onClick = {decreement()}
            ) {
                Text(text ="Decrement" )
            }
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    CounterMvvMTheme {
//        Greeting("Android")
//    }
//}