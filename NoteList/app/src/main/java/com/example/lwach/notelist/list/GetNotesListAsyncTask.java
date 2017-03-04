package com.example.lwach.notelist.list;

import android.os.AsyncTask;

import com.example.lwach.notelist.apiClient.NotesApiClient;
import com.example.lwach.notelist.apiClient.RetrofitApiClientFactory;
import com.example.lwach.notelist.model.NoteListItem;
import com.example.lwach.notelist.model.NotesListResponse;
import java.util.List;

import retrofit2.Response;

public class GetNotesListAsyncTask extends AsyncTask<Void, Void, List<NoteListItem>> {

    //pierwsze pole to interface z metodami GET i formami odpowiedzi i na niej wywołujemy
    //metodę create() z retrofita

    private final NotesApiClient notesApiClient;
    private final OnNotesDownloadListener onNotesDownloadListener;

    //powyższe dwa pola muszą znajdować się konstruktorze, bo nie są zinicjalizowane
    //wywołujemy retrofita

    public GetNotesListAsyncTask(OnNotesDownloadListener onNotesDownloadListener) {
        this.onNotesDownloadListener = onNotesDownloadListener;
        notesApiClient = new RetrofitApiClientFactory().create();
    }

    //Działamy na retroficie, bo on rozszerza NotesApiClient
    @Override
    protected List<NoteListItem> doInBackground(Void... voids) {
        try {
            Response<NotesListResponse> response = notesApiClient.getNotes().execute();
            if(response.isSuccessful()) {
                return response.body().getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //metoda przekazująca interfejs poniżej
    @Override
    protected void onPostExecute(List<NoteListItem> noteListItems) {
        onNotesDownloadListener.notesDownloaded(noteListItems);
    }

    //ten interface będzie importowany w MainActivity
    public interface OnNotesDownloadListener {
        void notesDownloaded(List<NoteListItem> noteListItems);
    }
}