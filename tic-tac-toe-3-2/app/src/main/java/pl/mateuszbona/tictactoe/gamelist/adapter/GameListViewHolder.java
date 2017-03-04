package pl.mateuszbona.tictactoe.gamelist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mateuszbona.tictactoe.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.mateuszbona.tictactoe.gamelist.model.Game;

public class GameListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.gameName)
    TextView gameName;

    @BindView(R.id.gameCreator)
    TextView gameCreator;

    @BindView(R.id.gamePlayer)
    TextView gamePlayer;

    private final GameListAdapter.ItemClickListener itemClickListener;

    private Game game;

    public GameListViewHolder(View itemView, final GameListAdapter.ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.itemClicked(game);
            }
        });
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
