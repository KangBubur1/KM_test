package com.example.km_test.ui.screens.screen1

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.km_test.R
import com.example.km_test.ui.screens.screen2.SecondScreen


@Composable
fun FirstScreen(
    navController: NavController,
    viewModel: FirstScreenViewModel = hiltViewModel()
) {
    var name by rememberSaveable { mutableStateOf("") }
    var palindrome by remember { mutableStateOf("") }
    var isPalindrome by remember { mutableStateOf(false) }
    var unjukHasil by remember { mutableStateOf(false) }

    val hasilPalindrome by viewModel.hasilPalindrome.observeAsState()

    if (hasilPalindrome != null) {
        isPalindrome = hasilPalindrome!!
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.sm_bg),
                contentDescription = "Background",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_photo),
                    contentDescription = "Photo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(114.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .height(50.dp)
                        .width(310.dp),
                    placeholder = {
                        Text(text = "Name", fontWeight = FontWeight.Light, color = Color.Gray)
                    }
                )

                TextField(
                    value = palindrome,
                    onValueChange = { palindrome = it },
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .height(50.dp)
                        .width(310.dp),
                    placeholder = {
                        Text(text = "Palindrome", fontWeight = FontWeight.Light, color = Color.Gray)
                    }
                )

                if (unjukHasil) {
                    Text(
                        text = if (isPalindrome) "True" else "False.",
                        color = if (isPalindrome) Color.Green else Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .width(310.dp),
                    shape = RoundedCornerShape(12.dp),
                    onClick = {
                        viewModel.cekPalindrome(palindrome)
                        unjukHasil = true
                    }) {
                    Text(text = "CHECK")
                }

                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .width(310.dp),
                    shape = RoundedCornerShape(12.dp),
                    onClick = {
                        viewModel.setName(name)
                        Log.d("FirstScreen", "Navigating to SecondScreen with name: $name")
                        navController.navigate("second_screen")
                    }) {
                    Text(text = "NEXT")
                }
            }
        }
    }
}






