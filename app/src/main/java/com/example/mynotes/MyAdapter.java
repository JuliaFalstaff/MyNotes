package com.example.mynotes;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public static final String TAG = "MyAdapter";
    private CardNoteSource cardNoteSource;
    private OnItemClickListener onItemClickListener;

    public MyAdapter(CardNoteSource cardNoteSource) {
        this.cardNoteSource = cardNoteSource;
        Log.d(TAG, "Constructor My Adapter");
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

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView subTitle;
        private TextView textViewHolder;
        private ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleOfNotes);
            subTitle = itemView.findViewById(R.id.subTitlesOfNote);
            image = itemView.findViewById(R.id.imageOfNotes);
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
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
