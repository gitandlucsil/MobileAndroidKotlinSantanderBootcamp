package br.com.andlucsil.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.andlucsil.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    //DogCard(dog = Dog("lessi", "shitzu"))
    /*Button(onClick = {}, colors = ButtonDefaults.buttonColors(
        backgroundColor = Color.Yellow
    )) {
        Icon(painter = painterResource(id = R.drawable.ic_baseline_star_24),
            contentDescription = null,
            tint = Color.White)
        Text(text = "Confirmar",
            color = Color.White)
    }*/
    var clicks by remember { mutableStateOf(0) }
    Column() {
        ClickCounter(clicks = clicks) {
            clicks++
        }
        HelloContent()
    }
}

data class Dog (
    val name: String,
    val breed: String
    )
@Composable
private fun DogCard(dog: Dog) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.ic_dog_foreground),
            contentDescription = null,
            modifier = Modifier.size(72.dp)
        )
        Column() {
            Text(text = dog.name)
            Text(text = dog.breed)
        }
    }
}

@Composable
private fun TestingColumns() {
    Column(
        modifier = Modifier
            .background(Color.Red)
            .fillMaxSize()
    ) {
        Text(
            text = "Hello World",
            maxLines = 3,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .background(Color.Green)
                .fillMaxWidth()
        )
        Text(
            text = "Outro texto qualquer",
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
        )
        Row {
            Text(text = "Text 3", modifier = Modifier.padding(end = 30.dp))
            Text(text = "Text 4")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun ClickCounter(clicks: Int, onClick:() -> Unit) {
    Button(onClick = onClick) {
        Text("I've been clicked $clicks times")
    }
}


@Composable
fun HelloContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember { mutableStateOf("") }
        if (name.isNotEmpty()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}