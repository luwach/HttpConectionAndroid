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

public class MainActivity extends AppCompatActivity
        implements GetBooksListAsyncTask.OnBookListDownloadedListener,
        BookListAdapter.OnBookItemClicked {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        new GetBooksListAsyncTask(this).execute();
    }

    @Override
    public void bookListDownloaded(List<BookListItem> listItems) {
        if(listItems == null) {
            Toast.makeText(this, "Failed to download list!", Toast.LENGTH_SHORT).show();
            return;
        }

        BookListAdapter bookListAdapter = new BookListAdapter(this, listItems, this);
        recyclerView.setAdapter(bookListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void bookClicked(BookListItem bookListItem) {
        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra("bookId", bookListItem.getId());
        startActivity(intent);
    }

}