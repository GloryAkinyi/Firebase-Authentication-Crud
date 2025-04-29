package com.glory.chromelabapp.ui.theme.screens.property

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.glory.chromelabapp.data.PropertyViewModel
import com.glory.chromelabapp.models.Property
import com.glory.chromelabapp.navigation.ROUT_VIEW_PROPERTY

@Composable
fun EditPropertyScreen(navController: NavHostController, propertyId: String) {
    val context = LocalContext.current
    val propertyViewModel = remember { PropertyViewModel(navController, context) }

    // State variables to hold form values
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    var isDataLoaded by remember { mutableStateOf(false) }

    // Load property data ONCE
    LaunchedEffect(propertyId) {
        if (!isDataLoaded) {
            propertyViewModel.getPropertyById(propertyId) { property ->
                property?.let {
                    title = it.title
                    description = it.description
                    price = it.price
                    location = it.location
                    isDataLoaded = true
                } ?: Toast.makeText(context, "Failed to load property", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Text(text = "Edit Property", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                propertyViewModel.updateProperty(propertyId, title, description, price, location)
                navController.navigate(ROUT_VIEW_PROPERTY)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Update Property")
        }
    }
}
