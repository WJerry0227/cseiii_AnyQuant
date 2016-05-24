package gofive.models.bl.indicator;

import gofive.models.bl.consts.IndexFeature;
import gofive.models.bl.consts.Reliability;
import gofive.models.bl.forecast.LinearRegrassion;
import gofive.models.db.DBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * BOLL只适合做短期分析,建议传入30日数据
 * Created by xu on 2016/5/14.
 */
public class BOLL extends Indicator{
//    a.股价由下向上穿过上轨线，卖出
//    b.股价由下向上穿过下轨线，买入
//    c.股价由下向上穿越中线，加仓买进
//    d.股价由上向下穿越中线，卖出

    double[] up;
    double[] mb;
    double[] down;
    double[] close;
    int flag;       //势头
    double a;       //股价斜率



    @Override
    public IndexFeature[] analysis() {
        ArrayList<IndexFeature> result = new ArrayList<>();
        if (isBollUpCrossLow()) {
            result.add(IndexFeature.BOLL_UP_CROSS_LOW);
            flag += 1;
        }
        if (isBollDownCrossMdAfterFluc()){
            result.add(IndexFeature.BOLL_DOWN_CROSS_MIDDLE_AFTER_FLUC);
            flag -= 1;
        }
        if (isBollDownCrossUp()) {
            result.add(IndexFeature.BOLL_DOWN_CROSS_UP);
            flag -= 1;
        }
        if (isBollUpCrossMiddle()) {
            result.add(IndexFeature.BOLL_UP_CROSS_MD);
            flag += 1;
        }
        if (isBollFlucBetweenMdUp()) {
            result.add(IndexFeature.BOLL_FLUC_BETWEEN_MD_UP);
        }
        if(a > 0) flag += 1;
        else flag -= 1;
        return result.toArray(new IndexFeature[result.size()]);
    }

    @Override
    public int forecast() {
        //flag 标记状态 2: 很强；1：强；0： 一般；-1 弱 ；-2 很弱
        return flag;
    }

    public boolean isBollUpCrossLow() {
        boolean result = false;
        for (int i = up.length - 5; i < up.length; i++) {
            double yclose = close[i - 1];
            double yboll_d = down[i - 1];
            double close = this.close[i];
            double boll_d = down[i];
            if ((yclose - yboll_d) * (close - boll_d) < 0 && close > boll_d) result = true;
            else if (close < boll_d) result = false;
        }
        return result;
    }

    public boolean isBollUpCrossMiddle() {
        boolean result = false;
        for (int i = up.length - 5; i < up.length; i++) {
            double yclose = this.close[i - 1];
            double yboll_m = mb[i - 1];
            double close = this.close[i];
            double boll_m = mb[i];
            if ((yclose - yboll_m) * (close - boll_m) < 0 && close > boll_m) result = true;
            else if (close < boll_m) result = false;
        }
        return result;
    }

    public boolean isBollFlucBetweenMdUp() {
        boolean result = true;
        for (int i = up.length - 10; i < up.length; ++i) {
            double close = this.close[i];
            double boll_u = up[i];
            double boll_m =mb[i];
            if (close < boll_m || close > boll_u) result = false;
        }
        return result;
    }

    public boolean isBollDownCrossMdAfterFluc() {
        boolean result = true;
        boolean isBollFluc = true;
        for (int i = up.length - 10 - 3; i < up.length - 3; ++i) {
            double close = this.close[i];
            double boll_u = up[i];
            double boll_m = mb[i];
            if (close < boll_m || close > boll_u) isBollFluc = false;
        }
        if (!isBollFluc) return false;
        for (int i = up.length - 3; i < up.length; ++i) {
            double yclose = close[i - 1];
            double yboll_m = mb[i - 1];
            double close = this.close[i];
            double boll_m = mb[i];
            if ((yclose - yboll_m) * (close - boll_m) < 0 && close < boll_m) result = true;
            else if (close > boll_m) result = false;
        }
        return result;
    }

    public boolean isBollDownCrossUp() {
        boolean isUpALot = false;
        boolean result = false;
        for (int i = up.length - 3 - 5; i < up.length - 3; ++i) {
            double close = this.close[i];
            double boll_u =up[i];
            if (close - boll_u > boll_u * 0.05) isUpALot = true;
        }
        if (!isUpALot) return false;
        for (int i = up.length - 3; i < up.length; ++i) {
            double yclose = close[i - 1];
            double yboll_u = up[i - 1];
            double close = this.close[i];
            double boll_u = up[i];
            if ((yclose - yboll_u) * (close - boll_u) < 0 && close < boll_u) result = true;
            else if (close > boll_u) result = false;
        }
        return result;
    }


    @Override
    public Reliability getReliability() {
        return Reliability.normal;
    }

    public BOLL(double[] up, double[] mb , double[] down,double[] close){
        this.up = up;
        this.mb = mb;
        this.down = down;
        this.close = close;
        flag = 0;
        ArrayList<Double> price = new ArrayList<>();
        for(int i = close.length - 10 ; i < close.length ; i++){
            price.add(close[i]);
        }
        LinearRegrassion lr = new LinearRegrassion(price);
        a = lr.getA();
    }

    public void setData(double[] up, double[] mb , double[] down,double[] close){
        this.up = up;
        this.mb = mb;
        this.down = down;
        this.close = close;
        flag = 0;
        ArrayList<Double> price = new ArrayList<>();
        for(int i = close.length - 10 ; i < close.length ; i++){
            price.add(close[i]);
        }
        LinearRegrassion lr = new LinearRegrassion(price);
        a = lr.getA();
    }
}
