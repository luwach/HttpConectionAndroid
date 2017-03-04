package oo.max.bookslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import oo.max.bookslist.adapter.BookListAdapter;
import oo.max.bookslist.async.GetBooksListAsyncTask;
import oo.max.bookslist.model.BookListItem;
import oo.max.bookslist.model.BookListResponse;
import oo.max.bookslist.retrofit.BookApiClient;
import oo.max.bookslist.retrofit.RetrofitApiClientFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements GetBooksListAsyncTask.OnBookListDownloadedListener,
        BookListAdapter.OnBookItemClicked {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //uruchomienie async taska:
        //new GetBooksListAsyncTask(this).execute();

        BookApiClient bookApiClient = new RetrofitApiClientFactory().create();

        bookApiClient.getBooksList().enqueue(new Callback<BookListResponse>() {
            @Override
            public void onResponse(Call<BookListResponse> call, Response<BookListResponse> response) {
                if(response.isSuccessful()) {
                    bookListDownloaded(response.body().getData());
                } else {
                    shorError();
                }
            }

            @Override
            public void onFailure(Call<BookListResponse> call, Throwable t) {
                t.printStackTrace();
                shorError();
            }
        });
    }

    @Override
    public void bookListDownloaded(List<BookListItem> listItems) {
        if(listItems == null) {
            return;
        }

        BookListAdapter bookListAdapter = new BookListAdapter(this, listItems, this);
        recyclerView.setAdapter(bookListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void shorError() {
        Toast.makeText(this, "Failed to download list!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bookClicked(BookListItem bookListItem) {
        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra("bookId", bookListItem.getId());
        startActivity(intent);
    }

}