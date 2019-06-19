package com.cnitpm.financial.Page.Activity.Sum;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.cnitpm.financial.Base.BaseActivity;
import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Base.MvpFragment;
import com.cnitpm.financial.Page.Activity.AddRecord.AddRecordActivity;
import com.cnitpm.financial.Page.Fragment.Main.MainFragment;
import com.cnitpm.financial.R;

import java.util.ArrayList;
import java.util.List;

class SumPresenter extends BasePresenter<SumView> {
    @Override
    public void init() {
        mvpView.getSum_TabLayout_Menu().addTab(mvpView.getSum_TabLayout_Menu().newTab().setIcon(R.mipmap.menu_chart_g));
        mvpView.getSum_TabLayout_Menu().addTab(mvpView.getSum_TabLayout_Menu().newTab().setIcon(R.mipmap.menu_add_k));
        mvpView.getSum_TabLayout_Menu().addTab(mvpView.getSum_TabLayout_Menu().newTab().setIcon(R.mipmap.menu_book_g));
        /**关闭背景水波纹**/
        mvpView.getSum_TabLayout_Menu().setTabRippleColor(null);
        /**设置默认选项**/
        mvpView.getSum_TabLayout_Menu().getTabAt(1).select();
        /**切换默认选择的图片**/
        mvpView.getSum_TabLayout_Menu().addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0: tab.setIcon(R.mipmap.menu_chart_k); break;
                    case 1: tab.setIcon(R.mipmap.menu_add_k); break;
                    case 2: tab.setIcon(R.mipmap.menu_book_k); break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0: tab.setIcon(R.mipmap.menu_chart_g); break;
                    case 1: tab.setIcon(R.mipmap.menu_add_g); break;
                    case 2: tab.setIcon(R.mipmap.menu_book_g); break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if( mvpView.getSum_TabLayout_Menu().getSelectedTabPosition()==1){
                    //添加按钮
                    ((BaseActivity)mvpView.getThisActivity()).JumpActivity(mvpView.getActivityContext(), AddRecordActivity.class);
                }
            }
        });
        /**viewpage显示的布局**/
        List<MvpFragment> fragments=new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new MainFragment());
        fragments.add(new MainFragment());
        /**viewpage设置adapter**/
        SumFragmentAdapter sumFragmentAdapter=new SumFragmentAdapter(((FragmentActivity)mvpView.getThisActivity()).getSupportFragmentManager(), fragments);
        mvpView.getSum_ViewPager_Page().setAdapter(sumFragmentAdapter);
        mvpView.getSum_ViewPager_Page().setCurrentItem(1);
        /**两个控件绑定起来**/
        mvpView.getSum_TabLayout_Menu().setupWithViewPager(mvpView.getSum_ViewPager_Page());
        /**设置图标  绑定后会清除 妈的**/
        mvpView.getSum_TabLayout_Menu().getTabAt(0).setIcon(R.mipmap.menu_chart_g);
        mvpView.getSum_TabLayout_Menu().getTabAt(1).setIcon(R.mipmap.menu_add_k);
        mvpView.getSum_TabLayout_Menu().getTabAt(2).setIcon(R.mipmap.menu_book_g);
    }
}
