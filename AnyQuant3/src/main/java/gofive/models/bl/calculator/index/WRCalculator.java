package gofive.models.bl.calculator.index;

import gofive.models.db.DBase;

import java.util.ArrayList;

/**
 * Created by xu on 2016/4/22.
 */
public class WRCalculator implements Calculator {
    @Override
    public void defaultCal(DBase[] data) {
        calculate(data, 14);
    }

    /**
     * 计算w&r指标
     * 威廉指标主要用于分析市场短期买卖走势，是测量超买超卖的简易指标。
     * 威廉指标利用摆动点来度量股市的的超买超卖现象，可以预测循环期内的高点和低点
     * @param dataPOs 股票数据
     * @param params n 周期,一般取14
     */
    @Override
    public void calculate(DBase[] dataPOs, int... params) {
        //(old)0,1,2,3,4,5,6,7,8,9(new)
        int n = params[0];
        ArrayList<DBase> data = new ArrayList<>();
        for(int i = 0 ; i < dataPOs.length ; i++){
            if(isValid(dataPOs[i]));
            data.add(dataPOs[i]);
        }
        if(data.size() < n) return ;
        for(int i = n -1; i < data.size(); i++){
            double hn = 0;
            double ln = Double.MAX_VALUE;
            double close = (double) data.get(i).get("close");
            double result = 0;
            for(int j = 0 ; j < n ; j ++){
                double h =(double)data.get(i - j).get("high");
                double l = (double)data.get(i - j).get("low");
                if( h > hn) hn = h;
                if( l < ln) ln = l;
            }
            result = (hn - close)/(hn - ln)*100;
            data.get(i).setValue("WR" + n,result);
        }

    }

    private boolean  isValid(DBase data){
        if(data == null){
            return false;
        }else {
            if((double)data.get("close") <= 0){
                return  false;
            }
        }
        return true;
    }
}