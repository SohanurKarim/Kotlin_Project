package com.example.shopingapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

data class ShoppingItems(
    var Id: Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean=false,
){

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopingApp(){

    var sItems by remember{mutableStateOf(listOf<ShoppingItems>())}
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {showDialog=true},
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            //Lamda function
            //var doubleNumber : (Int) -> Int = {Int*2}
           // Text(doubleNumber(5).toString())

            Text(text = "Add Item")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(sItems) {
                item ->
                if(item.isEditing){
                   ShoppingItemEditor(
                       items = item, onEditComplete = { editedName, editedQuantity ->
                           sItems = sItems.map { it.copy(isEditing = false) }
                           val editedItems = sItems.find { it.Id == item.Id
                           }
                           editedItems?.let{
                               it.name = editedName
                               it.quantity = editedQuantity
                           }
                       },
                      // items = TODO()
                   )
                }else{
                    ShoppingListItem(
                        items =item, onEditClick={
                          sItems = sItems.map {it.copy(isEditing = it.Id==item.Id) }
                        },
                        onDeleteClick = {
                            sItems = sItems -item
                        }
                    )
                }
            }

        }

    }

    if (showDialog){
        AlertDialog(
            onDismissRequest = {showDialog=false},
            confirmButton = {
                Row (
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                  Button(
                      onClick = {
                          if (itemName.isNotBlank()){
                             val newItems = ShoppingItems(
                                 Id = sItems.size+1,
                                 name = itemName,
                                 quantity = itemQuantity.toInt()
                             )
                              sItems = sItems + newItems
                              showDialog = false
                              itemName = ""
                          }
                      }
                  ) {

                      Text(text = "Add")
                  }
                    Button(
                        onClick = {showDialog=false}
                    ) {
                        Text(text = "Cancel")
                    }
                }
            },
            title = {Text(text = "Adding Shopping Item")},
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = {itemName=it},
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                        )
                    OutlinedTextField(
                        value = itemQuantity,
                        onValueChange = {itemQuantity=it},
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                        )

                }
            }
        )

    }

}



@Composable
fun ShoppingItemEditor(
    items: ShoppingItems,
    onEditComplete: (String, Int) -> Unit,
  //  item: ShoppingItems
) {
    var editedName by remember { mutableStateOf(items.name) }
    var editedQuantity by remember { mutableStateOf(items.quantity.toString()) } // Convert to String for TextField
    var isEditing by remember { mutableStateOf(items.isEditing) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            OutlinedTextField(
                value = editedName,
                onValueChange = { editedName = it },
                label = { Text("Item Name") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
            OutlinedTextField(
                value = editedQuantity,
                onValueChange = { editedQuantity = it },
                label = { Text("Quantity") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
        }

        Button(
            onClick = {
                isEditing = false
                val quantity = editedQuantity.toIntOrNull() ?: 1
                onEditComplete(editedName, quantity)
            },
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = "Save")
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListItem(
    items: ShoppingItems,
    onEditClick:() -> Unit,
    onDeleteClick:() -> Unit,
){
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color(0xFF013258),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(8.dp), // Add internal padding inside the border
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        //Column {
            Text(text = items.name, modifier = Modifier.padding(8.dp))
            Text(text = "Qty: ${items.quantity}", modifier = Modifier.padding(8.dp))
      //  }

        Row {
            IconButton(onClick = onEditClick) {
                //ShoppingItemEditor()
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            }
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }

}
