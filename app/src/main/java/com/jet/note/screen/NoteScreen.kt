@file:OptIn(ExperimentalMaterial3Api::class)

package com.jet.note.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jet.note.data.NoteDataSource
import com.jet.note.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit,
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    Column(Modifier.padding(16.dp)) {
        TopAppBar(
            colors = TopAppBarDefaults
                .smallTopAppBarColors(containerColor = Color.Cyan),
            title = { Text(text = "Siema") },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Dupa"
                )
            })
        // End TopAppBar

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(singleLine = true,
                textStyle = TextStyle(fontWeight = FontWeight.Bold),value = title,
                onValueChange = {title = it},
                label = { Text(text = "Tytul", fontWeight = FontWeight.Bold)})

            OutlinedTextField(value = description, onValueChange = {description = it},
                label = { Text(text = "Opis")})
            Button(onClick = { if (title.isNotEmpty() && description.isNotEmpty()) {
                title = ""
                description = ""

            }
                                                                                   },
                Modifier.padding(10.dp)) {
                Text(text = "Ok")
            }
            // End Button
        }
        // End Column #2
        Divider(Modifier.padding(10.dp))
        LazyColumn(){
            items(notes){note ->
                Text(text = note.title)
            }
        }

    }
    // End Column #1

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotesScreenPrevie() {
    NoteScreen(notes = NoteDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}