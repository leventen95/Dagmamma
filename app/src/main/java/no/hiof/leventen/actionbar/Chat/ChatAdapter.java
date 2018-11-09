package no.hiof.leventen.actionbar.Chat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import no.hiof.leventen.actionbar.R;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    List<ChatMessage> messages;

    public static class ChatViewHolder extends  RecyclerView.ViewHolder{
        TextView message;
        TextView messageDate;
        ImageView userImage;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message_in);
            messageDate = itemView.findViewById(R.id.message_in_date);
            userImage = itemView.findViewById(R.id.user_image);
        }
    }

    public ChatAdapter(List<ChatMessage> messages) { this.messages = messages; }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_message_in, viewGroup,false);
        ChatViewHolder chatViewHolder = new ChatViewHolder(v);
        return chatViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder chatViewHolder, int i) {
        chatViewHolder.message.setText(messages.get(i).getMessageText());
        chatViewHolder.messageDate.setText(DateFormat.format("MM/dd/yyyy", new Date(messages.get(i).getMessageDate())).toString());
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
