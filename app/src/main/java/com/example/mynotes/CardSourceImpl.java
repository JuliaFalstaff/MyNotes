package com.example.mynotes;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

public class CardSourceImpl implements CardSource {
    private List<CardData> list;
    private Resources resources;

    public CardSourceImpl(Resources resources) {
        list = new ArrayList<>();
        this.resources = resources;
    }

    public CardSourceImpl init() {
        String[] title = resources.getStringArray(R.array.notes);
        String[] description = resources.getStringArray(R.array.descriptions);
        int[] pictures = getImageArray();

        for (int i = 0; i < description.length; i++) {
            list.add(new CardData(
                    title[i],
                    description[i],
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
    public CardData getCardData(int position) {
        return list.get(position);
    }

    @Override
    public int size() {
        return list.size();
    }
}
