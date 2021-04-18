package mihaelantolovic.ferit.lv4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    private ArrayList<Player> players;

    public PlayerAdapter(ArrayList<Player> players){
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View playerView = inflater.inflate(R.layout.player, parent, false);
        ViewHolder playerViewHolder = new ViewHolder(playerView);
        return playerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerAdapter.ViewHolder holder, int position) {
        Player player = this.players.get(position);
        holder.tvPlayerName.setText(player.getPlayerName());
        holder.tvTries.setText(String.valueOf(player.getNumTries()));
    }

    @Override
    public int getItemCount() {
        return this.players.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvPlayerName, tvTries;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvPlayerName = (TextView) itemView.findViewById(R.id.tvStatsPlayerName);
            this.tvTries      = (TextView) itemView.findViewById(R.id.tvStatsTries);
        }
    }
}
