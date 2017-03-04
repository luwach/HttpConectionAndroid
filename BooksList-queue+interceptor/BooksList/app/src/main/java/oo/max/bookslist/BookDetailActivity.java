package oo.max.bookslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import oo.max.bookslist.async.GetBookDetailsAsyncTask;
import oo.max.bookslist.model.Book;


public class BookDetailActivity extends AppCompatActivity implements GetBookDetailsAsyncTask.OnBookDownloadedListener {

    private TextView name;
    private TextView author;
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String bookId = getIntent().getStringExtra("bookId");

        if(bookId == null) {
            finish();
            return;
        }

        name = (TextView) findViewById(R.id.name);
        author = (TextView) findViewById(R.id.author);
        image = (ImageView) findViewById(R.id.image);

        //uruchomienie async taska
        new GetBookDetailsAsyncTask(this).execute(bookId);
    }

    @Override
    //metoda z interface'u OnBookDownloadedListener (activity implementuje ten interface)
    public void bookDownloaded(Book book) {
        //sprawdzamy, czy nie dostaliśmy null'a
        if(book == null) {
            //wyświetlenie błędu - w prawdziwej aplikacji do rozbudowy
            Toast.makeText(this, "failed to download details!", Toast.LENGTH_SHORT).show();
            //finish - zamyka activity
            finish();
            return;
        }

        //jeśli book != null to wyświetlamy resultat
        name.setText(book.getName());
        author.setText(book.getAuthor());

        //wyświetlenie obrazja przy użyciu Picasso
        Picasso.with(this)
                .load(book.getImageUrl())
                .fit()
                .centerCrop()
                .into(image);
    }

}