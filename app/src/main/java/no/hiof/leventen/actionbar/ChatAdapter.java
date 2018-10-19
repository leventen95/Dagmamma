package no.hiof.leventen.actionbar;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    public ChatAdapter(ArrayList<ChatMessage>) {

    }

    public static class ChatViewHolder extends  RecyclerView.ViewHolder{

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder chatViewHolder, int i) {

    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter chatAdapter, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
