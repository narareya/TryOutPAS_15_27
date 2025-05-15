package com.example.tryoutpas_15_27.Premier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryoutpas_15_27.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {
    private List<Team> teamList;
    private Context context;

    public TeamAdapter(Context context, List<Team> teamList) {
        this.context = context;
        this.teamList = teamList;

    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_team, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        Team team = teamList.get(position);
        holder.txtTeamName.setText(team.getStrTeam());
        holder.txtStadiumName.setText(team.getStrStadium());
        Picasso.get().load(team.getStrBadge()).into(holder.imageLogo);
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder {
        TextView txtTeamName, txtStadiumName;
        ImageView imageLogo;

        public TeamViewHolder(View itemView) {
            super(itemView);
            txtTeamName = itemView.findViewById(R.id.txtTeamName);
            txtStadiumName = itemView.findViewById(R.id.txtStadiumName);
            imageLogo = itemView.findViewById(R.id.imageLogo);
        }
    }
}

