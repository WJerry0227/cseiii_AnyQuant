package gofive.models.bl.forecast;

import java.util.ArrayList;

/**
 * 短期预测
 * Created by xu on 2016/5/10.
 */
public class ShortTermForecast {
    ArrayList<Double> orig;//股价原始数据
    GreyMarkovModel model ;//灰色马尔可夫模型
    double modelForecast = 0;
    double priceForecast = 0;
    public ShortTermForecast(ArrayList<Double> input){
        orig = input;
        ArrayList<Double> ratio = new ArrayList<>();
        for(int i = 0; i < orig.size() - 1;i ++){//计算股价比值序列
            double tempRadio = orig.get(i + 1)/orig.get(i);
            ratio.add(tempRadio);
        }
        model = new GreyMarkovModel(ratio,5);
        modelForecast = model.forecast();
        priceForecast = (orig.get(orig.size() - 1) * modelForecast);
        //模型预测结果与最新数据相乘得到预测值
    }

    public double getPriceForecast(){
        return priceForecast;
    }

    public boolean isUp(){
        if(modelForecast >1) return  true;
        else return false;
    }


    public static void main(String[] args){
        ArrayList<Double> d = new ArrayList<>();
        d.add(10.5);
        d.add(10.3);
        d.add(12.3);
        d.add(14.8);
        d.add(18.9);
        d.add(13.8);
        d.add(12.9);
        d.add(15.5);
        d.add(14.2);
        d.add(17.0);
        ShortTermForecast sf = new ShortTermForecast(d);
        double result = sf.getPriceForecast();
        boolean isUp = sf.isUp();
        System.out.println("price:" + result);
        System.out.println("is up? " + isUp);


    }
}
