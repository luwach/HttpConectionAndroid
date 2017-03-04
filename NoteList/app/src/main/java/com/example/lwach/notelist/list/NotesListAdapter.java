package com.example.lwach.notelist.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lwach.notelist.R;
import com.example.lwach.notelist.model.NoteListItem;

import java.util.List;

//Wpisujemy adapterowi NotesItemViewHolder i tworzymy statyczną klasę w środku

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NotesItemViewHolder> {

    //tworzymy pola jak w response

    private final Context context;
    private final List<NoteListItem> noteListItems;
    private final LayoutInflater layoutInflater;
    private final OnNoteClickedListener onNoteClickedListener;

    public NotesListAdapter(Context context,
                            List<NoteListItem> noteListItems,
                            OnNoteClickedListener onNoteClickedListener) {
        this.context = context;
        this.noteListItems = noteListItems;
        layoutInflater = LayoutInflater.from(context);
        this.onNoteClickedListener = onNoteClickedListener;
    }

    //musi rozszerzać ViewHolder, najeżdżamy na nią i alt + enter - tworzy konstruktor
    //Najpierw tworze tą klasę statyczną

    @Override
    public NotesItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.notes_list_item, parent, false);
        return new NotesItemViewHolder(view, onNoteClickedListener);
    }

    @Override
    public void onBindViewHolder(NotesItemViewHolder holder, int position) {
        NoteListItem noteListItem = noteListItems.get(position);

        //Dodajemy to co pobraliśmy do NoteListItem
        holder.noteNameText.setText(noteListItem.getName());
        holder.noteListItem = noteListItem;
    }

    @Override
    public int getItemCount() {
        return noteListItems.size();
    }

    public static class NotesItemViewHolder extends RecyclerView.ViewHolder {

        TextView noteNameText;
        NoteListItem noteListItem;

        //Pojedynczy text wrzucany do listy i potem do onBindViewHolder,
        //aby wyświetlić go w odpowiedniej pozycji

        public NotesItemViewHolder(View itemView,
                                   final OnNoteClickedListener onNoteClickedListener) {
            super(itemView);
            noteNameText = (TextView) itemView.findViewById(R.id.note_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onNoteClickedListener.noteClicked(noteListItem);
                }
            });
        }
    }

    public interface OnNoteClickedListener {
        void noteClicked(NoteListItem noteListItem);
    }
}