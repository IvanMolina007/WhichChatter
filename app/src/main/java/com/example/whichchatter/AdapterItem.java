package com.example.whichchatter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.ViewHolderData> {

    ArrayList<User> listUserses;
    private Context context;

    public AdapterItem(ArrayList<User> listUserses) {
        this.listUserses = listUserses;
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .activity_item_user, null, false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, final int position) {
        holder.mapData(listUserses.get(position));

        final User positionUser = listUserses.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivityIntent = new Intent(context, ChatActivity.class);
                nextActivityIntent.putExtra("nameUser", positionUser.getName());
                nextActivityIntent.putExtra("idUIDuser", positionUser.getidUID());
                context.startActivity(nextActivityIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUserses.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {

        TextView name, desc, phone;
        public ViewHolderData(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();
            name = (TextView) itemView.findViewById(R.id.textViewUserName);
            desc = (TextView) itemView.findViewById(R.id.textViewUserProfile);
            phone = (TextView) itemView.findViewById(R.id.textViewUserPhone);
        }

        public void mapData(User user) {
            name.setText(user.getName());
            desc.setText(user.getDescription());
            phone.setText(user.getMobile());
        }
    }
}

