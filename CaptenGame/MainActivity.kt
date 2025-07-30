package com.example.captengame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.captengame.ui.theme.CaptenGameTheme
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaptenGameTheme {
                Scaffold { paddingValues ->
                    // This padding ensures your UI respects safe areas
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        color = MaterialTheme.colorScheme.background
                    ) {
                       // CaptenGame()
                        UnitConverter()
                    }
                }
            }
        }
    }
}


@SuppressLint("AutoboxingStateCreation")
@Composable
fun UnitConverter(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var inputText by remember { mutableStateOf("") }  //here by directly set value otherwise = assign value
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableDoubleStateOf(1.00) }
    val oConversionFactor = remember { mutableDoubleStateOf(1.00) }

    //Unite Convert Function
    fun convertUnits(){
        val inputValueDouble = inputText.toDoubleOrNull()?: 0.0
        val result = (inputValueDouble*conversionFactor.value*100.0/oConversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter")
        Spacer(Modifier.height(16.dp))
        //Input Box
        OutlinedTextField(
            value = inputText,
            onValueChange = {inputText= it  },
            label = { Text("Enter value") }
        )
        Spacer(Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box {
                Button(onClick = {
                    iExpanded = true
                    //Toast.makeText(context, "First button clicked!", Toast.LENGTH_SHORT).show()
                }) {
                    Text(inputUnit)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded=false}) {
                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()

                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            convertUnits()

                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()

                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Milimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Milimeters"
                            conversionFactor.value = 0.001
                            convertUnits()

                        }
                    )
                }
            }

            //Output Button
            Box {
                Button(onClick = {
                    oExpanded=true
                    //Toast.makeText(context, "Second button clicked!", Toast.LENGTH_SHORT).show()
                }) {
                    Text(outputUnit)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {oExpanded=false}) {
                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor.value = 1.0
                            convertUnits()

                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            convertUnits()

                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Milimeters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Milimeters"
                            oConversionFactor.value = 0.001
                            convertUnits()

                        }
                    )
                }
            }
        }

        Text("Result:$outputValue $outputUnit ")
    }
}

//@Composable
//fun CaptenGame() {
//   // Text(text = "Welcome to Capten Game!", style = MaterialTheme.typography.headlineMedium)
//    Spacer(modifier = Modifier.height(16.dp))
//   // val treasureFound by remember { mutableStateOf(0) }
//    //By usingin keyword then treasureFound.value not work, directly treasureFound
//    val treasureFound = remember { mutableStateOf(0) }
//    val direction = remember{ mutableStateOf("North") }
//    val stromOrTreasure = remember { mutableStateOf("") }
//    Column {
//        Text(text = "Treasure Found: ${treasureFound.value}")
//        Text(text = "Direction Found: ${direction.value}")
//        Text(stromOrTreasure.value)
//        Button(
//            onClick = {
//                direction.value = "East"
//                if(Random.nextBoolean()){
//                    treasureFound.value += 1
//                    stromOrTreasure.value ="We found a Treasure!!!"
//                }else{
//                    stromOrTreasure.value = "Storm Ahead!!!"
//                }
//            }
//        ) {
//         Text(text = "Sail East")
//        }
//         Button(
//             onClick = {
//                 direction.value = "East"
//                 if(Random.nextBoolean()){
//                     treasureFound.value += 1
//                     stromOrTreasure.value ="We found a Treasure!!!"
//                 }else{
//                     stromOrTreasure.value = "Storm Ahead!!!"
//                 }
//             }
//        ) {
//         Text(text = "Sail West")
//        }
//        Button(
//            onClick = {
//                direction.value = "East"
//                if(Random.nextBoolean()){
//                    treasureFound.value += 1
//                    stromOrTreasure.value ="We found a Treasure!!!"
//                }else{
//                    stromOrTreasure.value = "Storm Ahead!!!"
//                }
//            }
//        ) {
//         Text(text = "Sail North")
//        }
//        Button(
//            onClick = {
//                direction.value = "East"
//                if(Random.nextBoolean()){
//                    treasureFound.value += 1
//                    stromOrTreasure.value ="We found a Treasure!!!"
//                }else{
//                    stromOrTreasure.value = "Storm Ahead!!!"
//                }
//            }
//        ) {
//         Text(text = "Sail South")
//        }
//
//
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun CaptenGamePreview() {
//    CaptenGameTheme {
//        CaptenGame()
//    }
//}

