package sunger.net.org.dropmenu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;

import sunger.net.org.dropmenu.MainActivity;
import sunger.net.org.dropmenu.adapter.FilterAdapter;

/**
 * Created by sunger on 16/4/16.
 */
public class FragmentCategory extends Fragment {

    private String clothes[] = {"All", "Socks", "Hat", "Glove", "Briefs", "Jacket"};
    private ListView listView;
    private FilterAdapter adapter;
    private MainActivity mainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity=(MainActivity)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listView = new ListView(getActivity());
        return listView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView.setDividerHeight(0);
        adapter = new FilterAdapter(getActivity(), Arrays.asList(clothes));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mainActivity.onFilter(1,clothes[position]);
                adapter.setCheckItem(position);
            }
        });
    }
}
