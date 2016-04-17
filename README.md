# DropDownLayout
effect
![](https://github.com/sungerk/DropDownLayout/blob/master/art/shutcut.gif)
##How to use?
###1.config the layout like this
* DropDownLayout
 the container who can controller the menu sho or dissmiss

* RelativeLayout
 you custom  contentView,you can use RelativeLayout,LinearLayout or other layout view.

* MaskView
  when menu is show the mask will show,and when the menu dismiss the maskview will dismiss
  ## you must set the alpha. so i set the background color with alpha #88838685

* MenuLayout
the  menu layout

###Like this
```xml
<org.net.sunger.widget.DropDownLayout xmlns:android="http://schemas.android.com/apk/res/android"
     xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/dropdown"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
     tools:showIn="@layout/activity_main">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#fff"
        >

        <android.support.v7.widget.RecyclerView
            android:id="@+id/recyclerView"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
             ></android.support.v7.widget.RecyclerView>
    </RelativeLayout>

     <org.net.sunger.widget.MaskView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#88838685" />


    <org.net.sunger.widget.MenuLayout
        android:id="@+id/menuLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#fff"></org.net.sunger.widget.MenuLayout>


</org.net.sunger.widget.DropDownLayout>

```


###2.controller it in java code

```java
        MenuLayout  menuLayout = (MenuLayout) findViewById(R.id.menuLayout);
        DropDownLayout dropDownLayout = (DropDownLayout) findViewById(R.id.dropdown);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentFloor());
        fragments.add(new FragmentCategory());
        fragments.add(new FragmentSort());
        //you can set the anim by youself
        //menuLayout.setAnimationIn(R.anim.an);
        //menuLayout.setAnimationOut(R.anim.out);
        menuLayout.setFragmentManager(getSupportFragmentManager());
        menuLayout.setFragment(fragments, R.id.menuLayout);
        // dropDownLayout.closeMenu();
        //  dropDownLayout.showMenuAt(position);
```



## License

```
Copyright 2016 Hannes sungerk

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
