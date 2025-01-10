package com.dmuhia.composefastlane

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dmuhia.composefastlane.ui.theme.ComposeFastlaneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //FlavorUtils.printFlavorName()
        enableEdgeToEdge()

        setContent {
            ComposeFastlaneTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = stringResource(R.string.feature),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "$name!",
            modifier = modifier
                .padding(start = 16.dp, top = 16.dp)

        )
        Image(
            painter = painterResource(id = R.drawable.ic_add), // This resolves the flavor-specific image
            contentDescription = "Logo",
            modifier = Modifier.fillMaxSize() // You can customize the modifier
        )
//        if (BuildConfig.FLAVOR == "prod") {
//            Text(
//                text = "Welcome to Premium!",
//                modifier = modifier
//                    .padding(start = 16.dp)
//
//            )
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeFastlaneTheme {
        Greeting(stringResource(R.string.feature))
    }
}