package com.example.mynotes;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DescriptionFragment extends Fragment {

    public static final String NOTE = "NOTE";
    public static final int DEFAULT_INDEX = 0;
    private Notes note;

    public DescriptionFragment() {
    }

    public static DescriptionFragment newInstance(Notes note) {
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle args = new Bundle();
        args.putParcelable(NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(NOTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_description, container, false);
        TextView descriptionsTexView = view.findViewById(R.id.notes_descriptions);
        TypedArray arrayDescriptions = getResources().obtainTypedArray(R.array.descriptions);
        descriptionsTexView.setText(arrayDescriptions.getResourceId(note.getIndex(), DEFAULT_INDEX));
        TextView noteNameView = view.findViewById(R.id.textView);
        noteNameView.setText(note.getTitle());
        return view;
    }
}

