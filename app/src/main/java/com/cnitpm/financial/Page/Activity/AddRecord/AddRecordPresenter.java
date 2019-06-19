package com.cnitpm.financial.Page.Activity.AddRecord;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Base.MvpFragment;
import com.cnitpm.financial.Model.AllModel;
import com.cnitpm.financial.Model.TimeLine;
import com.cnitpm.financial.Page.Activity.AddRecord.ViewPage.ARViewPageFragment;
import com.cnitpm.financial.Page.Fragment.Main.MainFragment;
import com.cnitpm.financial.R;
import com.cnitpm.financial.Util.UtilRecyclerAdapter;
import com.cnitpm.financial.Util.Utils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 添加界面
 */
public class AddRecordPresenter extends BasePresenter<AddRecordView> implements View.OnClickListener {
    private static StringBuilder stringBuilder1=new StringBuilder();   //第一行数值
    private static boolean isDian=true;   //用来标识第一行有没有小数点
    private static int Jia_Jian=0;
    private static StringBuilder stringBuilder2=new StringBuilder();   //第一行数值
    private static boolean isDian1=true;   //用来标识第一行有没有小数点
    private int index=0;
    private boolean LR=false;

    private String setChooseTime=null;
    @Override
    public void init() {
        setChooseTime=Utils.getFormat("yyyy-MM-dd",new Date().getTime());
        //标题
        mvpView.getInclude_Title().setText("记一笔");
        //当前的数值
        mvpView.getAddRecord_TextView_Sum().setText("0");
        //默认的选择类型
        Glide.with(mvpView.getActivityContext()).load(Utils.RightIcon[index]).into(mvpView.getAddRecord_ImageView_Icon());
        mvpView.getAddRecord_TextView_Text().setText(Utils.RightClass[index]);
        //设置日期
        mvpView.getAddRecord_TextView_MessageTime().setText(setChooseTime);
        mvpView.getAddRecord_TextView_NoteBook().setText("默认账本");
        mvpView.getAddRecord_TextView_AddMessage().setTypeface(Utils.getTypeFace(mvpView.getActivityContext()));
        mvpView.getAddRecord_TextView_AddMessage().setText("\ue9c6");

        //计算器键盘
        List<AllModel> allModels=new ArrayList<>();
        allModels.add(new AllModel("7",1));
        allModels.add(new AllModel("8",1));
        allModels.add(new AllModel("9",1));
        allModels.add(new AllModel("BACK",1));
        allModels.add(new AllModel("4",1));
        allModels.add(new AllModel("5",1));
        allModels.add(new AllModel("6",1));
        allModels.add(new AllModel("+",1));
        allModels.add(new AllModel("1",1));
        allModels.add(new AllModel("2",1));
        allModels.add(new AllModel("3",1));
        allModels.add(new AllModel("-",1));
        allModels.add(new AllModel("C",1));
        allModels.add(new AllModel("0",1));
        allModels.add(new AllModel(".",1));
        allModels.add(new AllModel("确定",1));
        //tablayout
        mvpView.getAddRecord_TabLayout_LR().addTab(mvpView.getAddRecord_TabLayout_LR().newTab().setText("支出"));
        mvpView.getAddRecord_TabLayout_LR().addTab(mvpView.getAddRecord_TabLayout_LR().newTab().setText("收入"));
        setCalculatorRecycler(allModels);
        //添加viewpage界面
        List<MvpFragment> fragments=new ArrayList<>();
        ARViewPageFragment arViewPageFragment=new ARViewPageFragment();
        arViewPageFragment.setRL(true);
        arViewPageFragment.setArViewPageInterface(new ARViewPageFragment.ARViewPageInterface() {
            @Override
            public void ItemClick(int pos) {
                //回调接口  设置选择的类型
                index=pos;
                LR=false;
                Glide.with(mvpView.getActivityContext()).load(Utils.RightIcon[pos]).into(mvpView.getAddRecord_ImageView_Icon());
                mvpView.getAddRecord_TextView_Text().setText(Utils.RightClass[pos]);
            }
        });
        fragments.add(arViewPageFragment);
        ARViewPageFragment arViewPageFragment1=new ARViewPageFragment();
        arViewPageFragment1.setRL(false);
        fragments.add(arViewPageFragment1);
        arViewPageFragment1.setArViewPageInterface(new ARViewPageFragment.ARViewPageInterface() {
            @Override
            public void ItemClick(int pos) {
                index=pos;
                LR=true;
                Glide.with(mvpView.getActivityContext()).load(Utils.LeftIcon[pos]).into(mvpView.getAddRecord_ImageView_Icon());
                mvpView.getAddRecord_TextView_Text().setText(Utils.LeftClass[pos]);
            }
        });

        //Tab 与viewpage 联动
        mvpView.getAddRecord_ViewPager_LR().setAdapter(new AddRecordFragmentAdapter(((FragmentActivity)mvpView.getThisActivity()).getSupportFragmentManager(),fragments));
        mvpView.getAddRecord_TabLayout_LR().setupWithViewPager(mvpView.getAddRecord_ViewPager_LR());
        //需要重写设置一下
        mvpView.getAddRecord_TabLayout_LR().getTabAt(0).setText("支出");
        mvpView.getAddRecord_TabLayout_LR().getTabAt(1).setText("收入");
        setClick();
    }
    /**计算器键盘 的recycler**/
    private void setCalculatorRecycler(List<AllModel> allModels){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mvpView.getActivityContext(), 4);
        //设置表格，根据position计算在该position处1列占几格数据
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override public int getSpanSize(int position) {
                return 4;
            }
        });
        mvpView.getAddRecord_RecyclerView_Calculator().setLayoutManager(gridLayoutManager);
        mvpView.getAddRecord_RecyclerView_Calculator().setAdapter(new UtilRecyclerAdapter(mvpView.getActivityContext(),String.class,allModels));
        ((UtilRecyclerAdapter)mvpView.getAddRecord_RecyclerView_Calculator().getAdapter()).setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        if (Jia_Jian==0){
                            getSum(7);
                        }else {
                            getyunsuan(7);
                        }
                        break;
                    case 1:
                        if (Jia_Jian==0){
                            getSum(8);
                        }else {
                            getyunsuan(8);
                        }
                        break;
                    case 2:
                        if (Jia_Jian==0){
                            getSum(9);
                        }else {
                            getyunsuan(9);
                        }
                        break;
                    case 4:
                        if (Jia_Jian==0){
                            getSum(4);
                        }else {
                            getyunsuan(4);
                        }
                        break;
                    case 5:
                        if (Jia_Jian==0){
                            getSum(5);
                        }else {
                            getyunsuan(5);
                        }
                        break;
                    case 6:
                        if (Jia_Jian==0){
                            getSum(6);
                        }else {
                            getyunsuan(6);
                        }
                        break;
                    case 8:
                        if (Jia_Jian==0){
                            getSum(1);
                        }else {
                            getyunsuan(1);
                        }
                        break;
                    case 9:
                        if (Jia_Jian==0){
                            getSum(2);
                        }else {
                            getyunsuan(2);
                        }
                        break;
                    case 10:
                        if (Jia_Jian==0){
                            getSum(3);
                        }else {
                            getyunsuan(3);
                        }
                        break;
                    case 13:
                        if (Jia_Jian==0){
                            getSum(0);
                        }else {
                            getyunsuan(0);
                        }
                        break;
                    case 3:
                        /**返回**/
                        if(Jia_Jian==0){
                            if(stringBuilder1.length()!=0){  /**不为0的时候 删除上一个字段**/
                                stringBuilder1.deleteCharAt(stringBuilder1.length()-1);
                                mvpView.getAddRecord_TextView_Sum().setText(stringBuilder1.toString());
                                if(stringBuilder1.length()==0){
                                    mvpView.getAddRecord_TextView_Sum().setText("0");
                                }
                            }
                            /**每次都判断一下是否删除了小数点**/
                            if(!stringBuilder1.toString().contains(".")){
                                isDian=true;  /**如果删除了 那么就可以继续添加小数点**/
                            }
                        }else {
                            if(stringBuilder2.length()!=0){  /**不为0的时候 删除上一个字段**/
                                stringBuilder2.deleteCharAt(stringBuilder2.length()-1);
                                if(Jia_Jian==1){
                                    mvpView.getAddRecord_TextView_Symbol().setText("+"+stringBuilder2.toString());
                                }else {
                                    mvpView.getAddRecord_TextView_Symbol().setText("-"+stringBuilder2.toString());
                                }
                                if(stringBuilder2.length()==0){ /**当全部后退完后 还就是不进行运算了  继续第一个数字的运算**/
                                    Jia_Jian=0;
                                    mvpView.getAddRecord_TextView_Symbol().setVisibility(View.GONE);
                                    /**恢复确定按钮**/
                                    TextView textView=mvpView.getAddRecord_RecyclerView_Calculator().getLayoutManager().findViewByPosition(15).findViewById(R.id.AddRecord_RecyclerView_Calculator_Item_Text);
                                    textView.setText("确定");
                                }
                            }
                            /**每次都判断一下是否删除了小数点**/
                            if(!stringBuilder2.toString().contains(".")){
                                isDian1=true;  /**如果删除了 那么就可以继续添加小数点**/
                            }
                        }

                        break;
                    case 7:
                        if(Jia_Jian==0){  /**没有运算的时候才能进行运算**/
                            Jia_Jian=1; //加运算
                            /**显示第二个TextView**/
                            mvpView.getAddRecord_TextView_Symbol().setVisibility(View.VISIBLE);
                            /**显示第二个显示为加法**/
                            mvpView.getAddRecord_TextView_Symbol().setText("+");
                            /**恢复确定按钮**/
                            TextView textView=mvpView.getAddRecord_RecyclerView_Calculator().getLayoutManager().findViewByPosition(15).findViewById(R.id.AddRecord_RecyclerView_Calculator_Item_Text);
                            textView.setText("=");
                        }
                        break;
                    case 11:
                        if(Jia_Jian==0){
                            Jia_Jian=2; //减运算
                            /**显示第二个TextView**/
                            mvpView.getAddRecord_TextView_Symbol().setVisibility(View.VISIBLE);
                            /**显示第二个显示为减法**/
                            mvpView.getAddRecord_TextView_Symbol().setText("-");
                            TextView textView=mvpView.getAddRecord_RecyclerView_Calculator().getLayoutManager().findViewByPosition(15).findViewById(R.id.AddRecord_RecyclerView_Calculator_Item_Text);
                            textView.setText("=");
                        }
                        break;
                    case 12:
                        /**清空**/
                        stringBuilder1.setLength(0);
                        stringBuilder2.setLength(0);
                        /**小数点也有去掉 设置为没有小数点**/
                        isDian=true;
                        isDian1=true;
                        /**显示为0   第二个设置为空 并且隐藏**/
                        mvpView.getAddRecord_TextView_Sum().setText("0");
                        mvpView.getAddRecord_TextView_Symbol().setText("");
                        mvpView.getAddRecord_TextView_Symbol().setVisibility(View.GONE);
                        Jia_Jian=0;   //清除运算
                        TextView textView=mvpView.getAddRecord_RecyclerView_Calculator().getLayoutManager().findViewByPosition(15).findViewById(R.id.AddRecord_RecyclerView_Calculator_Item_Text);
                        textView.setText("确定");
                        break;
                    case 14:
                        if(Jia_Jian==0){
                            if(!stringBuilder1.toString().contains(".")){   /**判断是否已经添加了小数点**/
                                if(stringBuilder1.length()==0){
                                    stringBuilder1.append("0."); /**当没有数字的时候按了小数点**/
                                }else {
                                    stringBuilder1.append(".");
                                }
                                isDian=false;   /**已经添加小数点**/
                            }
                        }else {
                            if(!stringBuilder2.toString().contains(".")){   /**判断是否已经添加了小数点**/
                                if(stringBuilder2.length()==0){
                                    stringBuilder2.append("0."); /**当没有数字的时候按了小数点**/
                                }else {
                                    stringBuilder2.append(".");
                                }
                                isDian1=false;   /**已经添加小数点**/
                            }
                        }

                        break;
                    case 15:
                        if(Jia_Jian!=0){
                            double str1=0;   //应该会有没有输入的情况  所以默认为0
                            if(!stringBuilder1.toString().equals("")){
                                str1=Double.parseDouble(stringBuilder1.toString());
                            }
                            double str2=0;
                            if(!stringBuilder2.toString().equals("")){
                                str2=Double.parseDouble(stringBuilder2.toString());
                            }
                            /**获取值完毕  清空  小数点也是设置为没有**/
                            stringBuilder1.setLength(0);
                            stringBuilder2.setLength(0);
                            isDian=true;
                            isDian1=true;
                            //会出现很多位数小数  这里可与解决
                            BigDecimal b1;
                            BigDecimal b2;
                            if(Jia_Jian==1){
                                b1 = new BigDecimal(Double.toString(str1));
                                b2 = new BigDecimal(Double.toString(str2));
                                stringBuilder1.append(b1.add(b2).doubleValue());   //加法
                            }else if(Jia_Jian==2){
                                b1 = new BigDecimal(Double.toString(str1));
                                b2 = new BigDecimal(Double.toString(str2));
                                stringBuilder1.append(b1.subtract(b2).doubleValue());  //减法
                            }
                            //运算完了  清除运算符号
                            Jia_Jian=0;
                            mvpView.getAddRecord_TextView_Sum().setText(stringBuilder1.toString());
                            mvpView.getAddRecord_TextView_Symbol().setText("");
                            mvpView.getAddRecord_TextView_Symbol().setVisibility(View.GONE);
                            TextView textView1=mvpView.getAddRecord_RecyclerView_Calculator().getLayoutManager().findViewByPosition(15).findViewById(R.id.AddRecord_RecyclerView_Calculator_Item_Text);
                            textView1.setText("确定");
                        }else {
                            /**添加方法**/
                            AddRecord();
                        }

                        break;
                }
            }
        });
    }
    /**计算机获取的数字**/
    private void getSum(int i){
        if(stringBuilder1.length()!=0){   /**首先判断是否为0**/
            if(stringBuilder1.toString().contains(".")){   /**判断字符串中是否有.  有小数点那么就判断小数点后面的长度  长度不能超过2**/
                try {
                    if(stringBuilder1.toString().split("\\.")[1].length()!=2){   /**判断长度不能为2**/
                        stringBuilder1.append(i);
                        mvpView.getAddRecord_TextView_Sum().setText(stringBuilder1.toString());
                    }
                }catch (Exception e){   /**当我们小数点后面没有数字的时候 去判断长度  会出现一个空指针错误  我们处理他**/
                    stringBuilder1.append(i);
                    mvpView.getAddRecord_TextView_Sum().setText(stringBuilder1.toString());
                }
            }else {
                if(stringBuilder1.toString().split("\\.")[0].length()!=6){   /**长度不能够超过8  超过8就不给添加了**/
                    stringBuilder1.append(i);
                    mvpView.getAddRecord_TextView_Sum().setText(stringBuilder1.toString());
                }
            }
        }else {    /**长度为0的时候 说明还没有填入数字**/
            if(i!=0){   /**这个时候就添加数字  但是如果添加为0 那么就没有意义 所以长度为0的时候传入的数字不能为0**/
                stringBuilder1.append(i);
                mvpView.getAddRecord_TextView_Sum().setText(stringBuilder1.toString());
            }
        }
    }
    /**运算的数字**/
    private void getyunsuan(int i){
        if(stringBuilder2.length()!=0){   /**首先判断是否为0**/
            if(stringBuilder2.toString().contains(".")){   /**判断字符串中是否有.  有小数点那么就判断小数点后面的长度  长度不能超过2**/
                try {
                    if(stringBuilder2.toString().split("\\.")[1].length()!=2){   /**判断长度不能为2**/
                        stringBuilder2.append(i);
                        if(Jia_Jian==1){
                            mvpView.getAddRecord_TextView_Symbol().setText("+ "+stringBuilder2.toString());
                        }else if (Jia_Jian==2){
                            mvpView.getAddRecord_TextView_Symbol().setText("- "+stringBuilder2.toString());
                        }
                    }
                }catch (Exception e){   /**当我们小数点后面没有数字的时候 去判断长度  会出现一个空指针错误  我们处理他**/
                    stringBuilder2.append(i);
                    if(Jia_Jian==1){
                        mvpView.getAddRecord_TextView_Symbol().setText("+ "+stringBuilder2.toString());
                    }else if (Jia_Jian==2){
                        mvpView.getAddRecord_TextView_Symbol().setText("- "+stringBuilder2.toString());
                    }
                }
            }else {
                if(stringBuilder2.toString().split("\\.")[0].length()!=6){   /**长度不能够超过8  超过8就不给添加了**/
                    stringBuilder2.append(i);
                    if(Jia_Jian==1){
                        mvpView.getAddRecord_TextView_Symbol().setText("+ "+stringBuilder2.toString());
                    }else if (Jia_Jian==2){
                        mvpView.getAddRecord_TextView_Symbol().setText("- "+stringBuilder2.toString());
                    }
                }
            }
        }else {    /**长度为0的时候 说明还没有填入数字**/
            if(i!=0){   /**这个时候就添加数字  但是如果添加为0 那么就没有意义 所以长度为0的时候传入的数字不能为0**/
                stringBuilder2.append(i);
                if(Jia_Jian==1){
                    mvpView.getAddRecord_TextView_Symbol().setText("+ "+stringBuilder2.toString());
                }else if (Jia_Jian==2){
                    mvpView.getAddRecord_TextView_Symbol().setText("- "+stringBuilder2.toString());
                }
            }
        }
    }
    /**添加进数据库的方法**/
    private void AddRecord(){
        TimeLine timeLine=new TimeLine();
        timeLine.setDirection(LR);  //方向  True=左（收入）   false=右（支出）
        timeLine.setIcon_Class(index);
        timeLine.setMessage(mvpView.getAddRecord_EditText_Message().getText().toString().trim());
        timeLine.setImageUrl(mvpView.getImageUrl());
        timeLine.setNoteBook(0);
        timeLine.setPrice(Double.parseDouble(mvpView.getAddRecord_TextView_Sum().getText().toString().trim()));
        timeLine.setTime(setChooseTime);
        if(timeLine.save()){
            Toast.makeText(mvpView.getActivityContext(), "添加成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mvpView.getActivityContext(), "添加失败", Toast.LENGTH_SHORT).show();
        }
    }
    /**选择日期的方法**/
    private void getAddTime(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mvpView.getActivityContext());
        View dialogView = View.inflate(mvpView.getActivityContext(), R.layout.z_dialog_date_layout, null);
        final DatePicker datePicker = dialogView.findViewById(R.id.datePicker);
        builder.setPositiveButton("选择", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append(datePicker.getYear()+"-");
                if(datePicker.getMonth()<9){
                    stringBuilder.append("0"+(datePicker.getMonth()+1)+"-");
                }else {
                    stringBuilder.append((datePicker.getMonth()+1)+"-");
                }
                if(datePicker.getDayOfMonth()<10){
                    stringBuilder.append("0"+datePicker.getDayOfMonth());
                }else {
                    stringBuilder.append(datePicker.getDayOfMonth());
                }
                setChooseTime=stringBuilder.toString();
                mvpView.getAddRecord_TextView_MessageTime().setText(setChooseTime);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.setTitle("设置日期");
        dialog.setView(dialogView);
        dialog.show();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) Utils.getWindow(true,mvpView.getThisActivity())-100;
        params.height = (int) Utils.getWindow(false,mvpView.getThisActivity())-100;
        dialog.getWindow().setAttributes(params);
    }

    /**添加点击事件**/
    private void setClick(){
        mvpView.getInclude_Back().setOnClickListener(this);
        mvpView.getAddRecord_RelativeLayout_layout().setOnClickListener(this);
        mvpView.getAddRecord_TextView_AddMessage().setOnClickListener(this);
        mvpView.getAddRecord_ImageView_Camera().setOnClickListener(this);
        mvpView.getAddRecord_TextView_Cancel().setOnClickListener(this);
        mvpView.getAddRecord_TextView_Determine().setOnClickListener(this);
        mvpView.getAddRecord_TextView_MessageTime().setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Include_Back:  //返回按钮
                mvpView.getThisActivity().finish();
                break;
            /*************************************/
            case R.id.AddRecord_TextView_AddMessage:  //打开备注输入的界面
                mvpView.getAddRecord_EditText_Message().setFocusable(true);
                mvpView.getAddRecord_EditText_Message().setFocusableInTouchMode(true);
                //请求获得焦点
                mvpView.getAddRecord_EditText_Message().requestFocus();
                //调用系统输入法
                InputMethodManager inputManager = (InputMethodManager)  mvpView.getAddRecord_EditText_Message()
                        .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput( mvpView.getAddRecord_EditText_Message(), 0);

                mvpView.getAddRecord_RelativeLayout_layout().setVisibility(View.VISIBLE);
                break;
            /*************************************/
            case R.id.AddRecord_RelativeLayout_layout:  //输入界面
                try {
                    ((InputMethodManager) mvpView.getThisActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(mvpView.getThisActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }catch (Exception e){

                }
                mvpView.getAddRecord_RelativeLayout_layout().setVisibility(View.GONE);
                break;
            /*************************************/
            case R.id.AddRecord_ImageView_Camera:   //照片界面
                PictureSelector.create(mvpView.getThisActivity()).openGallery(PictureMimeType.ofImage())   //选择图片
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .minSelectNum(1)// 最小选择数量 int
                        .imageSpanCount(4)// 每行显示个数 int
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(true)// 是否可预览图片 true or false
                        .isCamera(true)// 是否显示拍照按钮 true or false
                        .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                break;
            /*************************************/
            case R.id.AddRecord_TextView_NoteBook://账本
                break;
            /*************************************/
            case R.id.AddRecord_TextView_Cancel://取消按钮
                try {
                    ((InputMethodManager) mvpView.getThisActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(mvpView.getThisActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }catch (Exception e){

                }
                mvpView.getAddRecord_RelativeLayout_layout().setVisibility(View.GONE);
                break;
            /*************************************/
            case R.id.AddRecord_TextView_Determine://确定按钮
                try {
                    ((InputMethodManager) mvpView.getThisActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(mvpView.getThisActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }catch (Exception e){

                }
                mvpView.getAddRecord_RelativeLayout_layout().setVisibility(View.GONE);
                break;
            /*************************************/
            case R.id.AddRecord_TextView_MessageTime://日期选择
                getAddTime();
                break;
        }
    }

}
