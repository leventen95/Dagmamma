package no.hiof.leventen.actionbar.Chat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import no.hiof.leventen.actionbar.R;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Conversation conversation;

    public ChatAdapter(Conversation conversation) {
        this.conversation = conversation;
    }

    public static class MessageInViewHolder extends RecyclerView.ViewHolder {
        TextView messageIn;
        TextView messageInDate;
        ImageView userImage;

        public MessageInViewHolder(@NonNull View itemView) {
            super(itemView);
            messageIn = itemView.findViewById(R.id.message_in);
            messageInDate = itemView.findViewById(R.id.message_in_date);
            userImage = itemView.findViewById(R.id.user_image);
        }
    }

    public static class MessageOutViewHolder extends RecyclerView.ViewHolder{
        TextView messageOut;
        TextView messageOutDate;
        ImageView userImage;

        public MessageOutViewHolder(@NonNull View itemView) {
            super(itemView);
            messageOut = itemView.findViewById(R.id.message_out);
            messageOutDate = itemView.findViewById(R.id.message_out_date);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(conversation.getConversationMessages().get(position).getFromUser() == /*Person.getCurrentUser().getName()*/"it mannen") {
            return 0;
        } else {
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i == 0) {
            return new MessageOutViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_message_out, viewGroup,false));
        } else {
            return new MessageInViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_message_in, viewGroup,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if(viewHolder.getItemViewType() == 0) {
            MessageOutViewHolder vh = (MessageOutViewHolder) viewHolder;
            vh.messageOut.setText(conversation.getConversationMessages().get(i).getMessageText());
            vh.messageOutDate.setText(new SimpleDateFormat("MM/dd/yyyy").format(conversation.getConversationMessages().get(i).getDate()));
        } else {
            MessageInViewHolder vh = (MessageInViewHolder) viewHolder;
            vh.messageIn.setText(conversation.getConversationMessages().get(i).getMessageText());
            vh.messageInDate.setText(new SimpleDateFormat("MM/dd/yyyy").format(conversation.getConversationMessages().get(i).getDate()));
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return conversation.getConversationMessages().size();
    }
}
