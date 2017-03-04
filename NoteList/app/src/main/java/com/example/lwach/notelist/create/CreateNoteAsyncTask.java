package com.example.lwach.notelist.create;

import android.os.AsyncTask;

import com.example.lwach.notelist.apiClient.NotesApiClient;
import com.example.lwach.notelist.apiClient.RetrofitApiClientFactory;
import com.example.lwach.notelist.model.Note;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class CreateNoteAsyncTask extends AsyncTask<Note, Void, Boolean> {

    private final OnNoteCreatedListener onNoteCreatedListener;
    private final NotesApiClient notesApiClient;

    public CreateNoteAsyncTask(OnNoteCreatedListener onNoteCreatedListener) {
        this.onNoteCreatedListener = onNoteCreatedListener;
        notesApiClient = new RetrofitApiClientFactory().create();
    }

    @Override
    protected Boolean doInBackground(Note... notes) {
        try {
            if(notes == null || notes.length == 0) {
                return false;
            }
            Response<ResponseBody> response = notesApiClient
                    .createNote(notes[0])
                    .execute();

            return response.isSuccessful();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean successful) {
        onNoteCreatedListener.created(successful);
    }

    public interface OnNoteCreatedListener {
        void created(boolean successful);
    }

}
