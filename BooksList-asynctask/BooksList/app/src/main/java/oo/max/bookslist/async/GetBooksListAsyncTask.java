package oo.max.bookslist.async;

import android.os.AsyncTask;

import java.util.List;

import oo.max.bookslist.model.BookListItem;
import oo.max.bookslist.model.BookListResponse;
import oo.max.bookslist.retrofit.BookApiClient;
import oo.max.bookslist.retrofit.RetrofitApiClientFactory;
import retrofit2.Call;
import retrofit2.Response;

public class GetBooksListAsyncTask extends AsyncTask<Void, Void, List<BookListItem>> {

    private final OnBookListDownloadedListener onBookListDownloadedListener;
    private final BookApiClient bookApiClient;

    public GetBooksListAsyncTask(OnBookListDownloadedListener onBookListDownloadedListener) {
        this.onBookListDownloadedListener = onBookListDownloadedListener;
        bookApiClient = new RetrofitApiClientFactory().create();
    }

    @Override
    protected List<BookListItem> doInBackground(Void... voids) {
        try {
            Call<BookListResponse> booksListCall = bookApiClient.getBooksList();
            Response<BookListResponse> response = booksListCall.execute();
            if(response.isSuccessful()) {
                return response.body().getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<BookListItem> bookListItems) {
        onBookListDownloadedListener.bookListDownloaded(bookListItems);
    }

    public interface OnBookListDownloadedListener {
        void bookListDownloaded(List<BookListItem> listItems);
    }
}
