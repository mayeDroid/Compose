package com.example.composestate

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ComposeViewModel: ViewModel() {
    val state = mutableStateOf(SaveStateOfScreen())

    fun upDateEditText(newText: String){
        state.value = state.value.copy(editTextState = newText)
    }

    fun upDateNamesList(newName: String){
        val currentList = state.value.listState
        currentList.add(newName)
        state.value = state.value.copy(listState = currentList)
    }


}

data class SaveStateOfScreen(
    var editTextState: String = "",
    val listState: MutableList<String> = mutableListOf()
){

}

