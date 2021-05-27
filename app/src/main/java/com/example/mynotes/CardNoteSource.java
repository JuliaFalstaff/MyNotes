package com.example.mynotes;

public interface CardNoteSource {
    Note getCardNote(int position);

    int size();

    void deleteNote(int position);

    void updateNote(int position, Note note);

    int addNote(Note note);

    void clearNote();

    boolean moveNote(int position);
}

