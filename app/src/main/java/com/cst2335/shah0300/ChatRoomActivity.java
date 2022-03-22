package com.cst2335.shah0300;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class ChatRoomActivity extends AppCompatActivity {
    public static final String TAG = "ChatRoomActivity";
    MyOpenHelper myOpener;
    SQLiteDatabase theDatabase;
    ListAdapter listAdapter;
    EditText message;
    Button btn_send;
    Button btn_receive;
    Adapter myAdapter;
    ArrayList<Message> message_arl = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        myOpener = new MyOpenHelper(this);


        theDatabase = myOpener.getWritableDatabase();

        Cursor results = theDatabase.rawQuery("Select * from " + MyOpenHelper.TABLE_NAME + ";", null);

        int idIndex = results.getColumnIndex(MyOpenHelper.COL_ID);
        int messageIndex = results.getColumnIndex(MyOpenHelper.COL_MESSAGE);
        int sendOrReceiveIndex = results.getColumnIndex( MyOpenHelper.COL_SEND_RECEIVE);
        results.moveToPosition(-1);

        while(results.moveToNext()){
            long id = results.getLong(idIndex);
            String message = results.getString(messageIndex);
            boolean isFromSelf = results.getInt(sendOrReceiveIndex) == 1;
            message_arl.list.add(new Message(message, isFromSelf, id));
            myAdapter.notifyDataSetChanged();
        }

        btn_send = findViewById(R.id.button);
        btn_receive = findViewById(R.id.button4);
        message = findViewById(R.id.editText7);
        ListView list = findViewById(R.id.Listview);
        list.setAdapter(myAdapter = new Adapter());



        btn_send.setOnClickListener(v -> {
            String usr_message = message.getText().toString();
            Log.e(TAG, "Send button is clicked");

            if (!usr_message.isEmpty()) {
                message_arl.add(new Message(usr_message, true, ));
                message.setText("");
                myAdapter.notifyDataSetChanged();
            }
        });

        btn_receive.setOnClickListener(v -> {
            String usr_message = message.getText().toString();
            Log.e(TAG, "Receive button is clicked");

            if (!usr_message.isEmpty()) {
                message_arl.add(new Message(usr_message, false));
                message.setText("");
                myAdapter.notifyDataSetChanged();
            }
        });

        list.setOnItemLongClickListener((adapterView, view, position, id) -> {
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

    private void utilMessage(boolean fromSelf) {

        Message message1 = new Message(message.getText().toString(), fromSelf, id);
        ContentValues newRow = new ContentValues();
        newRow.put(MyOpenHelper.COL_MESSAGE, message1.getText());
        newRow.put(MyOpenHelper.COL_SEND_RECEIVE, fromSelf ? 1 : 0);
        long id = theDatabase.insert(MyOpenHelper.TABLE_NAME, null, newRow);
        message1.setId(id);
        Adapter.list.add(message1);
        message.setText("");
        myAdapter.notifyDataSetChanged();

    }

    private void createDialog(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setView(view);
        alertDialog.setTitle("Do you want to delete this?");
        alertDialog.setMessage("The selected row is: " + myAdapter.getCount());
        alertDialog.setMessage("The database id is: ");
        EditText input = new EditText(getApplicationContext());
        alertDialog.setView(input);
        alertDialog.setPositiveButton("remove", (click , v) -> {
           String item = input.getText().toString();
           myAdapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Item deleted", Toast.LENGTH_SHORT).show();
        });

        alertDialog.create().show();
        alertDialog.setNegativeButton("cancel", (click, v2) -> {
            Log.i(TAG, "Cancelled");
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
            ImageView send_img = send_view.findViewById(R.id.send_Image);

            View receive_view = inflater.inflate(R.layout.message_receive, parent, false);
            TextView message_receive = receive_view.findViewById(R.id.receive_text);
            message_receive.setText(getItem(position).toString());
            ImageView receive_img = receive_view.findViewById(R.id.receive_Image);

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
}