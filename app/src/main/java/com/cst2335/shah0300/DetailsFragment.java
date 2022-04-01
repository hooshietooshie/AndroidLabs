package com.cst2335.shah0300;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {
    TextView message;
    TextView id_number;
    CheckBox check_box;
    Button hide_btn;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String message_text;
    private long message_id;
    private boolean isSend;
    private boolean tab_check;


    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            message_text = getArguments().getString("message", "");
            message_id = getArguments().getLong("id", 0);
            isSend = getArguments().getBoolean("isSent", false);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        message = view.findViewById(R.id.message_here);
        id_number = view.findViewById(R.id.textView7);
        check_box = view.findViewById(R.id.checkBox);
        hide_btn = view.findViewById(R.id.button5);

//        Bundle bundle = this.getArguments();
//        tab_check = view.findViewById(R.id.frame_layout) != null;
//        //tab_check = bundle.getBoolean("Is_tablet");
//        message_text = bundle.getString("Message_sent");
//        message.setText(message_text);
//        message_id = bundle.getLong("id_number");
//        id_number.setText("" + message_id);
//        isSend = bundle.getBoolean("Send_Or_Receive");

        if (savedInstanceState == null) {
            if (getArguments() != null) {
                message.setText(message_text.toString());
                id_number.setText("ID = " + Long.toString(message_id));
                if (isSend) {
                    check_box.setChecked(true);
                }

            }

        }
        hide_btn.setOnClickListener(v -> {
            remove();
        });
        return view;
    }

    private void remove() {


        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.remove(this);
        ft.commit();
//        } else {
//            Intent goToChat = new Intent(getActivity().getApplication(), ChatRoomActivity.class);
//            startActivity(goToChat);
//        }
    }
}