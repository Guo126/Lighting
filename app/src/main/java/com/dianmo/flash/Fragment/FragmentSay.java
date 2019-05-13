package com.dianmo.flash.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dianmo.flash.R;
import com.dianmo.view.AudioButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSay extends Fragment {

    private AudioButton audioButton;
    public FragmentSay() {
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
        audioButton = (AudioButton)getActivity().findViewById(R.id.recorder);

    }



}
