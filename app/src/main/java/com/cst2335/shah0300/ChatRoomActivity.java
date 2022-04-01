package com.cst2335.shah0300;

//import android.app.FragmentManager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class ChatRoomActivity extends AppCompatActivity {
    public static final String TAG = "ChatRoomActivity";
    MyOpenHelper myOpener;
    SQLiteDatabase theDatabase;
    EditText message;
    Button btn_send;
    Button btn_receive;
    Adapter myAdapter;
    ListView list;
    FrameLayout frame;
    Boolean tab_check = false;
    ArrayList<Message> message_arl = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        btn_send = findViewById(R.id.button);
        btn_receive = findViewById(R.id.button4);
        message = findViewById(R.id.editText7);
        list = findViewById(R.id.Listview);
        list.setAdapter(myAdapter = new Adapter());
        //frame = findViewById(R.id.frame_layout);


        tab_check = findViewById(R.id.frame_layout) != null;


//        FragmentManager frag_mag = getFragmentManager();

//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.setReorderingAllowed(true);
//        ft.add(R.id.frame_layout, fragment);
//        ft.commit();


        myOpener = new MyOpenHelper(this);


        theDatabase = myOpener.getWritableDatabase();

        Cursor results = theDatabase.rawQuery("Select * from " + MyOpenHelper.TABLE_NAME + ";", null);
        print_Cursor(results, theDatabase.getVersion());


        btn_send.setOnClickListener(v -> {
            String usr_message = message.getText().toString();
            Log.e(TAG, "Send button is clicked");

            if (!usr_message.isEmpty()) {
                ContentValues newRow = new ContentValues();
                newRow.put(MyOpenHelper.COL_MESSAGE, usr_message);
                newRow.put(MyOpenHelper.COL_SEND_RECEIVE, 1);
                long id = theDatabase.insert(MyOpenHelper.TABLE_NAME, null, newRow);
                message_arl.add(new Message(usr_message, true, id));
                message.setText("");
                myAdapter.notifyDataSetChanged();
            }
        });

        btn_receive.setOnClickListener(v -> {
            String usr_message = message.getText().toString();
            Log.e(TAG, "Receive button is clicked");

            if (!usr_message.isEmpty()) {
                ContentValues newRow = new ContentValues();
                newRow.put(MyOpenHelper.COL_MESSAGE, usr_message);
                newRow.put(MyOpenHelper.COL_SEND_RECEIVE, 0);
                long id = theDatabase.insert(MyOpenHelper.TABLE_NAME, null, newRow);
                message_arl.add(new Message(usr_message, false, id));
                message.setText("");
                myAdapter.notifyDataSetChanged();
            }
        });

        list.setOnItemClickListener((list1, view, position, id) -> {
            DetailsFragment fragment = new DetailsFragment();
//            FragmentManager fm = getSupportFragmentManager();


            if (tab_check) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, fragment)
                        .commit();

            } else {
                Intent load_frag = new Intent(this, EmptyActivity.class);
                startActivity(load_frag);
            }


        });


        list.setOnItemLongClickListener((adapterView, view, position, id) -> {
            Message selected_message = message_arl.get(position);
            id = selected_message.getId();
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Do you want to delete this?")
                    .setMessage("The selected row is: " + position + "\n The database id is: " + id)
                    .setPositiveButton("Yes", (v, args) -> {
                        message_arl.remove(position);
                        myAdapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("No", (v, args) -> {
                    })
                    .create().show();

            return true;
        });


    }


    public class Adapter extends BaseAdapter {


        @Override
        public int getCount() {
            return message_arl.size();
        }

        @Override
        public Object getItem(int position) {
            Message row = message_arl.get(position);
            return row.typed_Message;
        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public View getView(int position, View old, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();

            View send_view = inflater.inflate(R.layout.message_send, parent, false);
            TextView message_send = send_view.findViewById(R.id.send_text);
            message_send.setText(getItem(position).toString());
            //ImageView send_img = send_view.findViewById(R.id.send_Image);

            View receive_view = inflater.inflate(R.layout.message_receive, parent, false);
            TextView message_receive = receive_view.findViewById(R.id.receive_text);
            message_receive.setText(getItem(position).toString());
            //ImageView receive_img = receive_view.findViewById(R.id.receive_Image);

            Message row = message_arl.get(position);
            if (row.sendOrReceive) {
                return send_view;
            }

            return receive_view;
        }
    }

    public class Message {
        String typed_Message;
        boolean sendOrReceive;
        long id;

        public Message(String typed_Message, boolean sendOrReceive, long id) {
            this.typed_Message = typed_Message;
            this.sendOrReceive = sendOrReceive;
            this.id = id;
        }

        public String getTyped_Message() {
            return typed_Message;
        }

        public boolean isSendOrReceive() {
            return sendOrReceive;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }

    public Cursor print_Cursor(Cursor c, int version) {
        int id_Index = c.getColumnIndex(MyOpenHelper.COL_ID);
        int message_Index = c.getColumnIndex(MyOpenHelper.COL_MESSAGE);
        int sendOrReceive_Index = c.getColumnIndex(MyOpenHelper.COL_SEND_RECEIVE);

        int row_Count = c.getCount();
        int column_Count = c.getColumnCount();

        Log.i(TAG, "Database version: " + version);
        Log.i(TAG, "Row count: " + row_Count);
        Log.i(TAG, "Column count: " + column_Count);

        Log.i(TAG, "Column Names: ");

        for (int i = 0; i < column_Count; i++) {
            String column_Name = c.getColumnName(i);
            Log.i(TAG, "Column " + (i + 1) + ": " + column_Name);
        }

        while (c.moveToNext()) {
            int id = c.getInt(id_Index);
            String the_message = c.getString(message_Index);
            int is_Sent = c.getInt(sendOrReceive_Index);

            switch (is_Sent) {
                case 1:
                    message_arl.add(new Message(the_message, true, id));
                    break;
                case 2:
                    message_arl.add(new Message(the_message, false, id));
            }
        }
        return c;
    }

}