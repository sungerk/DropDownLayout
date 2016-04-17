package sunger.net.org.dropmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.net.sunger.widget.DropDownLayout;
import org.net.sunger.widget.MenuLayout;

import java.util.ArrayList;
import java.util.List;

import sunger.net.org.dropmenu.adapter.ShopAdapter;
import sunger.net.org.dropmenu.fragment.FragmentCategory;
import sunger.net.org.dropmenu.fragment.FragmentFloor;
import sunger.net.org.dropmenu.fragment.FragmentSort;
import sunger.net.org.dropmenu.tab.CommonTabLayout;
import sunger.net.org.dropmenu.tab.listener.CustomTabEntity;
import sunger.net.org.dropmenu.tab.listener.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
    private CommonTabLayout tabs;
    private String[] mTitles = {"All Floor", "All Merchant", "All Category"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_floor_unselected, R.mipmap.tab_category_unseleted,
            R.mipmap.tab_sort_unseleted};
    private int[] mIconSelectIds = {
            R.mipmap.tab_floor_selected, R.mipmap.tab_category_seleted,
            R.mipmap.tab_floor_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private DropDownLayout dropDownLayout;
    private MenuLayout menuLayout;

    private ShopAdapter shopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuLayout = (MenuLayout) findViewById(R.id.menuLayout);
        dropDownLayout = (DropDownLayout) findViewById(R.id.dropdown);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentFloor());
        fragments.add(new FragmentCategory());
        fragments.add(new FragmentSort());
//         menuLayout.setAnimationIn(R.anim.an);
//         menuLayout.setAnimationOut(R.anim.out);
        menuLayout.setFragmentManager(getSupportFragmentManager());
        menuLayout.setFragment(fragments, R.id.menuLayout);
        tabs = (CommonTabLayout) findViewById(R.id.tabs);
        updateTabData();


        tabs.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                dropDownLayout.showMenuAt(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (menuLayout.isShow()) {
                    dropDownLayout.closeMenu();
                } else {
                    dropDownLayout.showMenuAt(position);
                }

            }
        });

        shopAdapter = new ShopAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(shopAdapter);
    }

    private void updateTabData() {
        mTabEntities.clear();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabs.setTabData(mTabEntities);
    }

    public void onFilter(int type, String tag) {
        dropDownLayout.closeMenu();
        switch (type) {
            case 0:
                mTitles[0] = tag;
                break;
            case 1:
                mTitles[1] = tag;

                break;
            case 2:
                mTitles[2] = tag;
                break;
        }
        updateTabData();
    }


}
