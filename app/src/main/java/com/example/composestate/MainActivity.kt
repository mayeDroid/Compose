package com.example.composestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.composestate.ui.theme.ComposeStateTheme

class MainActivity : ComponentActivity() {
    val viewModel by lazy { ViewModelProvider(this).get(ComposeViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStateTheme {
                val state = viewModel.state
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.fillMaxWidth(),

                    ) {
                 /* var textState by rememberSaveable { mutableStateOf("") }  // this is like the
                    // editText Name in xml, you will use this as a reference when you need to call the edit text

                    val listOfNames = remember { mutableStateListOf<String>() } // remember savable will not work reason why we using viewmodel*/

                    EditTextComposable(
                        textValue = state.value.editTextState,
                        onValueChanged = { viewModel.upDateEditText(it) },
                        onClick = {
                            if (state.value.editTextState.isBlank()){
                                Unit
                            } else{
                                viewModel.upDateNamesList(state.value.editTextState)
                                viewModel.upDateEditText("")    // after clicking the button we want the edit text to be blank
                            }

                        },
                    )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    ) {

                        items(state.value.listState.size) {
                            Text(text = state.value.listState[it],)
                            Icon(imageVector = Icons.Default.Delete,
                                contentDescription = "",
                                Modifier
                                    .clickable {

                                            viewModel.state.value.listState.removeAt(it)
                                            viewModel.upDateEditText(" ")


                                    }
                                    .align(Alignment.End)
                            )


                        }

                    }

                }
            }
        }
    }
}

@Composable
fun Icons(onClick: () -> Unit){
    Icon(imageVector = Icons.Default.Delete,
        contentDescription = "",
        Modifier.clickable{onClick}
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextComposable(
    textValue: String,
    onValueChanged: (String) -> Unit,
    onClick: () -> Unit
) {
    TextField(
        value = textValue,
        onValueChange = { onValueChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                Modifier.clickable { onClick() })
        })
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeStateTheme {
        //Greeting("Android")
    }
}