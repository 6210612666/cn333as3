package com.example.numberguessinggamevercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.Text as Text
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                var num = randomNumber()
                ComponentPage(num)
            }
    }
}

fun randomNumber(): Int {
    var r = Random()
    var num = r.nextInt(1000)
    return num
}

@Composable
fun ComponentPage(num: Int) {
    var answer by remember { mutableStateOf("") }
    var answerEntered = remember { mutableStateOf(false) }
    var hint by remember { mutableStateOf("") }
    var reset by remember { mutableStateOf(false) }
    var count by remember { mutableStateOf(0) } //keep state of answer when count changing
    var number by remember { mutableStateOf(num) }



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxHeight()

    ) {
        Text(
            text = "Try to guessing the number I'm thinking of from 1 to 1000!",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(20.dp)
        )


        TextField(
            value = answer,
            onValueChange = {
                answer = it
            },
            placeholder = {
                Text(text = stringResource(id = R.string.hint))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                capitalization = KeyboardCapitalization.Words
            ),
            keyboardActions = KeyboardActions(onAny = {
                answerEntered.value = true
            })
        )


        Text(
            text = "$hint",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )


        if (reset) {
            Button(onClick = {
                reset = false
                hint = ""
                count = 0
                var r = Random()
                number = r.nextInt(1000)
            }) {
                Text(text = "PLAY AGAIN")
            }
        } else {
            Button(onClick = {
                answerEntered.value = true
                val checkAnswer = if (number < answer.toInt()) {
                    hint = "The number is lower than $answer"
                    count++
                } else if (number > answer.toInt()) {
                    hint = "The number is bigger than $answer"
                    count++
                } else if (number == answer.toInt()) {
                    hint = "Congratulations you win , count your guess is $count"
                    reset = true
                } else {
                }
                checkAnswer
            }
            ) {
                Text(text = "CHECK")
                Modifier.padding(50.dp)
            }
        }

    }
}
