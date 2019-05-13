package com.dianmo.flash.Fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dianmo.flash.Adapter.ItemAdapter;
import com.dianmo.flash.Entity.Item;
import com.dianmo.flash.Entity.user.BasMsg;
import com.dianmo.flash.Entity.user.UserInner;
import com.dianmo.flash.MainActivity;
import com.dianmo.flash.R;
import com.dianmo.flash.uitl.INetCallback;
import com.dianmo.flash.uitl.NetworkUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Arrays;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEdit extends Fragment {
    private ImageView oprate,more;
    private EditText editText;
    public FragmentEdit() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_edit, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        editText = (EditText)getActivity().findViewById(R.id.edit);
        oprate = (ImageView)getActivity().findViewById(R.id.oprate);
        more = (ImageView)getActivity().findViewById(R.id.more);
    }



}
