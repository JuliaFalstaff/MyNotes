package com.example.mynotes;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class NotesFragment extends Fragment {

    private boolean isLandscape;
    public static final String CURRENT_NOTE = "CurrentNote";
    private int currentPosition = 0;

    public NotesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_NOTE, currentPosition);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(CURRENT_NOTE, DescriptionFragment.DEFAULT_INDEX);
        }
        if (isLandscape) {
            showDescription(currentPosition);
        }
    }

    private void initList(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        String[] notes = getResources().getStringArray(R.array.notes);
        for (int i = 0; i < notes.length; i++) {
            String note = notes[i];
            TextView textView = new TextView(getContext());
            textView.setText(note);
            textView.setTextSize(36);
            linearLayout.addView(textView);
            final int currentIndex = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDescription(currentIndex);
                    currentPosition = currentIndex;
                }
            });
        }
    }

    void showDescription(int index) {
        if (isLandscape) {
            showLandscapeDescription(index);
        } else {
            showPortraitDescription(index);
        }
    }

    private void showLandscapeDescription(int index) {
        DescriptionFragment descriptionFragment = DescriptionFragment.newInstance(index);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.notes_descriptions, descriptionFragment)
                .commit();
    }

    void showPortraitDescription(int index) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), DescriptionActivity.class);
        intent.putExtra(DescriptionFragment.INDEX, index);
        startActivity(intent);
    }
}