package com.dianmo.flash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.dianmo.flash.Entity.Friend;
import com.facebook.drawee.view.SimpleDraweeView;

public class FriendMessage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendmessage);
        final Friend friend=new Friend(getIntent().getStringExtra("img"),getIntent().getStringExtra("name"),getIntent().getStringExtra("id"));
        SimpleDraweeView image=(SimpleDraweeView)findViewById(R.id.imageWin);
        final TextView name=(TextView)findViewById(R.id.friend_name);
        final TextView id=(TextView)findViewById(R.id.friend_id);
        TextView chat=(TextView)findViewById(R.id.imageButton);
        image.setImageURI(friend.getFriendImg());
        name.setText(friend.getFriendName());
        id.setText(friend.getId());
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FriendMessage.this,EditActivity.class);
                intent.putExtra("name",friend.getFriendName());
                intent.putExtra("id",friend.getId());
                startActivity(intent);
            }
        });
    }

}
