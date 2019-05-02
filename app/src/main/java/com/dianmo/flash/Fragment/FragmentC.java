package com.dianmo.flash.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.dianmo.flash.Entity.Item;
import com.dianmo.flash.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentC extends Fragment {
    public static final int take =1;

    private ListView list;
    Item [] items = new Item[4];
    public FragmentC() {
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
        return inflater.inflate(R.layout.fragment_find, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}
