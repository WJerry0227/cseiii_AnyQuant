package gofive.models.bl.calculator.index;

import gofive.models.db.DBase;

import java.util.ArrayList;

/**
 * @author 这菜咸了
 *相对强弱指数（RSI）是通过比较一段时期内的平均收盘涨数和平均收盘跌数来分析市场买沽盘
 *的意向和实力，从而作出未来市场的走势
 */
public class RSICalculator implements Calculator {

    @Override
    public void defaultCal(DBase[] data) {
        calculate(data, 14);
    }

    /**
     * 计算股票的RSI。计算周期多以14天为一个周期。RSI1是6日相对强弱指标，RSI2是12日相对强弱指标。RSI24是24日相对强弱指标
     * @param dataPOs 数据序列
     * @param params 包括一个参数。第一个参数为股票的计算周期。
     */
    @Override
    public void calculate(DBase[] dataPOs, int... params) {
        int n = params[0];          //周期
        ArrayList<DBase> data = new ArrayList<>();
        for (int i = 0 ; i < dataPOs.length; i++){
            if(isValid(dataPOs[i])){
                data.add(dataPOs[i]);
            }
        }

//      (old)0,1,2,3,4,5,6,7,8,9(new)
        for(int i = n  ;i < data.size(); i++){
            double up = 0;
            double down = 0;
            for(int j = 0 ; j < n; j++){
                double temp = (double)data.get(i - j).get("close") - (double)data.get(i - j - 1).get("close");
                if(temp > 0) up += (temp / n);
                else down -= (temp / n);
            }
            double rs = up/down;
            double rsi = 100 - 100/(rs + 1);
            data.get(i).setValue("RSI"+n,rsi);
        }
//        int timeperiod = params[0];
//            if (timeperiod < 1) {
//                throw new IllegalArgumentException("RSI calculator takes exactly 1 argument");
//            }
//            //初始化rsi值
//            double ups = 0;
//            double downs = 0;
//            for(int i=0;i<timeperiod;i++){
//                dataPOs[i].setValue("RSI"+timeperiod, 50);
//                double upanddown = Double.valueOf(dataPOs[i].get("close").toString())-Double.valueOf(dataPOs[i].get("open").toString());
//                if(upanddown>0){
//                    ups+=upanddown;
//                }else{
//                    downs-=upanddown;
//                }
//            }
//
//            //开始计算
//            for(int i= timeperiod;i<dataPOs.length;i++){
//                double upanddown = Double.valueOf(dataPOs[i].get("close").toString())-Double.valueOf(dataPOs[i].get("open").toString());
//                double hisupanddown = Double.valueOf(dataPOs[i-timeperiod].get("close").toString())-Double.valueOf(dataPOs[i-timeperiod].get("open").toString());
//                if(upanddown>0){
//                    ups+=upanddown;
//                }else{
//                    downs-=upanddown;
//                }
//                //减time之前的
//                if(hisupanddown>0){
//                    ups-=upanddown;
//                }else{
//                    downs+=upanddown;
//                }
//
//                double rs = ups/downs;
//                double rsi =100* rs/(1+rs);
//                dataPOs[i].setValue("RSI"+timeperiod, rsi);
//        }

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