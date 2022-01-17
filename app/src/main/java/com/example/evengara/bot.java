package com.example.evengara;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class bot extends AppCompatActivity {
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
        setContentView(R.layout.abc);
//        linearLayout=(LinearLayout)findViewById(R.id.listv2);
//        bottomnavigation=(BottomNavigationView)findViewById(R.id.bottomnav);
//
//
//        bottomnavigation.setOnNavigationItemSelectedListener(this);
//        bottomnavigation.setItemIconTintList(null);
//        tv1=(TextView)findViewById(R.id.tv1);
//        tv2=(TextView)findViewById(R.id.tv2);

        listMessages= (ListView)findViewById(R.id.list_chat);
        bt1= (ImageView) findViewById(R.id.button_chat_send);
        adapterMessages = new MessagesAdapter(bot.this);
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
//        message.setDate("2020-12-12");
        adapterMessages.add(message);
        listMessages.setAdapter(adapterMessages);
//
//
//        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//         url = "http://" + sh.getString("ip","") + ":5000/android_chat_send";

//        tv1.setOnLongClickListener(this);
//        tv2.setOnLongClickListener(this);


//        bt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//
//                final String msggg=edtxttosent.getText().toString();
//                if(msggg.equalsIgnoreCase(""))
//                {
//                    edtxttosent.setError("enter message");
//                }
//                else {
//
//                    ChatMessage	message = new ChatMessage();
//                    message.setMessage(msggg);
//                    message.setUsername("Me");
//                    message.setIncomingMessage(false);
//                    message.setDate("2020-12-12");
//                    adapterMessages.add(message);
//                    listMessages.setAdapter(adapterMessages);
//
//
//                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                            new Response.Listener<String>() {
//                                @Override
//                                public void onResponse(String response) {
//                                    //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//
//                                    // response
//                                    try {
//                                        JSONObject jsonObj = new JSONObject(response);
//                                        String status=jsonObj.getString("status");
//                                        if(status.equalsIgnoreCase("ok"))
//                                        {
//                                            ChatMessage	message = new ChatMessage();
//                                            message.setMessage(jsonObj.getString("data"));
//                                            message.setUsername("Robo");
//                                            message.setIncomingMessage(true);
//                                            message.setDate("2020-12-12");
//                                            adapterMessages.add(message);
//                                            listMessages.setAdapter(adapterMessages);
//
//                                            edtxttosent.setText("");
//                                        }
//
//
//
//                                    }    catch (Exception e) {
//                                        Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            },
//                            new Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//                                    // error
//                                    Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                    ) {
//                        @Override
//                        protected Map<String, String> getParams() {
//
//                            Map<String, String> params = new HashMap<>();
//
//                            params.put("msg",msggg);
//
//                            return params;
//                        }
//                    };
//
//                    int MY_SOCKET_TIMEOUT_MS=100000;
//
//                    postRequest.setRetryPolicy(new DefaultRetryPolicy(
//                            MY_SOCKET_TIMEOUT_MS,
//                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                   requestQueue.add(postRequest);
//
//
//
//
//
//
//                }
//            }
//        });

    }

}