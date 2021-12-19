package com.example.notebookapp201121.ui;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebookapp201121.R;
import com.example.notebookapp201121.data.Note;
import com.example.notebookapp201121.data.NoteSource;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private NoteSource dataSource;
    private final Fragment fragment;
    private OnItemClickListener itemClickListener;
    private int menuPos;

    public NoteAdapter(NoteSource dataSource, Fragment fragment) {
        this.dataSource = dataSource;
        this.fragment = fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
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

    public int getMenuPosition() {
        return menuPos;
    }

    public interface OnItemClickListener {
        void onItemClick (View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;

        @RequiresApi(api = Build.VERSION_CODES.N)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);

            registerContextMenu(itemView);

            title.setOnClickListener(view -> {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(view, getAdapterPosition());
                }
            });

            title.setOnLongClickListener(view -> {
                itemView.showContextMenu(10, 10);
                menuPos = getLayoutPosition(); 
                return true;
            });
        }

       private void registerContextMenu(@NonNull View itemView) {
            if (fragment != null) {
                itemView.setOnLongClickListener(view -> {
                    menuPos = getLayoutPosition();
                    return false;
                });
                fragment.registerForContextMenu(itemView);
            }
       }

        public void bind(Note note) {
            title.setText(note.getNoteTitle());
        }
    }
}
