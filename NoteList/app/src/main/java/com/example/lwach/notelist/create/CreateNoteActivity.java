package com.example.lwach.notelist.create;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lwach.notelist.R;
import com.example.lwach.notelist.model.Note;

public class CreateNoteActivity extends AppCompatActivity
        implements CreateNoteAsyncTask.OnNoteCreatedListener {

    private EditText nameEdit;
    private EditText contentEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        nameEdit = (EditText) findViewById(R.id.note_name_edit);
        contentEdit = (EditText) findViewById(R.id.note_content_edit);

        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });
    }

    private void saveNote() {
        Note note = new Note(
                null,
                nameEdit.getText().toString(),
                contentEdit.getText().toString()
        );

        new CreateNoteAsyncTask(this).execute(note);
    }

    @Override
    public void created(boolean successful) {
        Toast.makeText(this, "Result: "+successful, Toast.LENGTH_SHORT).show();

        if(successful) {
            finish();
            return;
        }
    }
}
