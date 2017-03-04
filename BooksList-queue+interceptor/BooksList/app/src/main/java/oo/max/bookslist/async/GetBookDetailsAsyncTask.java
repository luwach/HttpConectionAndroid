package oo.max.bookslist.async;

import android.os.AsyncTask;

import oo.max.bookslist.model.Book;
import oo.max.bookslist.model.BookResponse;
import oo.max.bookslist.retrofit.BookApiClient;
import oo.max.bookslist.retrofit.RetrofitApiClientFactory;
import retrofit2.Call;
import retrofit2.Response;

public class GetBookDetailsAsyncTask extends AsyncTask<String, Void, Book>{

    private final OnBookDownloadedListener onBookDownloadedListener;
    private final BookApiClient bookApiClient;

    public GetBookDetailsAsyncTask(OnBookDownloadedListener onBookDownloadedListener) {
        this.onBookDownloadedListener = onBookDownloadedListener;
        bookApiClient = new RetrofitApiClientFactory().create(); // Użycie factory - patrz poprzednie slajdy
    }

    @Override
    protected Book doInBackground(String... strings) {
        try {
            //metoda wykonywana w wątku w tle - nie blokuje wątku UI!
            if(strings == null || strings.length == 0) {
                return null;
            }
            String bookId = strings[0];
            //tworzymy obiekt call przez wywołanie metody z interface'u BookApiClient
            Call<BookResponse> bookDetails = bookApiClient.getBookDetails(bookId);
            //Uruchamiamy zapytanie do serwera przy pomocy execute
            Response<BookResponse> response = bookDetails.execute();
            //po otrzymaniu odpowiedzi, sprawdzamy, czy jest serwer zwócił poprawną odpowiedź.
            //Jeśli nie udało się nawiązać połączenia, to metoda execute wyrzuci wyjątek
            if(response.isSuccessful()) {
                //pobieramy dane z zapytania
                return response.body().getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Book book) {
        //ta metoda wywołuje się znowu w watku UI
        onBookDownloadedListener.bookDownloaded(book);
    }

    public interface OnBookDownloadedListener { // interface będziemy implementować w Activity albo Fragmencie
        void bookDownloaded(Book book);
    }
}
