package com.example.lwach.notelist.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.lwach.notelist.R;
import com.example.lwach.notelist.create.CreateNoteActivity;
import com.example.lwach.notelist.detail.NoteDetailActivity;
import com.example.lwach.notelist.model.NoteListItem;

import java.util.List;

//Najpierw AsyncTask potem Adapter

public class NotesListActivity extends AppCompatActivity
        implements GetNotesListAsyncTask.OnNotesDownloadListener,
        NotesListAdapter.OnNoteClickedListener {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        recyclerView = (RecyclerView) findViewById(R.id.notes_list_recycler_view);

        //wpisujemy to, żeby activity implementowało listenera i wybieramy 4 opcje
        // alt + enter new GetNotesListAsyncTask(this).execute();

        findViewById(R.id.add_note_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCreateNoteActivity();
            }
        });
    }

    private void startCreateNoteActivity() {
        startActivity(new Intent(this, CreateNoteActivity.class));
    }

    //Lista się odświeży po dodaniu do listy
    @Override
    protected void onResume() {
        super.onResume();
        new GetNotesListAsyncTask(this).execute();
    }

    @Override
    public void notesDownloaded(List<NoteListItem> noteListItems) {
        if(noteListItems == null) {
            Toast.makeText(this, "Failed to download notes", Toast.LENGTH_SHORT).show();
            return;
        }

        NotesListAdapter notesListAdapter = new NotesListAdapter(this, noteListItems, this);
        recyclerView.setAdapter(notesListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void noteClicked(NoteListItem noteListItem) {
        Intent intent = new Intent(this, NoteDetailActivity.class);
        intent.putExtra("noteId", noteListItem.getId());
        startActivity(intent);
    }
}