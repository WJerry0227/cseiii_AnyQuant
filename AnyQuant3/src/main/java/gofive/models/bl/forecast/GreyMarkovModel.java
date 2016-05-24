package gofive.models.bl.forecast;

import org.ujmp.core.Matrix;
import org.ujmp.core.calculation.Calculation;

import java.util.ArrayList;

/**
 * Created by xu on 2016/5/8.
 *
 * 马尔可夫模型
 */
public class GreyMarkovModel {
    ArrayList<Double> orig; //原始输入数据，前一天与后一天股价的比值
    int[][] stateTransMat;  //状态转移矩阵 index1:初始状态 index2:结果状态
    State[] states;         //状态

    /**
     * 构造方法
     * @param input 原始数据
     * @param boundNum 状态个数
     * 状态分界 [bound0,bound1],[bound1,bound2],...,[bound n-1, bound n]
     *
     */
    public GreyMarkovModel(ArrayList<Double> input, int boundNum){
        stateTransMat = new int[boundNum][boundNum];
        states = new State[boundNum];
        orig = input;
        initStates(boundNum);
        initStateTransMat();
    }

    private void initStateTransMat(){
        for(int i = 0 ; i < states.length; i ++){//初始化为0
            for(int j = 0 ; j < states.length; j++){
                stateTransMat[i][j] = 0;
            }
        }
        for(int i = 0 ; i < orig.size() - 1; i++ ){//初始化状态转移矩阵
            int index1 = checkState(orig.get(i));
            int index2 = checkState(orig.get(i + 1));
            stateTransMat[index1][index2] += 1;
        }
    }

    private int checkState(double input){//检查数据属于哪个状态
        for(int i = 0; i < states.length; i ++){
            if(states[i].isIn(input)){
                return i;
            }
        }
        return -1;
    }

    private void initStates(int boundNum){//根据界限初始化状态序列
        double min = 100;
        double max = 0;
        double interval = 0;
        for(int i = 0 ; i < orig.size() ; i ++){
            if(orig.get(i) > max) max = orig.get(i);
            if(orig.get(i) < min) min = orig.get(i);
         }

        interval = (max - min) / boundNum;
        states = new State[boundNum];
        for(int i = 1 ; i <= boundNum ; i++){
            states[i - 1] = new State(min + interval * (i - 1),min + interval * (i));
        }
    }

    public double forecast(){//获取预测值(预测股价与最新股价的比值)
        double gForecast = GREY();//GM（1,1）预测结果
        double mForecast = 0 ;
        int index = checkState(orig.get(orig.size() - 1));
        int count = 0;
        int stateIndex = -1;
        for(int i = 0 ; i < stateTransMat[index].length; i++){
            if(stateTransMat[index][i] > count){
                count = stateTransMat[index][i];
                stateIndex = i;
            }
        }
        if(stateIndex == -1){
            return -1;
        }
        mForecast = states[stateIndex].getMid();//马尔可夫模型预测结果
        //用马尔可夫模型预测结果修正灰色模型预测结果
        return mForecast * gForecast;

    }

    public double GREY(){
        ArrayList<Double> x0 = orig;//原始数据
        ArrayList<Double> x1 = new ArrayList<>();//累加生成序列
        //x1(i) = x0(0)+x0(1)+...+x0(i)
        for(int i = 0 ; i < x0.size(); i++){
            double temp = 0;
            for( int j = 0 ; j <= i; j++){
                temp += x0.get(j);
            }
            x1.add(temp);
        }
        //紧邻均值生成
        Matrix Bm = Matrix.Factory.zeros(x0.size() - 1,2);
        Matrix Ym = Matrix.Factory.zeros(x0.size() - 1,1);
        for(int i = 0 ; i < x1.size() - 1; i ++){
            double temp = (x1.get(i) + x1.get(i+1)) * (-0.5);
            Bm.setAsDouble(temp,i,0);//row i; column 0
            Bm.setAsDouble(1,i,1);
            Ym.setAsDouble(x0.get(i + 1), i,0);
        }
//        System.out.println("B matrix");
        System.out.println(Bm);
//        System.out.println("Y matrix");
        System.out.println(Ym);
        // 计算（a,b）t = （BtB）-1 BtY
        Matrix Bt = Bm.transpose(Calculation.Ret.NEW);
//        System.out.println("BT matrix");
        System.out.println(Bt);
        Matrix BTB = Bt.mtimes(Bm);
//        System.out.println("BTB matrix");
        System.out.println(BTB);
        Matrix BTBinv = BTB.inv();
//        System.out.println("BTBinv matrix");
        System.out.println(BTBinv);
        BTBinv = BTBinv.mtimes(Bt);
        BTBinv = BTBinv.mtimes(Ym);
//        System.out.println("result matrix");
        System.out.println(BTBinv);
        double a = BTBinv.getAsDouble(0,0);
        double b = BTBinv.getAsDouble(1,0);
        ArrayList<Double> px1 = new ArrayList<>();
        ArrayList<Double> px0 = new ArrayList<>();
        for(int k = 0 ; k <= x0.size(); k++){//前n项与原始数据对比，最后一项为x1预测项
            double temp = (x0.get(0) - b/a)*Math.exp((-a) * k) + b/a;
            px1.add(temp);
        }
//        System.out.println("x1:"+px1);
        px0.add(px1.get(0));
        for(int k = 1; k < px1.size();k++){
            double temp = px1.get(k) - px1.get(k-1);
            px0.add(temp);
        }
//        System.out.println("x0:"+px0);
        return px0.get(px0.size() - 1);
    }


    /**
     * 状态类
     */
    class State{//半开半闭区间，[start,end)
        double start;
        double end;

        public State(){
            start = 0;
            end = 0;
        }

        public  State(double start, double end){
            this.start = start;
            this.end = end;
        }

        public double getMid(){
            return (start + end) /2;
        }

        boolean isIn(double target){
            if(target >= start && target <= end){
                return true;
            }else {
                return false;
            }
        }
    }
}
