package com.northwindlabs.kartikeya.creditmanagement;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.northwindlabs.kartikeya.creditmanagement.data.User;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {

    private Activity context;

    public UserAdapter(@NonNull Activity context, @NonNull ArrayList<User> users) {
        super(context, 0, users);
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        // Check if the existing view is being reused, otherwise inflate the view
        com.lucasurbas.listitemview.ListItemView listItemView = (com.lucasurbas.listitemview.ListItemView)convertView;
        if (listItemView == null) {
            listItemView = (com.lucasurbas.listitemview.ListItemView)LayoutInflater.from(getContext()).inflate(R.layout.users_list_item, parent, false);
        }

        User currentUser = getItem(position);

        listItemView.setTitle(currentUser.getUserName());
        listItemView.setSubtitle(currentUser.getUserCredits()+" credtis");

        return listItemView;
    }
}
