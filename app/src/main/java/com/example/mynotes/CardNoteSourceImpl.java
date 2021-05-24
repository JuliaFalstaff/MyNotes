package com.example.mynotes;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

public class CardNoteSourceImpl implements CardNoteSource {
    private List<Note> list;
    private Resources resources;

    public CardNoteSourceImpl(Resources resources) {
        list = new ArrayList<>();
        this.resources = resources;
    }

    public CardNoteSourceImpl init() {
        String[] title = resources.getStringArray(R.array.notes);
        String[] subTitle = resources.getStringArray(R.array.subTitles);
        int[] pictures = getImageArray();

        for (int i = 0; i < subTitle.length; i++) {
            list.add(new Note(
                    title[i],
                    subTitle[i],
                    pictures[i]
            ));
        }
        return this;
    }

    private int[] getImageArray() {
        TypedArray array = resources.obtainTypedArray(R.array.pictures);
        int[] images  = new int[array.length()];
        for (int i = 0; i < array.length(); i++) {
            images[i] = array.getResourceId(i,0);
        }
        return images;
    }

    @Override
    public Note getCardNote(int position) {
        return list.get(position);
    }

    @Override
    public int size() {
        return list.size();
    }
}
