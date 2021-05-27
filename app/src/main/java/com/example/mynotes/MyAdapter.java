package com.example.mynotes;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public static final String TAG = "MyAdapter";
    private CardNoteSource cardNoteSource;
    private OnItemClickListener onItemClickListener;
    private int menuPosition;
    private Fragment fragment;

    public MyAdapter(CardNoteSource cardNoteSource) {
        this.cardNoteSource = cardNoteSource;
        Log.d(TAG, "Constructor My Adapter");
    }

    public MyAdapter(CardNoteSource cardNoteSource, Fragment fragment) {
        this.cardNoteSource = cardNoteSource;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        Log.d(TAG, "onCreateViewHolder");
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(cardNoteSource.getCardNote(position));
        Log.d(TAG, "onBindViewHolder" + position);
    }

    @Override
    public int getItemCount() {
        return cardNoteSource.size();
    }

    public int getMenuPosition() {
        return menuPosition;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView subTitle;
        private ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleOfNotes);
            subTitle = itemView.findViewById(R.id.subTitlesOfNote);
            image = itemView.findViewById(R.id.imageOfNotes);

            if (fragment != null) {
                fragment.registerForContextMenu(itemView);
            }
        }

        void bind(Note cardNotes) {
            title.setText(cardNotes.getTitle());
            subTitle.setText(cardNotes.getSubTitle());
            image.setImageResource(cardNotes.getPicture());

            //Обработка нажатия - решила сделать возможноть открытия Description при нажатии и на картинку и на Title.
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });

            image.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    menuPosition = getLayoutPosition();
                    v.showContextMenu(10, 10);
                    return true;
                }
            });

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getSubTitle() {
            return subTitle;
        }

        public ImageView getImage() {
            return image;
        }

    }

    interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
