package gofive.models.bl.calculator.index;

import gofive.models.db.DBase;
import gofive.models.db.StockInfo;

/**
 * ema 指标
 * Created by coral on 16-4-22.
 */
public class EmaCalculator implements Calculator {

    @Override
    public void defaultCal(DBase[] data) {
        throw new IllegalArgumentException("no default calculator for ema");
    }

    /**
     * 计算股票的ema。
     * @param dataPOs 数据序列
     * @param params 包括一个参数。第一个参数为股票的计算周期。
     */
    @Override
    public void calculate(DBase[] dataPOs, int... params) {
        int timeperiod = params[0];
        if (timeperiod < 1) {
            throw new IllegalArgumentException("Ema calculator takes exactly 1 argument");
        }
        dataPOs[0].setValue("EMA" + timeperiod, dataPOs[0].get("close"));
        for (int i = 1; i < dataPOs.length; i++) {
            double yema = Double.parseDouble(dataPOs[i - 1].get("EMA" + timeperiod).toString());
            double close = Double.parseDouble(dataPOs[i].get("close").toString());
            double ema = yema * (timeperiod - 1) / (timeperiod + 1) + close * 2 / (timeperiod + 1);
            dataPOs[i].setValue("EMA" + timeperiod, ema);
        }
    }

    public void calculate(DBase[] dataPOs, String src, String tar, int... params) {
        int timeperiod = params[0];
        if (timeperiod < 1) {
            throw new IllegalArgumentException("Ema calculator takes exactly 1 argument");
        }
        dataPOs[0].setValue(tar, dataPOs[0].get(src));
        for (int i = 1; i < dataPOs.length; i++) {
            double yema = Double.parseDouble(dataPOs[i - 1].get(tar).toString());
            double close = Double.parseDouble(dataPOs[i].get(src).toString());
            double ema = yema * (timeperiod - 1) / (timeperiod + 1) + close * 2 / (timeperiod + 1);
            dataPOs[i].setValue(tar, ema);
        }
    }

    public static void main(String[] args) {
        DBase[] res = StockInfo.query("603998").where("date > '2015-01-01'");
        new EmaCalculator().calculate(res, 12);
    }
}