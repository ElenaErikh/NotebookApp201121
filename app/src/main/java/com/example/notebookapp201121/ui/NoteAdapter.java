package com.example.notebookapp201121.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebookapp201121.R;
import com.example.notebookapp201121.data.Note;
import com.example.notebookapp201121.data.NoteSource;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private NoteSource dataSource;
    private OnItemClickListener itemClickListener;

    public NoteAdapter(NoteSource dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        holder.bind(dataSource.getNote(position));
    }

    @Override
    public int getItemCount() {
        return dataSource.listSize();
    }

    public void setOnItemClickListener (OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick (View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);

            title.setOnClickListener(view -> {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(view, getAdapterPosition());
                }
            });
        }

        public void bind(Note note) {
            title.setText(note.getNoteTitle());
        }
    }
}
