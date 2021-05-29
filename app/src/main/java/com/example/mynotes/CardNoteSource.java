package com.example.mynotes;

public interface CardNoteSource {
    CardNoteSource init (CardNoteSourceResponse cardNoteSourceResponse);

    Note getCardNote(int position);

    int size();

    void deleteNote(int position);

    void updateNote(int position, Note note);

    void addNote(Note note);

    void clearNote();
//
//    boolean moveNote(int position);
}

