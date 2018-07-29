package com.northwindlabs.kartikeya.creditmanagement;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.northwindlabs.kartikeya.creditmanagement.data.User;
import com.northwindlabs.kartikeya.creditmanagement.data.UserContract;
import com.northwindlabs.kartikeya.creditmanagement.data.UserCreditsDbHelper;
import com.special.ResideMenu.ResideMenu;

import java.util.ArrayList;

import static com.northwindlabs.kartikeya.creditmanagement.data.UserContract.UserEntry.TABLE_NAME;

public class UserFragment extends Fragment {
    private UserAdapter userAdapter;
    private View parentView;
    private ResideMenu resideMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_users, container, false);
        setUpViews();

        ListView userListView = parentView.findViewById(R.id.user_list);

        userAdapter = new UserAdapter(getActivity(), new ArrayList<User>());

        userAdapter.addAll(fetchUsersFromDatabase());
        userListView.setAdapter(userAdapter);

        return parentView;
    }

    private void setUpViews() {
        MainActivity parentActivity = (MainActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();

        // add gesture operation's ignored views
//        FrameLayout ignored_view = (FrameLayout) parentView.findViewById(R.id.ignored_view);
//        resideMenu.addIgnoredView(ignored_view);
    }

    ArrayList<User> fetchUsersFromDatabase(){
        final ArrayList<User> userArrayList = new ArrayList<>();

        UserCreditsDbHelper userCreditsDbHelper = new UserCreditsDbHelper(getContext());

        SQLiteDatabase sqLiteDatabase = userCreditsDbHelper.getReadableDatabase();

        Cursor mCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        int columnIndexUserName = mCursor.getColumnIndex(UserContract.UserEntry.COLUMN_USER_NAME);
        int columnIndexUserCredits = mCursor.getColumnIndex(UserContract.UserEntry.COLUMN_BALANCE);
        for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
            User user = new User(mCursor.getString(columnIndexUserName), mCursor.getInt(columnIndexUserCredits));
            userArrayList.add(user);
        }
        mCursor.close();
        return userArrayList;
    }
}
