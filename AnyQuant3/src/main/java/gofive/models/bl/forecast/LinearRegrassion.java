package gofive.models.bl.forecast;

import java.util.ArrayList;

/**
 * 线性回归分析
 * 最小二乘法
 * Created by xu on 2016/5/11.
 */
public class LinearRegrassion {
    /**
     * 原始数据：
     * x: 若不为null， 则使用x; 否则，使用y index
     * y: orig data
     */
    ArrayList<Double> y ;
    ArrayList<Double> x;
    double a;//斜率
    double b;//截距
    public LinearRegrassion(ArrayList<Double> y){
        this.y = y;
        x = null;
        calculate();
    }
    public LinearRegrassion(ArrayList<Double> x , ArrayList<Double> y){
        this.x = x;
        this.y = y;
        calculate();
    }

    private double getAvg(ArrayList<Double> input){
        if(input == null){
            return (y.size() - 1)/2;
        }else {
            double sum = 0;
            for(int i = 0 ; i < input.size() ; i ++){
                sum += input.get(i);
            }
            return (sum / input.size());
        }
    }

    private void calculate(){
        double xb = getAvg(x);
        double yb = getAvg(y);
        double up = 0;
        double down = 0;
        if(x == null){//没有横轴的情况下
            for(int i = 0 ; i < y.size() ; i++){
                up += (i - xb) * (y.get(i) - yb);
                down += (i - xb) * (i - xb);
            }

            a = up / down;
            b = yb - a * xb;
        }else {//有横轴的情况下
            for(int i = 0 ; i < y.size() ; i++){
                up += (x.get(i)- xb) * (y.get(i) - yb);
                down += (x.get(i) - xb);
            }
            a = up / down;
            b = yb - a * xb;
        }
    }

    public double getA(){ return  a;}
    public double getB(){ return  b;}


//    public static void main(String[] args){
//        ArrayList<Double> a = new ArrayList<>();
//        for(int i = 0 ; i < 20; i++){
//            a.add(i * (-3.0));
//        }
//        LinearRegrassion lr = new LinearRegrassion(a);
//        double aa = lr.getA();
//        double b = lr.getB();
//        System.out.println("a:"+aa+"b:"+b);
//    }


}
