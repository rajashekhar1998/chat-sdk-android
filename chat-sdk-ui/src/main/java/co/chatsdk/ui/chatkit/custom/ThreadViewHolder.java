package co.chatsdk.ui.chatkit.custom;

import android.view.View;
import android.widget.ImageView;

import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import co.chatsdk.core.dao.Message;
import co.chatsdk.ui.R;
import co.chatsdk.ui.chatkit.model.MessageHolder;
import co.chatsdk.ui.chatkit.model.ThreadHolder;

public class ThreadViewHolder extends DialogsListAdapter.DialogViewHolder<ThreadHolder> {

    private View onlineIndicator;
    private ImageView readReceipt;

    public ThreadViewHolder(View itemView) {
        super(itemView);
        onlineIndicator = itemView.findViewById(R.id.onlineIndicator);
        readReceipt = itemView.findViewById(R.id.image_read_receipt);
    }

    @Override
    public void onBind(ThreadHolder dialog) {
        super.onBind(dialog);

        if (dialog.getUsers().size() == 1) {
            boolean isOnline = dialog.getUsers().get(0).isOnline();
            onlineIndicator.setVisibility(View.VISIBLE);
            if (isOnline) {
                onlineIndicator.setBackgroundResource(R.drawable.chatkit_shape_bubble_online);
            } else {
                onlineIndicator.setBackgroundResource(R.drawable.chatkit_shape_bubble_offline);
            }
        } else {
            onlineIndicator.setVisibility(View.GONE);
        }

        MessageHolder lastMessageHolder = dialog.getLastMessage();
        if (lastMessageHolder == null || lastMessageHolder.getReadStatusResourceId() == null) {
            readReceipt.setVisibility(View.GONE);
        } else {
            readReceipt.setVisibility(View.VISIBLE);
            readReceipt.setImageResource(lastMessageHolder.getReadStatusResourceId());
        }
    }

}