package gofive.models.bl.indicator;

import gofive.models.bl.consts.IndexFeature;
import gofive.models.bl.consts.Reliability;
import gofive.models.bl.forecast.LinearRegrassion;

import java.util.ArrayList;

/**
 * Created by xu on 2016/5/14.
 */
public class WR extends Indicator {
    double a = 0;
    double[] wr;
    @Override
    public IndexFeature[] analysis() {
        ArrayList<IndexFeature> features = new ArrayList<>();
        if(a == 0) forecast();
        if(a > 0) features.add(IndexFeature.WR_UP);
        else if (a < 0) features.add(IndexFeature.WR_DOWN);
        double latest = wr[wr.length - 1];
        if(latest < 20) features.add(IndexFeature.WR_SUPER_BUY);
        else if (latest > 80) features.add(IndexFeature.WR_SUPER_SELL);
        else features.add(IndexFeature.WR_STAY);
        return features.toArray(new IndexFeature[features.size()]);
    }

    @Override
    public int forecast() {
        int flag = 0;
        double[] temp = wr;
        ArrayList<Double> wr = new ArrayList<>();
        for(int i = temp.length - 10 ; i < temp.length; i++) { wr.add(temp[i]);}
        LinearRegrassion lr = new LinearRegrassion(wr);
        a = lr.getA();
        if(a > 0) flag += 1;
        else if (a < 0) flag -= 1;
        if(temp[temp.length - 1] < 20) flag -= 1;
        else if (temp[temp.length - 1] > 80) flag += 1;
        return flag;
    }

    @Override
    public Reliability getReliability() {
        return Reliability.low;
    }

    public WR(double[] data){
        wr = data;
    }
    public void setData(double[] data){
        wr = data;
    }
}
