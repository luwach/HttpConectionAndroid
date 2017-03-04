package pl.mateuszbona.tictactoe.gamelist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mateuszbona.tictactoe.R;

import java.util.List;

import pl.mateuszbona.tictactoe.gamelist.model.Game;

public class GameListAdapter extends RecyclerView.Adapter<GameListViewHolder> {

    private final Context context;
    private final List<Game> games;
    private final LayoutInflater layoutInflater;
    private final ItemClickListener itemClickListener;

    public GameListAdapter(Context context, List<Game> games, ItemClickListener itemClickListener) {
        this.context = context;
        this.games = games;
        layoutInflater = LayoutInflater.from(context);
        this.itemClickListener = itemClickListener;
    }

    @Override
    public GameListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.view_game_item, parent, false);
        return new GameListViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(GameListViewHolder holder, int position) {
        Game game = games.get(position);

        holder.gameCreator.setText(game.getCreator());
        holder.gameName.setText(game.getName());
        holder.gamePlayer.setText(game.getPlayer());

        holder.setGame(game);

    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public interface ItemClickListener {
        void itemClicked(Game game);
    }

}
