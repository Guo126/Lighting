package com.dianmo.flash.Fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dianmo.flash.Adapter.FriItemAdapter;
import com.dianmo.flash.Adapter.ItemAdapter;
import com.dianmo.flash.Entity.Item;
import com.dianmo.flash.Entity.user.BasMsg;
import com.dianmo.flash.Entity.user.UserInner;
import com.dianmo.flash.Entity.user.UserMsg;
import com.dianmo.flash.MainActivity;
import com.dianmo.flash.R;
import com.dianmo.flash.uitl.INetCallback;
import com.dianmo.flash.uitl.NetworkUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentD extends Fragment {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    public static final int TAKEPHOTO =1;
    private static final int GETIMG = 2;
    private TextView uName;
    private Uri imageUri;
    private Button take_photo;
    private SimpleDraweeView draweeView;
    private Button cancel;
    private ListView list;
    private UserInner userInner = new UserInner();
    private BasMsg basMsg = new BasMsg();
    private boolean isUp = false;
    String picturePath= null;
    Item[] items = new Item[4];
    public FragmentD() {
        // Required empty public constructor
        items[0] = new Item(R.drawable.user,"用户资料");
        items[1] = new Item(R.drawable.book,"订单信息");
        items[2] = new Item(R.drawable.sync,"版本更新");
        items[3] = new Item(R.drawable.warningcircle,"关于");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        uName = (TextView) getActivity().findViewById(R.id.uName);
        userInner = (UserInner) getActivity().getIntent().getSerializableExtra("userInner");
        uName.setText(userInner.getName());
        draweeView = (SimpleDraweeView) getActivity().findViewById(R.id.photo);
        if(userInner.getPhoto()!=null){
            Uri uri = Uri.parse(userInner.getPhoto());
            draweeView.setImageURI(uri);
        }
        draweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,GETIMG);
            }
        });

        list = (ListView) getActivity().findViewById(R.id.list);
        cancel = (Button) getActivity().findViewById(R.id.cancel) ;
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
        list.setAdapter(new ItemAdapter(getActivity(),Arrays.asList(items)));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKEPHOTO:
                if(resultCode ==RESULT_OK){
                    draweeView.setImageURI(imageUri);
                }
                break;
            case GETIMG:
                //打开相册并选择照片，这个方式选择单张
// 获取返回的数据，这里是android自定义的Uri地址
                if(data==null)
                    return;
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
// 获取选择照片的数据视图
                if(selectedImage!=null){
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
// 从数据视图中获取已选择图片的路径
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    picturePath = cursor.getString(columnIndex);
                    cursor.close();
// 将图片显示到界面上

                    NetworkUtil.upLoadImg("http://39.106.81.100:9999/firefly/user/upload",userInner.getId().toString(),picturePath, BasMsg.class, new INetCallback<BasMsg>() {
                        @Override
                        public void onSuccess(final BasMsg msg) {
                            basMsg = msg;
                            isUp = true;
                        }
                    });
                    if(isUp&&basMsg.getMsg()!=null){
                        draweeView.setImageURI(basMsg.getMsg());
                        isUp =false;
                    }
                }
                break;

                default:break;
        }
    }
}
