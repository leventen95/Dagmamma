package no.hiof.leventen.actionbar;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    List<ChatMessage> messages;

    public static class ChatViewHolder extends  RecyclerView.ViewHolder{
        TextView message;
        TextView messageDate;
        ImageView userImage;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

    public ChatAdapter(List<ChatMessage> messages) { this.messages = messages; }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_layout, viewGroup,false);
        ChatViewHolder chatViewHolder = new ChatViewHolder(v);
        return chatViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder chatViewHolder, int i) {
        chatViewHolder..setText(persons.get(i).name);
        chatViewHolder.personAge.setText(persons.get(i).age+" år");
        chatViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
    }
}
