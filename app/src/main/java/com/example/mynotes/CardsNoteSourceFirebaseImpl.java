package com.example.mynotes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CardsNoteSourceFirebaseImpl implements CardNoteSource {
    public static final String CARDS_COLLECTION = "cards";
    public static final String TAG = "[CardsNoteSourceFirebaseImpl]";


    private FirebaseFirestore store = FirebaseFirestore.getInstance();

    private CollectionReference collection = store.collection(CARDS_COLLECTION);

    private List<Note> cardsNote = new ArrayList<>();


    @Override
    public CardNoteSource init(CardNoteSourceResponse cardNoteSourceResponse) {
        collection.orderBy(CardsNoteMapping.Fields.TITLE, Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    cardsNote = new ArrayList<Note>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> doc = document.getData();
                        String id = document.getId();
                        Note note = CardsNoteMapping.toCardData(id, doc);
                        cardsNote.add(note);
                    }
                    Log.d(TAG, "success " + cardsNote.size() + " qnt");
                    cardNoteSourceResponse.initialized(CardsNoteSourceFirebaseImpl.this);
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        return this;
    }

    @Override
    public Note getCardNote(int position) {
        return cardsNote.get(position);
    }

    @Override
    public int size() {
        if (cardsNote == null) {
            return 0;
        }
        return cardsNote.size();
    }

    @Override
    public void deleteNote(int position) {
        collection.document(cardsNote.get(position).getId()).delete();
        cardsNote.remove(position);
    }

    @Override
    public void updateNote(int position, Note note) {
        String id = note.getId();
        collection.document(id).set(CardsNoteMapping.toDocument(note));

    }

    @Override
    public void addNote(Note note) {
        collection.add(CardsNoteMapping.toDocument(note)).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                note.setId(documentReference.getId());
            }
        });
    }

    @Override
    public void clearNote() {
        for (Note note : cardsNote) {
            collection.document(note.getId()).delete();
        }
        cardsNote = new ArrayList<Note>();
    }


}
