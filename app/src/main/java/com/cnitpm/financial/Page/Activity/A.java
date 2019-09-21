package com.cnitpm.financial.Page.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cnitpm.financial.Base.BaseActivity;
import com.cnitpm.financial.Model.BillModel;
import com.cnitpm.financial.R;
import com.cnitpm.financial.Util.SqlOperation;
import com.cnitpm.financial.Util.Utils;

import java.util.List;

public class A extends BaseActivity {
    private TextView afsdgdfg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a);
        hiddenBar();
        Bundle bundle=getIntent().getBundleExtra("KEY");
        List<BillModel>  billModels=new SqlOperation().SelectAll(BillModel.class);
        BillModel billModel=billModels.get(bundle.getInt("key"));

        afsdgdfg=findViewById(R.id.afsdgdfg);
        String s=text(billModel);
        afsdgdfg.setText(s);

    }

    private String text(BillModel billModel){
        String string="这个账单创建于:"+billModel.getCreateTime()+"\n"+
                "账单记录的开始时间是:"+billModel.getInitialDay()+"\n"+
                "而账单记录的结束时间是："+billModel.getEndDay()+"\n"+
                "账单记录跨越"+billModel.getSumDay()+"天，\n"+
                "这个账单是你所有的账本的这段时间的记录\n"+
                "其中你收入最多的账本是："+billModel.getMaxIncomeMoneyNoteBook()+"\n"+
                "而支出最多的账户则是："+billModel.getMaxSpendingMoneyNoteBook()+"\n"+
                "你所有的支出金额是："+billModel.getSpendingSumMoney()+"\n"+
                "所有的收入是："+billModel.getIncomeSumMoney()+"\n"+
                "在"+billModel.getMaxSpendingMoneyNoteBook()+"个账本中，"+billModel.getMaxSpendingMoneyTime()+"这一天出现了最大的一笔支出，一共支出了："+billModel.getMaxSpendingMoney()+"\n"+
                "而支出的类型是："+ Utils.RightClass[billModel.getMaxSpendingMoneyClass()]+"\n"+
                "另外在"+billModel.getMaxIncomeMoneyNoteBook()+"个账本中，"+billModel.getMaxIncomeMoneyTime()+"这一天出现了最大的一笔收入，一共收入了："+billModel.getMaxIncomeMoney()+"\n"+
                "而收入的类型是："+Utils.LeftClass[billModel.getMaxIncomeMoneyClass()]+"\n"+
                "在这么多天中平均每天支出："+billModel.getAverageSpendingMoney()+",而支出最多的是："+Utils.RightClass[billModel.getSpendingFrequencyClass()]+"一共有"+billModel.getSpendingFrequency()+"次支出\n"+
                "每天的收入则是："+billModel.getAverageIncomeMoney()+",而支出最多的是："+Utils.LeftClass[billModel.getIncomeFrequencyClass()]+"一共有"+billModel.getIncomeFrequency()+"次收入\n";


        return string;
    }
}
