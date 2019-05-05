package com.example.votingapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.votingapp.Financial;
import com.example.votingapp.Model.CandidateDisplay;
import com.example.votingapp.Organizer;
import com.example.votingapp.President;
import com.example.votingapp.R;
import com.example.votingapp.SecretaryPage;
import com.example.votingapp.WomensCommissioner;

import java.util.ArrayList;

public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.ViewHolder> {

int index=-1;
    private Context context;
    private ArrayList<CandidateDisplay> candidates;

    public CandidateAdapter(Context context, ArrayList<CandidateDisplay> candidates) {
        this.context = context;
        this.candidates = candidates;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidate_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final CandidateDisplay candidate = candidates.get(holder.getAdapterPosition());
        holder.candidateName.setText(candidate.getCandidateName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = holder.getAdapterPosition();
                //Toast.makeText(context, "You have selected: "+candidate.getCandidateName(), Toast.LENGTH_SHORT).show();
                if(context instanceof President) {
                    Log.d("adapterTest", candidate.getCandidateName());
                    new President().setSelectedPresident(candidate.getCandidateName());
                }else if (context instanceof SecretaryPage){
                    new SecretaryPage().setSelectedSecretary(candidate.getCandidateName());
                }
                else if (context instanceof Financial ){
                    new Financial().setSelectedFinancial(candidate.getCandidateName());
                }else if (context instanceof Organizer){
                    new Organizer().setSelectedOrganizer(candidate.getCandidateName());
                }
                else if (context instanceof WomensCommissioner){
                    new WomensCommissioner().setSelectedWomen(candidate.getCandidateName());

                }
                notifyDataSetChanged();
            }
        });

        if(index==holder.getAdapterPosition()){
            holder.cardView.setBackgroundColor(Color.parseColor("#FFFDD0"));

        }else{
            holder.cardView.setBackgroundColor(Color.parseColor("#FFFFFF"));

        }
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView candidateName;
        ImageView candidateImage;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            candidateName = itemView.findViewById(R.id.A1);
            candidateImage = itemView.findViewById(R.id.tintinpic);
            cardView = itemView.findViewById(R.id.candidate1);
        }
    }

}
