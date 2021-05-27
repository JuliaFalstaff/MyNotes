package com.example.mynotes;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;


public class NoteFragment extends Fragment {

    private boolean isLandscape;
    public static final String CURRENT_NOTE = "CurrentNotes";
    public static final String TAG = "NoteFragment";
    private Note currentNote;
    private CardNoteSource data;
    private MyAdapter adapter;
    private RecyclerView recyclerView;

    public NoteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        initView(view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
            currentNote = new Note(0, getResources().getStringArray(R.array.notes)[0]);
        }
        if (isLandscape) {
            showDescription(currentNote);
        }
    }

    private void showDescription(Note currentNotes) {
        if (isLandscape) {
            showLandscapeDescription(currentNotes);
        } else {
            showPortraitDescription(currentNotes);
        }
    }

    private void showLandscapeDescription(Note currentNotes) {
        DescriptionFragment descriptionFragment = DescriptionFragment.newInstance(currentNotes);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.notes_descriptions, descriptionFragment)
                .commit();
    }

    private void showPortraitDescription(Note currentNotes) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), DescriptionActivity.class);
        intent.putExtra(DescriptionFragment.INDEX, currentNotes);
        startActivity(intent);
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyAdapter(data, this);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator, null));
        recyclerView.addItemDecoration(itemDecoration);

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(1000);
        animator.setRemoveDuration(1000);
        animator.setMoveDuration(1000);
        recyclerView.setItemAnimator(animator);

        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                currentNote = new Note(position, getResources().getStringArray(R.array.notes)[position]);
                showDescription(currentNote);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                int position = data.addNote(new Note("Title " + data.size(), "SubTitle " + data.size(), R.drawable.ic_avatar_foreground));
                adapter.notifyItemInserted(position);
                recyclerView.smoothScrollToPosition(position);
                Log.d(TAG, "AddedNewList");
                return true;
            case R.id.action_clear:
                data.clearNote();
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        // Получим источник данных для списка
        data = new CardNoteSourceImpl(getResources()).init();
        initRecyclerView();
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.card_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = adapter.getMenuPosition();
        switch (item.getItemId()) {
            case R.id.action_update:
                data.updateNote(position, new Note("Clone Title " + data.size(), "SubTitle " + data.size(), R.drawable.ic_avatar_foreground));
                adapter.notifyItemChanged(position);
                return true;
            case R.id.action_delete:
                data.deleteNote(position);
                adapter.notifyItemRemoved(position);
                return true;
            case (R.id.action_move):
                if (data.moveNote(position)) {
                    adapter.notifyItemMoved(position, position + 1);
                }
                return true;
        }
        return super.onContextItemSelected(item);
    }
}