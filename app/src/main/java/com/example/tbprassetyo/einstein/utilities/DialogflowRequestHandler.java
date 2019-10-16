package com.example.tbprassetyo.einstein.utilities;

import android.os.AsyncTask;

import com.example.tbprassetyo.einstein.model.Reply;
import com.example.tbprassetyo.einstein.interfaces.OnGetReplyListener;

import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

public class DialogflowRequestHandler extends AsyncTask<AIRequest, Void, Reply> {
    OnGetReplyListener listener;
    AIDataService aiDataService;

    public DialogflowRequestHandler(OnGetReplyListener listener, AIDataService aiDataService) {
        this.listener = listener;
        this.aiDataService = aiDataService;
    }

    @Override
    protected Reply doInBackground(AIRequest... params) {
        final AIRequest request = params[0];

        Reply reply = null;
        try {
            AIResponse response = aiDataService.request(request);
            if(response != null){
                reply = DialogflowResponseParser.getSimpleResponse(response);
            }
        } catch (AIServiceException e) {

        }
        return reply;
    }

    @Override
    protected void onPostExecute(Reply reply) {
        listener.onGetReply(reply);
    }
}
