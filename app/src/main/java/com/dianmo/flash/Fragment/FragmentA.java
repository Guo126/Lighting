package com.dianmo.flash.Fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.dianmo.flash.R;
import com.dianmo.flash.SelectActivity;
import com.dianmo.flash.ShowActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment {
    private Button check;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Bundle bd = new Bundle();
    private TextView start , end;
    private ImageView turn;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sp = this.getActivity().getSharedPreferences("cityInfo",MODE_PRIVATE);
        editor =sp.edit();
        start = (TextView) getActivity().findViewById(R.id.start_input);
        turn = (ImageView) getActivity().findViewById(R.id.turn);
        end = (TextView) getActivity().findViewById(R.id.end_input);
        check = (Button) getActivity().findViewById(R.id.check_btn);

        start.setText(sp.getString("start","出发地"));
        end.setText(sp.getString("end","目的地"));
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SelectActivity.class);
                bd.putInt("key",100);
                intent.putExtras(bd);
                startActivity(intent);
                getActivity().finish();
            }
        });

        turn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s1 = start.getText().toString();
                String s2 = end.getText().toString();
                editor.putString("start",s2);
                editor.putString("end",s1);
                editor.apply();
                start.setText(s2);
                end.setText(s1);
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SelectActivity.class);
                bd.putInt("key",101);
                intent.putExtras(bd);
                startActivity(intent);
                getActivity().finish();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = start.getText().toString();
                String s2 = end.getText().toString();
                if(s1.equals(s2)){
                    Toast.makeText(getActivity(),"出发地和目的地不得相同",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getActivity(),ShowActivity.class);
                    bd.putString("start",s1);
                    bd.putString("end",s2);
                    intent.putExtras(bd);
                    startActivity(intent);
                    getActivity().finish();
                }

            }
        });
    }


    public FragmentA() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

}
