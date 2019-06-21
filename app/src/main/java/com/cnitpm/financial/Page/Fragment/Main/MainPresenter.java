package com.cnitpm.financial.Page.Fragment.Main;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cnitpm.financial.Base.BaseActivity;
import com.cnitpm.financial.Base.BasePresenter;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cnitpm.financial.Base.BaseActivity;
import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Custom.SpringbackView;
import com.cnitpm.financial.Custom.WaveView;
import com.cnitpm.financial.Model.AllModel;
import com.cnitpm.financial.Model.NoteBook;
import com.cnitpm.financial.Model.TimeLine;
import com.cnitpm.financial.Page.Activity.Calendar.CalendarActivity;
import com.cnitpm.financial.R;
import com.cnitpm.financial.Util.SqlOperation;
import com.cnitpm.financial.Util.UtilRecyclerAdapter;
import com.cnitpm.financial.Util.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class MainPresenter extends BasePresenter<MainView> implements View.OnClickListener {
    private double budget=1000;//预算
    private  List<Animator> animators = new ArrayList<>();   //动画
    private  AnimatorSet mAnimatorSet=new AnimatorSet();  //集合动画组

    List<NoteBook> noteBooks;

    private int noteBookIndex=0;  //账本列表下标
    @Override
    public void init() {
        setNoteBookView();

        mvpView.getMain_Recycler_TimeLine().setLayoutManager(new LinearLayoutManager(mvpView.getActivityContext()));
        Refresh();
        //title 日期按钮
        mvpView.getMain_TextView_Date().setText(Utils.getFormat("MM/dd",new Date().getTime()));
        //搜索按钮
        mvpView.getMain_TextView_Search().setTypeface(Utils.getTypeFace(mvpView.getActivityContext()));
        mvpView.getMain_TextView_Search().setText("\uecb3");
        //日历按钮
        mvpView.getMain_TextView_Calendar().setTypeface(Utils.getTypeFace(mvpView.getActivityContext()));
        mvpView.getMain_TextView_Calendar().setText("\uea7a");
        //日常账本
        mvpView.getMain_TextView_NoteBook().setTypeface(Utils.getTypeFace(mvpView.getActivityContext()));

        mvpView.getMain_SpringbackView().setSpringBackViewEvent(new SpringbackView.SpringBackViewEvent() {
            @Override
            public void Translate(boolean b) {

                if(mvpView.getMain_LinearLayout_layouts().getTranslationY()!=0){
                    ObjectAnimator waveShiftAnim= ObjectAnimator.ofFloat(mvpView.getMain_LinearLayout_layouts()
                            , "translationY"
                            , Utils.dip2px(mvpView.getActivityContext(),120)
                            , 0);
                    waveShiftAnim.setDuration(500);
                    waveShiftAnim.start();
                }
            }
        });

        /**点击事件**/
        mvpView.getMain_TextView_Calendar().setOnClickListener(this);
        mvpView.getMain_TextView_NoteBook().setOnClickListener(this);
        mvpView.getMain_TextView_Search().setOnClickListener(this);
    }

    /**账本列表**/
    private void setNoteBookView(){
        List<AllModel> allModels=new ArrayList<>();
        noteBooks= new SqlOperation().SelectAll(NoteBook.class);  //获取全部账本
        /**预算**/
        budget=noteBooks.get(0).getBudget();
        /**这里账本id  和账本列表 id是不同的，因为可能会有删除账本 **/
        for(int i=0;i<noteBooks.size();i++){
            allModels.add(new AllModel(noteBooks.get(i),1));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mvpView.getActivityContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mvpView.getMain_RecyclerView_NoteBooks().setLayoutManager(linearLayoutManager);
        mvpView.getMain_RecyclerView_NoteBooks().setAdapter(new UtilRecyclerAdapter(mvpView.getActivityContext(),NoteBook.class,allModels,noteBookIndex));
        mvpView.getMain_TextView_NoteBook().setText(noteBooks.get(noteBookIndex).getNoteBookName());
        /**选择账本**/
        ((UtilRecyclerAdapter)mvpView.getMain_RecyclerView_NoteBooks().getAdapter()).setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ((UtilRecyclerAdapter)mvpView.getMain_RecyclerView_NoteBooks().getAdapter()).setObject(position);
                mvpView.getMain_RecyclerView_NoteBooks().getAdapter().notifyDataSetChanged();
                mvpView.setNoteBookId(noteBooks.get(position).getId());
                mvpView.getMain_TextView_NoteBook().setText(noteBooks.get(position).getNoteBookName());
                noteBookIndex=position;
                selectNoteBook();

            }
        });
    }

    private void selectNoteBook(){
        if(mvpView.getMain_LinearLayout_layouts().getTranslationY()!=0){
            ObjectAnimator waveShiftAnim= ObjectAnimator.ofFloat(mvpView.getMain_LinearLayout_layouts()
                    , "translationY"
                    , Utils.dip2px(mvpView.getActivityContext(),120)
                    , 0);
            waveShiftAnim.setDuration(500);
            waveShiftAnim.start();
        }

        Refresh();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Main_TextView_Calendar:
                Bundle bundle=new Bundle();
                bundle.putSerializable("key",noteBooks.get(noteBookIndex));
                ((BaseActivity)mvpView.getThisActivity()).JumpBundleActivity(mvpView.getActivityContext(), CalendarActivity.class,bundle);
                break;
            case R.id.Main_TextView_NoteBook:
                ObjectAnimator waveShiftAnim;
                if(mvpView.getMain_LinearLayout_layouts().getTranslationY()==0){
                    waveShiftAnim= ObjectAnimator.ofFloat(mvpView.getMain_LinearLayout_layouts()
                            , "translationY"
                            , 0
                            , Utils.dip2px(mvpView.getActivityContext(),120));

                }else {
                    waveShiftAnim= ObjectAnimator.ofFloat(mvpView.getMain_LinearLayout_layouts()
                            , "translationY"
                            , Utils.dip2px(mvpView.getActivityContext(),120)
                            , 0);
                }
                waveShiftAnim.setDuration(500);
                waveShiftAnim.start();
                break;
            case R.id.Main_TextView_Search:
                if(new NoteBook(2,"默认账本3", Utils.getFormat("YYYY-MM-dd",new Date().getTime()),1000).save()){
                    Toast.makeText(mvpView.getActivityContext(), "添加成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mvpView.getActivityContext(), "添加失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    /**更新 获取信息方法**/
    public void Refresh(){

        double Money_L=0,Money_R=0;   //本月的支出与收入
        double balance=noteBooks.get(noteBookIndex).getBudget();
        //时间轴要用
        List<AllModel> allModels=new ArrayList<>();
        //今天添加的
        List<TimeLine> timeLines=new SqlOperation().SelectWhere(TimeLine.class,"Time=? and NoteBook=?",Utils.getFormat("yyyy-MM-dd",new Date().getTime()),noteBooks.get(noteBookIndex).getId()+"");
        //时间轴添加
        for(TimeLine timeLine:timeLines){
            allModels.add(new AllModel(timeLine,1));

        }
        /**查询本月的支出与收入**/
        List<TimeLine> timeLines1=new SqlOperation().SelectWhere(TimeLine.class,"Time like ? and NoteBook=?",Utils.getFormat("yyyy-MM",new Date().getTime())+"%",noteBooks.get(noteBookIndex).getId()+"");
        for (int i=0;i<timeLines1.size();i++){
            TimeLine timeLine=timeLines1.get(i);
            if(timeLine.getDirection()){
                Money_L+=timeLine.getPrice();
            }else {
                Money_R+=timeLine.getPrice();

                balance= budget-Money_R;   //获取余额
            }
        }
        //收入与支出总和按钮
        mvpView.getMain_TextView_Money_L().setText("本月收入\n"+Money_L);
        mvpView.getMain_TextView_Money_R().setText("本月支出\n"+Money_R);
        mvpView.getMain_TextView_Budget().setText("剩 余\n"+balance);
        mvpView.getMain_Recycler_TimeLine().setAdapter(new UtilRecyclerAdapter(mvpView.getActivityContext(),TimeLine.class,allModels));
        /**添加空布局**/
        View view=  LayoutInflater.from(mvpView.getActivityContext()).inflate(R.layout.z_recycler_nodata_item1, null);
        ((UtilRecyclerAdapter)mvpView.getMain_Recycler_TimeLine().getAdapter()).setEmptyView(view);


        //控件波浪所占的多少
        if(Money_R>budget){
            setWavesView(0.1f);
        }else {
            setWavesView((1-((float)Money_R/(float)budget)));
        }

    }
    /**设置预算的高度 及动画**/
    private void setWavesView(float f){
        mAnimatorSet.end();  //关闭动画
        animators.clear();   //清除动画
        mvpView.getMain_Recycler_WavesView().setWaterLevelRatio(f);
        //设置波动速度
        ObjectAnimator waveShiftAnim = ObjectAnimator.ofFloat(mvpView.getMain_Recycler_WavesView(), "waveShiftRatio", 0f, 1f);
        waveShiftAnim.setRepeatCount(ValueAnimator.INFINITE);
        waveShiftAnim.setDuration(2000);
        waveShiftAnim.setInterpolator(new LinearInterpolator());
        animators.add(waveShiftAnim);
        //设置上涨速度
        ObjectAnimator waterLevelAnim = ObjectAnimator.ofFloat(mvpView.getMain_Recycler_WavesView(), "waterLevelRatio", 0f, f);
        waterLevelAnim.setDuration(5000);
        waterLevelAnim.setInterpolator(new DecelerateInterpolator());
        animators.add(waterLevelAnim);

        // 波动幅度
        // ObjectAnimator amplitudeAnim = ObjectAnimator.ofFloat(mWaveView, "amplitudeRatio", 0f, 0.05f);
        // amplitudeAnim.setRepeatCount(ValueAnimator.INFINITE);
        // amplitudeAnim.setRepeatMode(ValueAnimator.REVERSE);
        // amplitudeAnim.setDuration(2000);
        // amplitudeAnim.setInterpolator(new LinearInterpolator());
        // animators.add(amplitudeAnim);
        //运行速度
        mAnimatorSet.playTogether(animators);
        mAnimatorSet.start();
    }
}
