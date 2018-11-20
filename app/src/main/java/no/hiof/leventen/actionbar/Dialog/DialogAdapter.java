package no.hiof.leventen.actionbar.Dialog;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import no.hiof.leventen.actionbar.Chat.Conversation;
import no.hiof.leventen.actionbar.R;


public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.DialogViewHolder> {

    private List<Conversation> conversations;

    public DialogAdapter(List<Conversation> conversations) {
        this.conversations = conversations;
    }

    public static class DialogViewHolder extends RecyclerView.ViewHolder{
        ImageView userImage;
        TextView userFullName;
        TextView lastMessage;

        public DialogViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.dialogs_user_image);
            userFullName = itemView.findViewById(R.id.dialogs_user_name);
            lastMessage = itemView.findViewById(R.id.dialogs_last_message);
        }
    }

    @NonNull
    @Override
    public DialogViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_dialog_item, viewGroup,false);
        DialogViewHolder chatViewHolder = new DialogViewHolder(v);
        return chatViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DialogViewHolder dialogViewHolder, int i) {
        //dialogViewHolder.userImage.setImageBitmap(conversations.get(i).getOtherUser().get);
        dialogViewHolder.userFullName.setText(conversations.get(i).getOtherUser());
        dialogViewHolder.lastMessage.setText(conversations.get(i).getLastMessage().getMessageText());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return conversations.size();
    }
}
