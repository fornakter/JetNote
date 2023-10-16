package com.jet.note.data

import com.jet.note.model.Note

class NoteDataSource{
    fun loadNotes(): List<Note>{
        return listOf(
            Note(title = "Siema", description = "Drugie siema"),
            Note(title = "Dupa", description = "Drugie dupa")
        )
    }
}