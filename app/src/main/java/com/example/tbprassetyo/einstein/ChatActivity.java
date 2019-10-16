package com.example.tbprassetyo.einstein;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tbprassetyo.einstein.data.DBHelper;
import com.example.tbprassetyo.einstein.interfaces.OnGetReplyListener;
import com.example.tbprassetyo.einstein.model.Message;
import com.example.tbprassetyo.einstein.model.Reply;
import com.example.tbprassetyo.einstein.utilities.DialogflowRequestHandler;
import com.example.tbprassetyo.einstein.utilities.SharedPrefManager;

import ai.api.AIDataService;
import ai.api.android.AIConfiguration;
import ai.api.model.AIRequest;

public class ChatActivity extends AppCompatActivity implements OnGetReplyListener{

    private static DBHelper dbHelper;

    private ChatAdapter chatAdapter;

    private AIDataService aiDataService;


    private EditText mNewGuestNameEditText;
    private RecyclerView waitlistRecyclerView;

    static SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPrefManager = new SharedPrefManager(this);

        if (!sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(ChatActivity.this, WelcomeActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            //finish();
        }

        setContentView(R.layout.activity_chat);

        // Set local attributes to corresponding views
        waitlistRecyclerView = (RecyclerView) this.findViewById(R.id.all_guests_list_view);
        mNewGuestNameEditText = (EditText) this.findViewById(R.id.person_name_edit_text);

        final LinearLayoutManager mLayoutManager=new LinearLayoutManager(this);
        mLayoutManager.setStackFromEnd(true);
        // Set layout for the RecyclerView, because it's a list we are using the linear layout
        waitlistRecyclerView.setLayoutManager(mLayoutManager);


        // Create a DB helper (this will create the DB if run for the first time)
        dbHelper = new DBHelper(this);
        Message[] messages = dbHelper.getAllMessages();


        chatAdapter = new ChatAdapter(this, messages);
        // Link the adapter to the RecyclerView
        waitlistRecyclerView.setAdapter(chatAdapter);


        final AIConfiguration config = new AIConfiguration(getResources().getString(R.string.client_access_token),
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        aiDataService = new AIDataService(config);



        mNewGuestNameEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                sendMessage();
                return true;
            }
        });

    }

    public static SharedPrefManager getSharedPreferencesObj() {
        return sharedPrefManager;
    }

    public static DBHelper getDBHelper() {
        return dbHelper;
    }

    public void sendMessage() {

        if (mNewGuestNameEditText.getText().length() == 0) {
            return;
        }

        String userInput = mNewGuestNameEditText.getText().toString();


        Message message = new Message(userInput,0);
        int msgId= (int) dbHelper.addNewMessage(message);
        message.setId(msgId);


        // call chatAdapter.swapCursor to update the cursor by passing in getAllGuests()
        // Update the cursor in the adapter to trigger UI to display the new list
        chatAdapter.swapCursor(dbHelper.getAllMessages());
        waitlistRecyclerView.smoothScrollToPosition(chatAdapter.getItemCount());

        final AIRequest aiRequest = new AIRequest();
        aiRequest.setQuery(userInput);

        DialogflowRequestHandler dialogflowTask = new DialogflowRequestHandler(this, aiDataService);
        dialogflowTask.execute(aiRequest);

        mNewGuestNameEditText.getText().clear();
    }

    @Override
    public void onGetReply(Reply reply) {
        dbHelper.addNewMessage(new Message(reply.getBody(),1));

        chatAdapter.swapCursor(dbHelper.getAllMessages());
        waitlistRecyclerView.smoothScrollToPosition(chatAdapter.getItemCount());
    }

    // Override onCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Within onCreateOptionsMenu, use getMenuInflater().inflate to inflate the menu
        getMenuInflater().inflate(R.menu.main, menu);
        // Return true to display your menu
        return true;
    }

    // If you do NOT handle the menu click, return super.onOptionsItemSelected to let Android handle the menu click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        Context context = ChatActivity.this;

        switch (itemThatWasClickedId) {
            case R.id.action_log:
                Toast.makeText(context, "Log clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ChatActivity.this, LogActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                return true;
            case R.id.action_logout:
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(ChatActivity.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                //finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        chatAdapter.swapCursor(dbHelper.getAllMessages());
        waitlistRecyclerView.smoothScrollToPosition(chatAdapter.getItemCount());
    }

}
