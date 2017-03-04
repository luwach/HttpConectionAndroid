package com.example.lwach.notelist.apiClient;

import com.example.lwach.notelist.model.Note;
import com.example.lwach.notelist.model.NoteDetailsResponse;
import com.example.lwach.notelist.model.NotesListResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NotesApiClient {

    //Wzywamy tu gettery z poszczególnych responsów

    @GET("/plugin/notes.list")
    Call<NotesListResponse> getNotes();

    @GET("/plugin/note.details")
    Call<NoteDetailsResponse> getNoteDetails(@Query("id") String id);

    @POST("/plugin/notes.create")
    Call<ResponseBody> createNote(@Body Note note);
}
