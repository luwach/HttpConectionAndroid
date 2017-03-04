package com.example.lwach.notelist.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lwach.notelist.R;
import com.example.lwach.notelist.model.Note;

public class NoteDetailActivity extends AppCompatActivity
        implements GetNoteDetailsAsyncTask.OnNoteDownloadedListener {

    private TextView nameText;
    private TextView contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        nameText = (TextView) findViewById(R.id.name);
        contentText = (TextView) findViewById(R.id.content);

        String noteId = getIntent().getStringExtra("noteId");

        if(noteId == null) {
            finish();
            return;
        }

        new GetNoteDetailsAsyncTask(this).execute(noteId);
    }

    @Override
    public void noteDownloaded(Note note) {
        if (note == null) {
            finish();
            return;
        }

        nameText.setText(note.getName());
        contentText.setText(note.getContent());
    }
}
