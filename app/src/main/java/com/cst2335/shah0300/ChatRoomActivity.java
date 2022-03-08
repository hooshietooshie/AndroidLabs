package com.cst2335.shah0300;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;


public class ChatRoomActivity extends AppCompatActivity {
    public static final String TAG = "ChatRoomActivity";
    EditText message;
    Button btn_send;
    Button btn_receive;
    Adapter myAdapter;
    ArrayList<Message> message_arl = new ArrayList<Message>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        btn_send = findViewById(R.id.button);
        btn_receive = findViewById(R.id.button4);
        message = findViewById(R.id.editText7);
        ListView list = findViewById(R.id.Listview);
        list.setAdapter(myAdapter = new Adapter());

        btn_send.setOnClickListener(v -> {
            String usr_message = message.getText().toString();
            Log.e(TAG, "Send button is clicked");

            if (!usr_message.isEmpty()) {
                message_arl.add(new Message(usr_message, true));
                message.setText("");
                myAdapter.notifyDataSetChanged();
            }
        });

        btn_receive.setOnClickListener(v -> {
            String usr_message = message.getText().toString();
            Log.e(TAG, "Recieve button is clicked");

            if (!usr_message.isEmpty()) {
                message_arl.add(new Message(usr_message, false));
                message.setText("");
                myAdapter.notifyDataSetChanged();
            }
        });

        list.setOnItemLongClickListener((adapterView, view, position, id) -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Do you want to delete this?")
                    .setMessage("The selected row is: " + position + "/nThe database id is: " + id)
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
            return message_arl.get(position);
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
            ImageView send_img = send_view.findViewById(R.id.send_Image);

            View recive_view = inflater.inflate(R.layout.message_recive, parent, false);
            TextView message_recive = recive_view.findViewById(R.id.recive_text);
            message_recive.setText(getItem(position).toString());
            ImageView recive_img = recive_view.findViewById(R.id.recive_Image);

            Message row = message_arl.get(position);
            if (row.sendOrReceive == true) {
                return send_view;
            }

            return recive_view;
        }
    }

    public class Message {
        String typed_Message;
        boolean sendOrReceive;

        public Message(String typed_Message, boolean sendOrReceive) {
            this.typed_Message = typed_Message;
            this.sendOrReceive = sendOrReceive;
        }

        public String getTyped_Message() {
            return typed_Message;
        }

        public boolean isSendOrReceive() {
            return sendOrReceive;
        }
    }
}