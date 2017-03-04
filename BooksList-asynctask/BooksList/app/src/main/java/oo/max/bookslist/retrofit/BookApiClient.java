package oo.max.bookslist.retrofit;

import oo.max.bookslist.model.BookListResponse;
import oo.max.bookslist.model.BookResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface BookApiClient {

    @GET("/plugin/book.list")
    @Headers({
            "X-BAASBOX-APPCODE: 1234567890"
    })
    Call<BookListResponse> getBooksList();

    @GET("/plugin/book.details")
    @Headers({
            "X-BAASBOX-APPCODE: 1234567890"
    })
    Call<BookResponse> getBookDetails(@Query("id") String bookId);

}