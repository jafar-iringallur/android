package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class chat_sample extends AppCompatActivity {
    LinearLayout linearLayout;
    BottomNavigationView bottomnavigation;


    MessagesAdapter adapterMessages;
    ListView listMessages;
    ImageView bt1;
    EditText edtxttosent;

    SharedPreferences sh;
    String url="",url1="",lid,toid,shop_id;
    String lastid;
    ArrayList<String> from,message,date;
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_sample);

        listMessages= (ListView)findViewById(R.id.list_chat);
        bt1= (ImageView) findViewById(R.id.button_chat_send);
        adapterMessages = new MessagesAdapter(chat_sample.this);
        edtxttosent=(EditText)findViewById(R.id.input_chat_message);
        listMessages.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listMessages.setStackFromBottom(true);
//
        from=new ArrayList<>();
        message=new ArrayList<>();
        date=new ArrayList<>();

        ChatMessage	message = new ChatMessage();
        message.setMessage("Welcom to AugmeniX");
        message.setUsername("Robo");
        message.setIncomingMessage(true);
        message.setDate("2020-12-12");
        adapterMessages.add(message);
        listMessages.setAdapter(adapterMessages);
    }
}