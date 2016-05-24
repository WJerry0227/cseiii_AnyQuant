package gofive.models.bl.indicator;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import gofive.models.bl.consts.IndexFeature;
import gofive.models.bl.consts.Reliability;

import javax.print.DocFlavor;
import javax.servlet.jsp.el.ELException;
import java.util.ArrayList;

/**
 * Created by xu on 2016/5/14.
 */
public class RSI extends Indicator {
    int flag = 0;
    double[] rsi6;
    double[] rsi12;
    @Override
    public IndexFeature[] analysis() {
        ArrayList<IndexFeature> features = new ArrayList<>();
        if (goldX()) features.add(IndexFeature.RSI_GOLDEN_CROSS);
        else if (deadX()) features.add(IndexFeature.RSI_DEAD_CROSS);

        if (superBuy()) features.add(IndexFeature.RSI_SUPER_BUY);
        else if (superSell()) features.add(IndexFeature.RSI_SUPER_SELL);
        else if (add()) features.add(IndexFeature.RSI_ADD);
        else if (watch()) features.add(IndexFeature.RSI_STAY);

        return features.toArray(new IndexFeature[features.size()]);
    }

    @Override
    public int forecast() {
        return 0;
    }

    @Override
    public Reliability getReliability() {
        return Reliability.normal;
    }


    private boolean goldX(){
        boolean flag = false;
        for (int i = rsi6.length - 10 ; i < rsi6.length ; i++){//10日内保持低位
            if (rsi6[i] > 30 || rsi12[i] > 30) {
                flag = true;
                break;
            }
        }
        if (flag == false){
            int m = 1;
            for (int i = rsi6.length - 3; i < rsi6.length; i ++){
                if (rsi6[i] < rsi12[i] && m == 1) m = 2;//6 低于 12
                else if (rsi6[i] > rsi12[i] && m == 2) m = 3;// 6 高于 12
            }
            if (m == 3){
                this.flag += 1;
                return true;
            }
        }
        return false;
    }

    private boolean deadX(){
        boolean flag = false;
        for (int i = rsi6.length - 10; i < rsi6.length; i++){//10日内保持高位
            if (rsi6[i] < 70 || rsi12[i] < 70){
                flag = true;
                break;
            }
        }
        if (flag == false){
            int m = 1;
            for (int i = rsi6.length - 3; i < rsi6.length ; i++){
                if (rsi6[i] > rsi12[i] && m == 1) m = 2;//6 高于 12
                else if (rsi6[i] < rsi12[i] && m == 2) m = 3;//6 低于 12
            }
            if (m == 3) {
                this.flag -= 1;
                return true;
            }
        }
        return false;
    }

    private boolean superSell(){
        if (rsi6[rsi6.length - 1] < 20) {
            flag += 1;
            return true;
        }
        return false;
    }

    private boolean superBuy(){
        if (rsi6[rsi6.length - 1] > 80) {
            flag -= 1;
            return true;
        }
        return false;
    }

    private boolean add(){
        if (rsi12[rsi12.length - 1] > 50 && rsi12[rsi12.length - 1] < 80){
            flag += 1;
            return true;
        }
        return false;
    }

    private boolean watch(){
        if (rsi12[rsi12.length - 1] > 20 && rsi12[rsi12.length - 1] < 50) return true;
        return false;
    }

    public RSI(double[] rsi6,double[] rsi12){
        this.rsi6 = rsi6;
        this.rsi12 = rsi12;
    }

    public void setData(double[] rsi6,double[] rsi12){
        this.rsi6 = rsi6;
        this.rsi12 = rsi12;
    }

}
