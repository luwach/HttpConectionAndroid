package oo.max.bookslist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import oo.max.bookslist.R;
import oo.max.bookslist.model.BookListItem;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListViewHolder> {

    private final Context context;
    private final List<BookListItem> bookListItems;
    private final OnBookItemClicked onBookItemClicked;
    private final LayoutInflater layoutInflater;

    public BookListAdapter(Context context, List<BookListItem> bookListItems, OnBookItemClicked onBookItemClicked) {
        this.context = context;
        this.bookListItems = bookListItems;
        layoutInflater = LayoutInflater.from(context);

        //on book clicked - callback przekazany i implementowany przez activity
        this.onBookItemClicked = onBookItemClicked;
    }

    @Override
    public BookListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //tworzymy widok i view holdera
        View view = layoutInflater.inflate(R.layout.view_book_item, parent, false);
        //to każdego view holdera przekazujemy callbacka
        return new BookListViewHolder(view, onBookItemClicked);
    }

    @Override
    public void onBindViewHolder(BookListViewHolder holder, int position) {
        BookListItem bookListItem = bookListItems.get(position);

        holder.bookName.setText(bookListItem.getName());

        //ustawiamy book list item, żeby móc przekazać go do callbacka kiedy użytkownik kliknie element
        holder.bookListItem = bookListItem;
    }

    @Override
    public int getItemCount() {
        return bookListItems.size(); // koniecznie zwracamy ilość elementów!
    }

    public static class BookListViewHolder extends RecyclerView.ViewHolder {
        //view holder, trzyma widoki i reference do aktualnie wyświetlanego elementu
        private final OnBookItemClicked onBookItemClicked;
        private TextView bookName;
        private BookListItem bookListItem;

        public BookListViewHolder(View itemView, final OnBookItemClicked onBookItemClicked) {
            super(itemView);
            this.onBookItemClicked = onBookItemClicked;
            this.bookName = (TextView) itemView.findViewById(R.id.name);

            // ustawiamy click listenera na pojedyńczym elemencie, albo całym itemView
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBookItemClicked.bookClicked(bookListItem);
                }
            });
        }
    }

    public interface OnBookItemClicked {
        void bookClicked(BookListItem bookListItem);
    }
}
