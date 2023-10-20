@file:OptIn(ExperimentalMaterial3Api::class)

package com.jet.note.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jet.note.data.NoteDataSource
import com.jet.note.model.Note
import java.time.format.DateTimeFormatter

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

        val context = LocalContext.current

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(singleLine = true,
                textStyle = TextStyle(fontWeight = FontWeight.Bold),value = title,
                onValueChange = {title = it},
                label = { Text(text = "Title", fontWeight = FontWeight.Bold)})

            OutlinedTextField(singleLine = true,
                value = description, onValueChange = {description = it},
                label = { Text(text = "Note")})

            Button(onClick = { if (title.isNotEmpty() && description.isNotEmpty()) {
                onAddNote(Note(title = title, description = description))
                title = ""
                description = ""
                Toast.makeText(context, "Note added", Toast.LENGTH_SHORT).show()}},

                Modifier.padding(10.dp)) {
                Text(text = "Ok")
            }
            // End Button
        }
        // End Column #2
        Divider(Modifier.padding(10.dp))
        LazyColumn(){
            items(notes){note ->
                NoteRow(note = note, onNoteClicked = {onRemoveNote(note)})
            }
        }

    }
    // End Column #1

}

@Composable
fun NoteRow(modifier: Modifier = Modifier,
            note: Note,
            onNoteClicked: (Note) -> Unit){
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB)){
        Column(modifier
            .clickable { onNoteClicked(note) }
            .padding(horizontal = 14.dp, vertical = 6.dp))
        {
            Text(text = note.title,
                style = MaterialTheme.typography.titleMedium)
            Text(text = note.description, style = MaterialTheme.typography.labelMedium)
            Text(text = note.dateNow.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                style = MaterialTheme.typography.labelSmall)
        }
        // End Column
    }
    // End Surface
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotesScreenPrevie() {
    NoteScreen(notes = NoteDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}