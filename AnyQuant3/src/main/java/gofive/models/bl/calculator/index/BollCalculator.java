package gofive.models.bl.calculator.index;

import gofive.models.db.DBase;

import java.util.ArrayList;

/**
 * Created by xu on 2016/4/22.
 */
public class BollCalculator implements Calculator {
    @Override
    public void defaultCal(DBase[] data) {
        calculate(data, 20, 2);
    }

    /**
     * 计算股票BOLL指标值
     * @param dataPOs 股票数据,应提供早于起始日期至少n+1个有效数据
     * @param params N 计算周期，K 波动幅度（加减k个标准差）,通常n=20,k=2
     */
    @Override
    public void calculate(DBase[] dataPOs, int... params) {
        int n = params[0];
        int k = params[1];
        ArrayList<DBase> data = new ArrayList<DBase>();
        for(int i = 0 ; i < dataPOs.length; i++){
            if(isValid(dataPOs[i])){
                data.add(dataPOs[i]);
            }
        }
        //0,1,2,3,4,5,6,7,8,9,10,11,12
        for(int i = n;i < data.size();i ++){
            double ma = 0;
            double mb = 0;
            double biaozhuncha = 0;
            for(int j = 0; j < n; j++){//ma
                ma += Double.valueOf(data.get(i - j).get("close").toString());
            }
            ma = ma / n;
            for(int j = 0 ; j < n; j++){//biaozhuncha
                double t = Double.valueOf(data.get(i - j).get("close").toString());
                biaozhuncha += (t - ma)*(t - ma);
            }
            biaozhuncha = biaozhuncha/n;
            biaozhuncha = Math.sqrt(biaozhuncha);
            for(int j = 0 ; j < n; j++){
                mb +=  Double.valueOf(data.get(i - j - 1).get("close").toString());
            }
            mb = mb / n;
            data.get(i).setValue("BOLL_M", mb);
            data.get(i).setValue("BOLL_U", mb + k * biaozhuncha);
            data.get(i).setValue("BOLL_D", mb - k * biaozhuncha);
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