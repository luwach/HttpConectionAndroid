package oo.max.bookslist.async;

import android.os.AsyncTask;

import oo.max.bookslist.model.Book;
import oo.max.bookslist.model.BookResponse;
import oo.max.bookslist.retrofit.BookApiClient;
import oo.max.bookslist.retrofit.RetrofitApiClientFactory;
import retrofit2.Response;

public class GetBookDetailsAsyncTask extends AsyncTask<String, Void, Book>{

    private final OnBookDownloadedListener onBookDownloadedListener;
    private final BookApiClient bookApiClient;

    public GetBookDetailsAsyncTask(OnBookDownloadedListener onBookDownloadedListener) {
        this.onBookDownloadedListener = onBookDownloadedListener;
        bookApiClient = new RetrofitApiClientFactory().create();
    }

    @Override
    protected Book doInBackground(String... strings) {
        try {
            if(strings == null || strings.length == 0) {
                return null;
            }
            String bookId = strings[0];
            Response<BookResponse> response = bookApiClient.getBookDetails(bookId).execute();
            if(response.isSuccessful()) {
                return response.body().getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Book book) {
        onBookDownloadedListener.bookDownloaded(book);
    }

    public interface OnBookDownloadedListener {
        void bookDownloaded(Book book);
    }
}
