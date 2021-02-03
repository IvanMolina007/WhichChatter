package com.example.whichchatter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterMessage extends RecyclerView.Adapter<AdapterMessage.ViewHolderData> {

    ArrayList<Messages> listMessages;

    public AdapterMessage(ArrayList<Messages> listMessages) {
        this.listMessages = listMessages;
    }

    @NonNull
    @Override
    public AdapterMessage.ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .activity_item_chat, null, false);
        return new AdapterMessage.ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        holder.mapData(listMessages.get(position));
    }

    @Override
    public int getItemCount() {return listMessages.size();}

    public class ViewHolderData extends RecyclerView.ViewHolder {

        TextView name, message;
        public ViewHolderData(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.textViewMessageUser);
            message = (TextView) itemView.findViewById(R.id.textViewMessageItem);
        }

        public void mapData(Messages messages) {
            name.setText(messages.received);
            message.setText(messages.message);
        }
    }
}
