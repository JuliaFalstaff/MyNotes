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
//    private int currentPosition = 0;
    private Notes currentNote;

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
        outState.putParcelable(CURRENT_NOTE, currentNote);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE);
        } else {
            currentNote = new Notes(0, getResources().getStringArray(R.array.notes)[0]);
        }
        if (isLandscape) {
            showDescription(currentNote);
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
                    currentNote = new Notes(currentIndex, getResources().getStringArray(R.array.notes)[currentIndex]);
                    showDescription(currentNote);
                }
            });
        }
    }

    void showDescription(Notes currentNote) {
        if (isLandscape) {
            showLandscapeDescription(currentNote);
        } else {
            showPortraitDescription(currentNote);
        }
    }

    private void showLandscapeDescription(Notes currentNote) {
        DescriptionFragment descriptionFragment = DescriptionFragment.newInstance(currentNote);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.notes_descriptions, descriptionFragment)
                .commit();
    }

    void showPortraitDescription(Notes currentNote) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), DescriptionActivity.class);
        intent.putExtra(DescriptionFragment.NOTE, currentNote);
        startActivity(intent);
    }
}