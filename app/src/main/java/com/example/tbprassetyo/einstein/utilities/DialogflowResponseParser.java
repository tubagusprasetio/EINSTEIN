package com.example.tbprassetyo.einstein.utilities;
import com.example.tbprassetyo.einstein.model.Reply;

import ai.api.model.AIResponse;

public class DialogflowResponseParser {
    public static Reply getSimpleResponse(AIResponse response){

        return new Reply(response.getResult().getFulfillment().getSpeech(), response.getResult().getAction());
    }
}
