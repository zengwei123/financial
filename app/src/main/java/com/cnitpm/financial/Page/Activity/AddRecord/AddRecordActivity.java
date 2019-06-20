package com.cnitpm.financial.Page.Activity.AddRecord;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cnitpm.financial.Base.MvpActivity;
import com.cnitpm.financial.Base.ViewBind;
import com.cnitpm.financial.R;
import com.cnitpm.financial.Util.Utils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * 添加页面
 */
public class AddRecordActivity extends MvpActivity<AddRecordPresenter> implements AddRecordView {
    @ViewBind(R.id.Include_Back)
    private ImageView Include_Back;   //返回
    @ViewBind(R.id.Include_Title)
    private TextView textView;     //标题

    @ViewBind(R.id.AddRecord_TextView_Sum)
    private TextView AddRecord_TextView_Sum; //计算器的和
    @ViewBind(R.id.AddRecord_TextView_Symbol)
    private TextView AddRecord_TextView_Symbol; //用来进行加减计算的
    @ViewBind(R.id.AddRecord_RecyclerView_Calculator)
    private RecyclerView AddRecord_RecyclerView_Calculator;   //计算器的算盘
    @ViewBind(R.id.AddRecord_TabLayout_LR)
    private TabLayout AddRecord_TabLayout_LR;  //切换 收入还是支出
    @ViewBind(R.id.AddRecord_ViewPager_LR)
    private ViewPager AddRecord_ViewPager_LR;
    @ViewBind(R.id.AddRecord_ImageView_Icon)
    private ImageView AddRecord_ImageView_Icon;   //选则的图标
    @ViewBind(R.id.AddRecord_TextView_Text)
    private TextView AddRecord_TextView_Text;   //选择的类型
    @ViewBind(R.id.AddRecord_TextView_MessageTime)
    private TextView AddRecord_TextView_MessageTime;  //选择日期
    @ViewBind(R.id.AddRecord_TextView_NoteBook)
    private TextView AddRecord_TextView_NoteBook;  //账本
    @ViewBind(R.id.AddRecord_TextView_AddMessage)
    private TextView AddRecord_TextView_AddMessage;  //添加内容按钮
    @ViewBind(R.id.AddRecord_RelativeLayout_layout)
    private RelativeLayout AddRecord_RelativeLayout_layout;
    @ViewBind(R.id.AddRecord_ImageView_Camera)
    private ImageView AddRecord_ImageView_Camera;   //选择图片
    @ViewBind(R.id.AddRecord_EditText_Message)
    private EditText AddRecord_EditText_Message;
    @ViewBind(R.id.AddRecord_TextView_Cancel)
    private TextView AddRecord_TextView_Cancel;  //取消按钮
    @ViewBind(R.id.AddRecord_TextView_Determine)
    private TextView AddRecord_TextView_Determine;  //确定
    @ViewBind(R.id.AddRecord_RelativeLayout_NoteBook)
    private RelativeLayout AddRecord_RelativeLayout_NoteBook;  //选择账本布局
    @ViewBind(R.id.AddRecord_RecyclerView_NoteBooks)
    private RecyclerView AddRecord_RecyclerView_NoteBooks;   //选择账本的列表

    private Bundle bundle;
    private String imageUrl=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecord_layout);
        bundle=getIntent().getBundleExtra("KEY");
        mvpPresenter.attachView(this);
        super.injectViews();
        mvpPresenter.init();
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public Activity getThisActivity() {
        return this;
    }
    @Override
    public ImageView getInclude_Back() {
        return Include_Back;
    }

    @Override
    public TextView getInclude_Title() {
        return textView;
    }

    @Override
    protected AddRecordPresenter createPresenter() {
        return new AddRecordPresenter();
    }

    @Override
    public RecyclerView getAddRecord_RecyclerView_Calculator() {
        return AddRecord_RecyclerView_Calculator;
    }

    @Override
    public TextView getAddRecord_TextView_Sum() {
        return AddRecord_TextView_Sum;
    }

    @Override
    public TextView getAddRecord_TextView_Symbol() {
        return AddRecord_TextView_Symbol;
    }

    @Override
    public TabLayout getAddRecord_TabLayout_LR() {
        return AddRecord_TabLayout_LR;
    }

    @Override
    public ViewPager getAddRecord_ViewPager_LR() {
        return AddRecord_ViewPager_LR;
    }

    @Override
    public ImageView getAddRecord_ImageView_Icon() {
        return AddRecord_ImageView_Icon;
    }

    @Override
    public TextView getAddRecord_TextView_Text() {
        return AddRecord_TextView_Text;
    }

    @Override
    public TextView getAddRecord_TextView_MessageTime() {
        return AddRecord_TextView_MessageTime;
    }

    @Override
    public TextView getAddRecord_TextView_AddMessage() {
        return AddRecord_TextView_AddMessage;
    }

    @Override
    public TextView getAddRecord_TextView_NoteBook() {
        return AddRecord_TextView_NoteBook;
    }

    @Override
    public RelativeLayout getAddRecord_RelativeLayout_layout() {
        return AddRecord_RelativeLayout_layout;
    }

    @Override
    public ImageView getAddRecord_ImageView_Camera() {
        return AddRecord_ImageView_Camera;
    }

    @Override
    public EditText getAddRecord_EditText_Message() {
        return AddRecord_EditText_Message;
    }

    @Override
    public TextView getAddRecord_TextView_Cancel() {
        return AddRecord_TextView_Cancel;
    }

    @Override
    public TextView getAddRecord_TextView_Determine() {
        return AddRecord_TextView_Determine;
    }

    @Override
    public RelativeLayout getAddRecord_RelativeLayout_NoteBook() {
        return AddRecord_RelativeLayout_NoteBook;
    }

    @Override
    public RecyclerView getAddRecord_RecyclerView_NoteBooks() {
        return AddRecord_RecyclerView_NoteBooks;
    }

    @Override
    public Bundle getBundle() {
        return bundle;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public void onBackPressed() {
        if(getAddRecord_RelativeLayout_layout().getVisibility()== View.VISIBLE){
            getAddRecord_RelativeLayout_layout().setVisibility(View.GONE);
        }else if(getAddRecord_RelativeLayout_NoteBook().getVisibility()== View.VISIBLE){
            getAddRecord_RelativeLayout_NoteBook().setVisibility(View.GONE);
        }else {
            super.onBackPressed();
        }
    }

    /**选择照片的回调**/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    if(selectList.size()>0){
                        LocalMedia media=selectList.get(0);
                        Glide.with(getActivityContext()).load(media.getPath()).into(getAddRecord_ImageView_Camera());
                        imageUrl=media.getPath();
                    }
                    break;
            }
        }
    }
}
