package com.cnitpm.financial.Model;

public class BillModel {
    private String CreateTime;  //创建时间  1
    private String InitialDay;   //开始时间  1
    private String EndDay;  //结束时间   1

    private double SpendingSumMoney;  //全部支出金额 1
    private double IncomeSumMoney; //全部收入金额 1

    private double MaxSpendingMoney;  //最高支出金额  1
    private int MaxSpendingMoneyClass; //最高支出金额类型 1
    private String MaxSpendingMoneyTime;  //最高支出金额日期 1
    private int  MaxSpendingMoneyNoteBookId;  //最高支出金额所属账本id  1
    private String  MaxSpendingMoneyNoteBook;  //最高支出金额所属账本  1

    private double MaxIncomeMoney;  //最高收入金额   1
    private int MaxIncomeMoneyClass;  //最高收入金额类型  1
    private String MaxIncomeMoneyTime;  //最高收入金额日期  1
    private int  MaxIncomeMoneyNoteBookId;  //最高收入金额所属账本id  1
    private String MaxIncomeMoneyNoteBook;  //最高收入金额所属账本  1

    private double AverageSpendingMoney; //平均每天的支出 1
    private double AverageIncomeMoney; //平均每天的收入  1

    private int SpendingFrequency; //支出的次数    1
    private int IncomeFrequency; // 收入的次数    1

    private int SpendingFrequencyClass; //支出次数最多的是什么 1
    private int IncomeFrequencyClass;  //收入次数最多的是什么  1

    private String SpendingNoteBook;  //支出最多的账本  1
    private String IncomeNoteBook;  //收入最多的账本  1

    private String SpendingIncomeRatio;  //支付收入的比率
    private int SumDay;// 全部天数  1
    private int NoteBookId;   //账本的id
    private String NoteBookName;  //账本的名字

    @Override
    public String toString() {
        return "BillModel{" +
                "创建时间='" + CreateTime + '\'' +
                ", 开始时间='" + InitialDay + '\'' +
                ", 结束时间='" + EndDay + '\'' +
                ", 全部支出金额=" + SpendingSumMoney +
                ", 全部收入金额=" + IncomeSumMoney +
                ", 最高支出金额=" + MaxSpendingMoney +
                ", 最高支出金额类型=" + MaxSpendingMoneyClass +
                ", 最高支出金额日期='" + MaxSpendingMoneyTime + '\'' +
                ", 最高支出金额所属账本id=" + MaxSpendingMoneyNoteBookId +
                ", 最高支出金额所属账本='" + MaxSpendingMoneyNoteBook + '\'' +
                ", 最高收入金额=" + MaxIncomeMoney +
                ", 最高收入金额类型=" + MaxIncomeMoneyClass +
                ", 最高收入金额日期='" + MaxIncomeMoneyTime + '\'' +
                ", 最高收入金额所属账本id=" + MaxIncomeMoneyNoteBookId +
                ", 最高收入金额所属账本='" + MaxIncomeMoneyNoteBook + '\'' +
                ", 平均每天的支出=" + AverageSpendingMoney +
                ", 平均每天的收入=" + AverageIncomeMoney +
                ", 支出的次数=" + SpendingFrequency +
                ", 收入的次数=" + IncomeFrequency +
                ", 支出次数最多的是什么=" + SpendingFrequencyClass +
                ", 收入次数最多的是什么=" + IncomeFrequencyClass +
                ", 支出最多的账本='" + SpendingNoteBook + '\'' +
                ", 收入最多的账本='" + IncomeNoteBook + '\'' +
                ", 支付收入的比率='" + SpendingIncomeRatio + '\'' +
                ", 全部天数=" + SumDay +
                ", 账本的id=" + NoteBookId +
                ", 账本的名字='" + NoteBookName + '\'' +
                '}';
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getInitialDay() {
        return InitialDay;
    }

    public void setInitialDay(String initialDay) {
        InitialDay = initialDay;
    }

    public String getEndDay() {
        return EndDay;
    }

    public void setEndDay(String endDay) {
        EndDay = endDay;
    }

    public double getSpendingSumMoney() {
        return SpendingSumMoney;
    }

    public void setSpendingSumMoney(double spendingSumMoney) {
        SpendingSumMoney = spendingSumMoney;
    }

    public double getIncomeSumMoney() {
        return IncomeSumMoney;
    }

    public void setIncomeSumMoney(double incomeSumMoney) {
        IncomeSumMoney = incomeSumMoney;
    }

    public double getMaxSpendingMoney() {
        return MaxSpendingMoney;
    }

    public void setMaxSpendingMoney(double maxSpendingMoney) {
        MaxSpendingMoney = maxSpendingMoney;
    }

    public int getMaxSpendingMoneyClass() {
        return MaxSpendingMoneyClass;
    }

    public void setMaxSpendingMoneyClass(int maxSpendingMoneyClass) {
        MaxSpendingMoneyClass = maxSpendingMoneyClass;
    }

    public String getMaxSpendingMoneyTime() {
        return MaxSpendingMoneyTime;
    }

    public void setMaxSpendingMoneyTime(String maxSpendingMoneyTime) {
        MaxSpendingMoneyTime = maxSpendingMoneyTime;
    }

    public String getMaxSpendingMoneyNoteBook() {
        return MaxSpendingMoneyNoteBook;
    }

    public int getMaxSpendingMoneyNoteBookId() {
        return MaxSpendingMoneyNoteBookId;
    }

    public void setMaxSpendingMoneyNoteBookId(int maxSpendingMoneyNoteBookId) {
        MaxSpendingMoneyNoteBookId = maxSpendingMoneyNoteBookId;
    }

    public void setMaxSpendingMoneyNoteBook(String maxSpendingMoneyNoteBook) {
        MaxSpendingMoneyNoteBook = maxSpendingMoneyNoteBook;
    }

    public double getMaxIncomeMoney() {
        return MaxIncomeMoney;
    }

    public void setMaxIncomeMoney(double maxIncomeMoney) {
        MaxIncomeMoney = maxIncomeMoney;
    }

    public int getMaxIncomeMoneyClass() {
        return MaxIncomeMoneyClass;
    }

    public void setMaxIncomeMoneyClass(int maxIncomeMoneyClass) {
        MaxIncomeMoneyClass = maxIncomeMoneyClass;
    }

    public String getMaxIncomeMoneyTime() {
        return MaxIncomeMoneyTime;
    }

    public void setMaxIncomeMoneyTime(String maxIncomeMoneyTime) {
        MaxIncomeMoneyTime = maxIncomeMoneyTime;
    }

    public int getMaxIncomeMoneyNoteBookId() {
        return MaxIncomeMoneyNoteBookId;
    }

    public void setMaxIncomeMoneyNoteBookId(int maxIncomeMoneyNoteBookId) {
        MaxIncomeMoneyNoteBookId = maxIncomeMoneyNoteBookId;
    }

    public String getMaxIncomeMoneyNoteBook() {
        return MaxIncomeMoneyNoteBook;
    }

    public void setMaxIncomeMoneyNoteBook(String maxIncomeMoneyNoteBook) {
        MaxIncomeMoneyNoteBook = maxIncomeMoneyNoteBook;
    }

    public double getAverageSpendingMoney() {
        return AverageSpendingMoney;
    }

    public void setAverageSpendingMoney(double averageSpendingMoney) {
        AverageSpendingMoney = averageSpendingMoney;
    }

    public double getAverageIncomeMoney() {
        return AverageIncomeMoney;
    }

    public void setAverageIncomeMoney(double averageIncomeMoney) {
        AverageIncomeMoney = averageIncomeMoney;
    }

    public int getSpendingFrequency() {
        return SpendingFrequency;
    }

    public void setSpendingFrequency(int spendingFrequency) {
        SpendingFrequency = spendingFrequency;
    }

    public int getIncomeFrequency() {
        return IncomeFrequency;
    }

    public void setIncomeFrequency(int incomeFrequency) {
        IncomeFrequency = incomeFrequency;
    }

    public int getSpendingFrequencyClass() {
        return SpendingFrequencyClass;
    }

    public void setSpendingFrequencyClass(int spendingFrequencyClass) {
        SpendingFrequencyClass = spendingFrequencyClass;
    }

    public int getIncomeFrequencyClass() {
        return IncomeFrequencyClass;
    }

    public void setIncomeFrequencyClass(int incomeFrequencyClass) {
        IncomeFrequencyClass = incomeFrequencyClass;
    }

    public String getSpendingNoteBook() {
        return SpendingNoteBook;
    }

    public void setSpendingNoteBook(String spendingNoteBook) {
        SpendingNoteBook = spendingNoteBook;
    }

    public String getIncomeNoteBook() {
        return IncomeNoteBook;
    }

    public void setIncomeNoteBook(String incomeNoteBook) {
        IncomeNoteBook = incomeNoteBook;
    }

    public String getSpendingIncomeRatio() {
        return SpendingIncomeRatio;
    }

    public void setSpendingIncomeRatio(String spendingIncomeRatio) {
        SpendingIncomeRatio = spendingIncomeRatio;
    }

    public int getSumDay() {
        return SumDay;
    }

    public void setSumDay(int sumDay) {
        SumDay = sumDay;
    }

    public int getNoteBookId() {
        return NoteBookId;
    }

    public void setNoteBookId(int noteBookId) {
        NoteBookId = noteBookId;
    }

    public String getNoteBookName() {
        return NoteBookName;
    }

    public void setNoteBookName(String noteBookName) {
        NoteBookName = noteBookName;
    }


}
