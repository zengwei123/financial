package com.cnitpm.financial.Page.Fragment.Main;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
        final List<AllModel> allModels=new ArrayList<>();
        noteBooks= new SqlOperation().SelectAll(NoteBook.class);  //获取全部账本
        /**获得预算**/
        budget=noteBooks.get(0).getBudget();
        /**这里账本id  和账本列表 id是不同的，因为可能会有删除账本 **/
        for(int i=0;i<noteBooks.size();i++){
            allModels.add(new AllModel(noteBooks.get(i),1));
        }
        allModels.add(new AllModel(noteBooks.get(0),2));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mvpView.getActivityContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mvpView.getMain_RecyclerView_NoteBooks().setLayoutManager(linearLayoutManager);
        mvpView.getMain_RecyclerView_NoteBooks().setAdapter(new UtilRecyclerAdapter(mvpView.getActivityContext(),NoteBook.class,allModels,noteBookIndex));
        mvpView.getMain_TextView_NoteBook().setText(noteBooks.get(noteBookIndex).getNoteBookName());
        /**选择账本**/
        ((UtilRecyclerAdapter)mvpView.getMain_RecyclerView_NoteBooks().getAdapter()).setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(position==allModels.size()-1){
                    /**添加账本**/
                    addNoteBook("新建账本",true,allModels,0);
                }else {
                    ((UtilRecyclerAdapter)mvpView.getMain_RecyclerView_NoteBooks().getAdapter()).setObject(position);
                    mvpView.getMain_RecyclerView_NoteBooks().getAdapter().notifyDataSetChanged();
                    mvpView.setNoteBookId(noteBooks.get(position).getId());
                    mvpView.getMain_TextView_NoteBook().setText(noteBooks.get(position).getNoteBookName());
                    noteBookIndex=position;
                    selectNoteBook();
                }
            }
        });
        ((UtilRecyclerAdapter)mvpView.getMain_RecyclerView_NoteBooks().getAdapter()).setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                if(position!=allModels.size()-1){
                    addNoteBook("修改账本",false,allModels,position);
                }
                return false;
            }
        });
    }



    /**选择账本 名字好想起错了**/
    private void selectNoteBook(){
        if(mvpView.getMain_LinearLayout_layouts().getTranslationY()!=0){
            try {
                ObjectAnimator waveShiftAnim= ObjectAnimator.ofFloat(mvpView.getMain_LinearLayout_layouts()
                        , "translationY"
                        , Utils.dip2px(mvpView.getActivityContext(),120)
                        , 0);
                waveShiftAnim.setDuration(500);
                waveShiftAnim.start();
            }catch (Exception e){
                e.printStackTrace();
            }

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
//                Snackbar snackbar=Snackbar.make(mvpView.getThisActivity().findViewById(android.R.id.content),"是否确认删除此账本？", Snackbar.LENGTH_LONG).setAction("删除", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(mvpView.getActivityContext(),"你点击了action",Toast.LENGTH_SHORT).show();
//                    }
//                });
//                View mView = snackbar.getView();
//                mView.setBackgroundColor(Color.parseColor("#DB9019"));
//                snackbar.setActionTextColor(Color.BLACK);
//                snackbar.show();
                break;
        }
    }


    /**更新 获取信息方法**/
    public void Refresh(){
        double Money_L=0,Money_R=0;   //本月的支出与收入
        /**获得预算**/
        budget=noteBooks.get(noteBookIndex).getBudget();
        double balance=noteBooks.get(noteBookIndex).getBudget();
        if(balance==0){
            mvpView.getMain_Recycler_WavesView().setVisibility(View.INVISIBLE);
            mvpView.getMain_TextView_Budget().setVisibility(View.INVISIBLE);
        }else {
            mvpView.getMain_TextView_Budget().setVisibility(View.VISIBLE);
            mvpView.getMain_Recycler_WavesView().setVisibility(View.VISIBLE);
        }
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
        FrameLayout.LayoutParams layoutParams= (FrameLayout.LayoutParams) mvpView.getMain_Recycler_TimeLine().getLayoutParams();
        /**当没有数据的时候没有数据提示的居中显示 有数据则置顶显示**/
        if(allModels.size()!=0){
            layoutParams.gravity= Gravity.TOP;
            mvpView.getMain_SpringbackView().setHua(true);  //允许滑动
            mvpView.getMain_Recycler_TimeLine().setLayoutParams(layoutParams);
        }else {
            layoutParams.gravity= Gravity.CENTER;
            mvpView.getMain_SpringbackView().setHua(false);
            mvpView.getMain_Recycler_TimeLine().setLayoutParams(layoutParams);
        }

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
    /**添加账本的方法**/
    private void addNoteBook(final String titleStr, final boolean isUpdate, final List<AllModel> allModels, final int id){
        /**创建dialog**/
        AlertDialog.Builder builder = new AlertDialog.Builder(mvpView.getActivityContext());
        final AlertDialog dialog = builder.create();
        /**dialogd的布局**/
        View dialogView = View.inflate(mvpView.getActivityContext(), R.layout.z_dialog_notebook_layout, null);
        TextView title=dialogView.findViewById(R.id.Z_Dialog_NoteBook_Title);  //标题
        final EditText editText=dialogView.findViewById(R.id.Z_Dialog_NoteBook_EditText);  //内容
        final EditText editText1=dialogView.findViewById(R.id.Z_Dialog_NoteBook_EditText1);  //预算
        ImageView imageView=dialogView.findViewById(R.id.Z_Dialog_NoteBook_Delete);
        /**一些设置**/
        if(isUpdate){
            editText1.setText("1000");
            editText.requestFocus();
            imageView.setVisibility(View.GONE);
        }else {
            editText.setText(((NoteBook)allModels.get(id).getData()).getNoteBookName()+"");
            editText1.setText(((NoteBook)allModels.get(id).getData()).getBudget()+"");
            editText.requestFocus();
            /**删除按钮**/
            imageView.setVisibility(View.VISIBLE);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                    Snackbar snackbar=Snackbar.make(mvpView.getThisActivity().findViewById(R.id.Sum_ViewPager_Page),"是否确认删除此账本？", Snackbar.LENGTH_LONG)
                            .setAction("删除", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mvpView.getActivityContext(),"你点击了action",Toast.LENGTH_SHORT).show();
                        }
                    }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                                @Override
                                public void onDismissed(Snackbar transientBottomBar, int event) {
                                    super.onDismissed(transientBottomBar, event);
                                    if(event==2||event==0){
                                        dialog.show();
                                    }else {
                                        NoteBook noteBook=((NoteBook)allModels.get(id).getData());
                                        new SqlOperation().DeleteSql(NoteBook.class,noteBook.getId());
                                        setNoteBookView();
                                    }
                                }
                            });
                    View mView = snackbar.getView();
                    mView.setBackgroundColor(Color.parseColor("#DB9019"));
                    snackbar.setActionTextColor(Color.BLACK);
                    snackbar.show();
                }
            });
        }
        title.setText(titleStr);

        /**确认按钮**/
        TextView Y=dialogView.findViewById(R.id.Z_Dialog_NoteBook_Y);  //确认
        Y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=editText.getText().toString().trim();   //账本名
                String str1=editText1.getText().toString().trim();  //账本的预算
                if(str1.equals("")){   //  如果没有输入内容
                    str1="0";   //值为0
                }
                //账本名不允许为空
                if(!str.equals("")){
                    boolean b;
                    //添加或者修改
                    if(isUpdate){
                        NoteBook noteBook=new NoteBook(str, Utils.getFormat("yyyy-MM-dd",new Date().getTime()),Double.parseDouble(str1));
                        b=noteBook.save();
                    }else {
                        //用来获取数据库中的id
                        NoteBook noteBook=((NoteBook)allModels.get(id).getData());
                        ContentValues contentValues=new ContentValues();
                        contentValues.put("NoteBookName",str);
                        contentValues.put("Budget",Double.parseDouble(str1));
                        /**修改内容**/
                        b=new SqlOperation().UpdateSql(NoteBook.class,contentValues,noteBook.getId());
                    }
                    if(b){
                        /**更新账本信息**/
                        //刷新账本列表
                        setNoteBookView();
                        mvpView.getMain_RecyclerView_NoteBooks().getAdapter().notifyDataSetChanged();
                        mvpView.setNoteBookId(noteBooks.get(id).getId());
                        mvpView.getMain_TextView_NoteBook().setText(noteBooks.get(id).getNoteBookName());
                        noteBookIndex=id;
                        dialog.cancel();
                        selectNoteBook();
                        Toast.makeText(mvpView.getActivityContext(),"操作成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(mvpView.getActivityContext(),"失败，可能是账本名重复",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(mvpView.getActivityContext(),"账本名不能为空",Toast.LENGTH_SHORT).show();

                }
            }
        });
        TextView N=dialogView.findViewById(R.id.Z_Dialog_NoteBook_N);  //取消
        N.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

         //添加自定义布局
        dialog.setView(dialogView);
        dialog.show();
        //设置宽度
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) Utils.getWindow(true,mvpView.getThisActivity())-100;
        dialog.getWindow().setAttributes(params);

    }
}
