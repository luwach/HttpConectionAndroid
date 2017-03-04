package com.example.lwach.notelist.detail;

import android.os.AsyncTask;

import com.example.lwach.notelist.apiClient.NotesApiClient;
import com.example.lwach.notelist.apiClient.RetrofitApiClientFactory;
import com.example.lwach.notelist.model.Note;
import com.example.lwach.notelist.model.NoteDetailsResponse;

import retrofit2.Response;

public class GetNoteDetailsAsyncTask extends AsyncTask<String, Void, Note> {

    private final OnNoteDownloadedListener onNoteDownloadedListener;
    private final NotesApiClient notesApiClient;

    public GetNoteDetailsAsyncTask(OnNoteDownloadedListener onNoteDownloadedListener) {
        this.onNoteDownloadedListener = onNoteDownloadedListener;
        notesApiClient = new RetrofitApiClientFactory().create();
    }

    @Override
    protected Note doInBackground(String... strings) {
        try {
            if(strings == null || strings.length == 0) {
                return null;
            }
            Response<NoteDetailsResponse> response = notesApiClient
                    .getNoteDetails(strings[0])
                    .execute();

            if(response.isSuccessful()) {
                return response.body().getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Note note) {
        onNoteDownloadedListener.noteDownloaded(note);
    }

    public interface OnNoteDownloadedListener {
        void noteDownloaded(Note note);
    }
}
