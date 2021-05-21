package com.example.mynotes;

public interface CardSource {
    CardData getCardData(int position);
    int size();

}
