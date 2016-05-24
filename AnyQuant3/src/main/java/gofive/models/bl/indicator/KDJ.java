package gofive.models.bl.indicator;

import gofive.models.bl.consts.IndexFeature;
import gofive.models.bl.consts.Reliability;
import gofive.models.bl.forecast.LinearRegrassion;
import org.omg.CORBA.DATA_CONVERSION;

import java.util.ArrayList;

/**
 * Created by xu on 2016/5/14.
 */
public class KDJ extends Indicator {
    int flag = 0;
    double[] k;
    double[] d;
    double[] j;
    @Override
    public IndexFeature[] analysis() {
        ArrayList<IndexFeature> features = new ArrayList<>();
        if (all80()) features.add(IndexFeature.KDJ_EXTRA_BUY);
        else if (all20()) features.add(IndexFeature.KDJ_EXTRA_SELL);
        else if (swing()) features.add(IndexFeature.KDJ_SWING);

        if (goldenX()) features.add(IndexFeature.KDJ_GOLDEN_CROSS);
        if (deadX()) features.add(IndexFeature.KDJ_DEATH_CROSS);
        return features.toArray(new IndexFeature[features.size()]);
    }

    private boolean swing(){
        boolean k = false;
        boolean d = false;
        boolean j = false;
        if (this.k[this.k.length - 1] > 20 && this.k[this.k.length - 1] < 80) k = true;
        if (this.d[this.d.length - 1] > 20 && this.d[this.d.length - 1] < 80) k = true;
        if (this.j[this.j.length - 1] > 20 && this.j[this.j.length - 1] < 80) k = true;
        if( k && d && j){
            return true;
        }
        return false;
    }

    private boolean all80(){
        if(     k[k.length - 1] > 80 &&
                d[d.length - 1] > 80 &&
                j[j.length - 1] > 80){
            this.flag -= 1;
            return true;
        }
        return false;
    }

    private boolean all20(){
        if(     k[k.length - 1] < 20 &&
                d[d.length - 1] < 20 &&
                j[j.length - 1] < 20){
            this.flag += 1;
            return true;
        }
        return false;
    }

    private boolean goldenX(){
        boolean flag = false;
        for (int i = k.length - 8; i < k.length - 3; i++){//5日低于k,d,j低于50
            if (k[i] > 50 || d[i] > 50 || j[i] > 50){
                flag = true;
                break;
            }
        }
        if (flag == true){
            flag = false;
            for (int i = k.length - 8; i < k.length - 3;i++){//5日k,d,j在45~55徘徊
                if (k[i] < 45 || k[i] > 55 || d[i] < 45 || d[i] > 45 || j[i] < 45 || j[i] > 55) {
                    flag = true;
                    break;
                }
            }
        }
        if (flag == false){
        int f = 1;
            for (int i = k.length - 3; i < k.length; i++){//三天之内k,j向上穿越d
                if (k[i] < d[i] && j[i] < d[i] && f == 1) f = 2;//f = 2:k,j 低于d
                else if (k[i] > d[i] && j[i] > d[i] && f == 2) f = 3;//f = 3:k,j 高于d
            }
            if (f == 3) {
                this.flag += 1;
                return true;
            }
        }
        return false;
    }

    private boolean deadX(){
        boolean flag = false;
        for (int i = k.length - 10; i < k.length - 3;i++){//7日内k>80,j>75,d>70
            if (k[i] < 80 || d[i] < 70 || j[i] < 75){
                flag = true;
                break;
            }
        }
        if (flag == false){
            int f = 1;
            for (int i = k.length - 3; i < k.length ; i++){
                if (k[i] > d[i] && j[i] > d[i] && f == 1) f = 2;//k,j高于d
                else if (k[i] < d[i] && j[i] < d[i] && f == 2) f = 3;//k,j低于d
             }
            if (f == 3){
                this.flag -= 1;
                return true;
            }
        }
        return false;
    }




    @Override
    public int forecast() {
        return flag;
    }

    @Override
    public Reliability getReliability() {
        return Reliability.normal;
    }

    public KDJ(double[] k,double[] d, double[] j){
        this.k = k;
        this.d = d;
        this.j = j;
    }

    public void setDate(double[] k,double[] d, double[] j){
        this.k = k;
        this.d = d;
        this.j = j;
    }
}
