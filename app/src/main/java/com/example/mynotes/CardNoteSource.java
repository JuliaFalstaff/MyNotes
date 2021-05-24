package com.example.mynotes;

public interface CardNoteSource {
    Note getCardNote(int position);
    int size();
}

