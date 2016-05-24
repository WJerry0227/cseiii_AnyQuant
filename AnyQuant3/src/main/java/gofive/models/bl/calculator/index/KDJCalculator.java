package gofive.models.bl.calculator.index;


import gofive.models.db.DBase;

/**
 * @author 这菜咸了
 * 随机指标KDJ一般是用于股票分析的统计体系，根据统计学原理，通过一个特定的周期（常为9日、9周等）
 * 内出现过的最高价、最低价及最后一个计算周期的收盘价及这三者之间的比例关系，来计算最后一个计算周
 * 期的未成熟随机值RSV，然后根据平滑移动平均线的方法来计算K值、D值与J值，并绘成曲线图来研判股票走势。
 */
public class KDJCalculator implements Calculator {


    @Override
    public void defaultCal(DBase[] data) {
        calculate(data, 9);
    }

    /*
         * 计算股票的KDJ
         * @param dataPOs 数据序列
         * @param params 包括一个参数。第一个参数为KDJ指数的计算周期(一般为9日或者9周)。
         */
    @Override
    public void calculate(DBase[] dataPOs, int... params) {
        int timeperiod = params[0];
        if (timeperiod <= 1) {
            throw new IllegalArgumentException("KDJ calculator takes exactly 1 argument");
        }
        //初始化前timeperiod天的数据
        for(int i =0; i<timeperiod; i++){
            dataPOs[i].setValue("K"+timeperiod+"", 50.0);
            dataPOs[i].setValue("D"+timeperiod+"", 50.0);
            dataPOs[i].setValue("J"+timeperiod+"", 50.0);
        }
        //计算JDK的值
        for(int i =timeperiod; i<dataPOs.length;i++){
            double cn = Double.valueOf(dataPOs[i].get("close").toString());
            double ln = 65536,hn = -65536;
            //计算ln（前time时间内最低价）和hn（前time时间内最高价）
            for(int j=i-timeperiod;j<i;j++){
                ln = ln > Double.valueOf(dataPOs[j].get("close").toString())? Double.valueOf(dataPOs[j].get("close").toString()):ln;
                hn = hn < Double.valueOf(dataPOs[j].get("high").toString())? Double.valueOf(dataPOs[j].get("high").toString()):hn;
            }
            double rsv = 100*(cn-ln)/(hn-ln);
            double kvalue = 2*(Double.valueOf(dataPOs[i-1].get("K"+timeperiod+"").toString()))/3+rsv/3;
            double dvalue = 2*(Double.valueOf(dataPOs[i-1].get("D"+timeperiod+"").toString()))/3+kvalue/3;
            double jvalue = 3*kvalue - 2*dvalue;

            dataPOs[i].setValue("K"+timeperiod, kvalue);
            dataPOs[i].setValue("D"+timeperiod, dvalue);
            dataPOs[i].setValue("J"+timeperiod, jvalue);
        }

    }

}