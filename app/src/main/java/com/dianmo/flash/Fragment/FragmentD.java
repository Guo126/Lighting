package com.dianmo.flash.Fragment;


import android.content.Intent;
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
import com.dianmo.flash.Adapter.FriItemAdapter;
import com.dianmo.flash.Adapter.ItemAdapter;
import com.dianmo.flash.Entity.Item;
import com.dianmo.flash.MainActivity;
import com.dianmo.flash.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentD extends Fragment {
    public static final int take =1;
    private static final int GETIMG = 2;
    private ImageView photo;
    private Uri imageUri;
    private Button take_photo;
    private Button cancel;
    private ListView list;
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
        photo = (ImageView) getActivity().findViewById(R.id.photo) ;
        photo.setOnClickListener(new View.OnClickListener() {
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
            case take:
                if(resultCode ==RESULT_OK){
                    try{
                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
                        photo.setImageBitmap(bitmap);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
            case GETIMG:
                //打开相册并选择照片，这个方式选择单张
// 获取返回的数据，这里是android自定义的Uri地址
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
// 获取选择照片的数据视图
                Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
// 从数据视图中获取已选择图片的路径
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
// 将图片显示到界面上

                photo.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                break;
                default:break;
        }
    }
}
