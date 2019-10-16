package com.example.tbprassetyo.einstein;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tbprassetyo.einstein.model.Message;

public class  ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder>{

    private Message[] mCursor;
    private Context mContext;

    public ChatAdapter(Context context, Message[] cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);


        if(viewType == 0){
            return new MessageViewHolder(inflater.inflate(R.layout.list_item_message_send, parent, false));
        }else{
            return new MessageViewHolder(inflater.inflate(R.layout.list_item_message_rcv, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, final int position) {

        if(mCursor[position]==null){
            return;
        }


        String body = mCursor[position].getBody();
        holder.nameTextView.setText(body);
    }

    @Override
    public int getItemCount() {
        return mCursor.length;
    }

    @Override
    public int getItemViewType(int position) {
        return mCursor[position].getMsgType();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {

        // Will display the guest name
        TextView nameTextView;

        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews
         *
         * @param itemView The View that you inflated in
         *                 {@link ChatAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public MessageViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.text_message);
        }

    }

    public void swapCursor(Message[] newCursor) {
        //Update the local mCursor to be equal to  newCursor
        mCursor = newCursor;

        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }
}
