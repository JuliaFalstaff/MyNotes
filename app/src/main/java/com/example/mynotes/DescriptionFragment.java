package com.example.mynotes;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DescriptionFragment extends Fragment {

    public static final String INDEX = "INDEX";
    public static final int DEFAULT_INDEX = 0;
    private Note note;

    public DescriptionFragment() {
    }

    public static DescriptionFragment newInstance(Note notes) {
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle args = new Bundle();
        args.putParcelable(INDEX, notes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_description, container, false);
        setHasOptionsMenu(true);
        TextView descriptionsTexView = view.findViewById(R.id.notes_descriptions);
        TypedArray arrayDescriptions = getResources().obtainTypedArray(R.array.descriptions);
        descriptionsTexView.setText(arrayDescriptions.getResourceId(note.getIndexOfDescription(), DEFAULT_INDEX));
        TextView textViewTitle = view.findViewById(R.id.textView);
        textViewTitle.setText(note.getTitle());
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.description, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_attach){
            Toast.makeText(getContext(), "Attach something here", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.action_share) {
            Toast.makeText(getContext(), "Share with somebody", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            Toast.makeText(getContext(), "Delete", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

