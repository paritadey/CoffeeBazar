package presentation.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coffeebazar.composeapp.generated.resources.Res
import coffeebazar.composeapp.generated.resources.coffee
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import presentation.screen.home.HomeScreen


class WelcomeScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getScreenModel<WelcomeViewModel>()
        val show = remember { mutableStateOf(true) }
        /*Scaffold(
            topBar = {
                CenterAlignedTopAppBar(title = { Text(text = "") })
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navigator.push(HomeScreen()) },
                    shape = RoundedCornerShape(size = 12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = "Edit Icon"
                    )
                }
            }
        ){}*/
        MaterialTheme {
            Surface(modifier = Modifier.fillMaxSize().background(color= Color(0xffFFFFF))) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Image(painterResource(resource = Res.drawable.coffee), contentDescription = "Coffee icon")
                    TypewriterText(
                        texts = listOf(
                            "Hello Coffee-holics! 💜",
                            "Let's have a cup of coffee" ,
                            "And make your soul happy ! 👋 " ,
                            "\uD83E\uDD0E ☕ \uD83E\uDDCB "
                        ),
                    )
                    LaunchedEffect(key1 = Unit) {
                        delay(1500)
                        show.value = false
                    }
                    if(!show.value){
                        Spacer(modifier = Modifier.size(24.dp))
                        FloatingActionButton(
                            modifier = Modifier.width(200.dp).height(44.dp).background(color = Color(0xFFFE814B), shape = RoundedCornerShape(size = 12.dp)),
                            onClick = { navigator.push(HomeScreen()) },
                        ) {
                            Text(
                                text = "Click to Next",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TypewriterText(texts: List<String>,){
    var textIndex by remember {
        mutableStateOf(0)
    }
    var textToDisplay by remember {
        mutableStateOf("")
    }

    val textCharsList: List<List<String>> = remember {
        texts.map { text ->
            text.map {
                it.toString()
            }
        }
    }

    LaunchedEffect(
        key1 = texts,
    ) {
        while (textIndex < textCharsList.size) {
            textCharsList[textIndex].forEachIndexed { charIndex, _ ->
                textToDisplay = textCharsList[textIndex].take(n = charIndex,).joinToString(separator = "",)
                delay(160)
            }
            textIndex = (textIndex + 1) % texts.size
            delay(1000)
        }
    }

    Text(
        text = textToDisplay,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
    )
}