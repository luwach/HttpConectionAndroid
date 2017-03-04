package oo.max.bookslist.retrofit;

import oo.max.bookslist.model.BookListResponse;
import oo.max.bookslist.model.BookResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookApiClient {

    @GET("/plugin/book.list")
    Call<BookListResponse> getBooksList();

    @GET("/plugin/book.details")
    Call<BookResponse> getBookDetails(@Query("id") String bookId);


}