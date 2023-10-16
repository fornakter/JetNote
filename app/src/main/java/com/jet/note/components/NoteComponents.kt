package com.jet.note.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NoteInputText(modifier: Modifier = Modifier,
                  text: String,
                  label: String,
                  maxLine: Int = 1,
                  onTextChange: (String) -> Unit,
                  onImeAction: () -> Unit = {}
        ){
}