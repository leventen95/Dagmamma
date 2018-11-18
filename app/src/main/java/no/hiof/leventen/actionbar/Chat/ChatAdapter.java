package no.hiof.leventen.actionbar.Chat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import no.hiof.leventen.actionbar.R;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    List<Message> messages;


    public ChatAdapter(List<Message> messages) {
        this.messages = messages;
    }

    public static class ChatViewHolder extends  RecyclerView.ViewHolder{
        TextView messageIn;
        TextView messageInDate;
        ImageView userImage;



        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            messageIn = itemView.findViewById(R.id.message_in);
            messageInDate = itemView.findViewById(R.id.message_in_date);
            userImage = itemView.findViewById(R.id.user_image);
        }
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_message_in, viewGroup,false);
        ChatViewHolder chatViewHolder = new ChatViewHolder(v);
        return chatViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder chatViewHolder, int i) {
        chatViewHolder.messageIn.setText(messages.get(i).getMessageText());
        chatViewHolder.messageInDate.setText(new SimpleDateFormat("MM/dd/yyyy").format(messages.get(i).getDate()));
    //    chatViewHolder.userImage.setImageResource(persons.get(i).getMessageUser());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
