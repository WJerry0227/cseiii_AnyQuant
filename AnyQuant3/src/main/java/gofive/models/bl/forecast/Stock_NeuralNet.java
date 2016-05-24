package gofive.models.bl.forecast;

import gofive.models.db.DBase;
import gofive.models.db.StockEntry;
import gofive.models.db.StockInfo;
import org.joone.engine.*;
import org.joone.engine.learning.*;
import org.joone.io.FileInputSynapse;
import org.joone.io.MemoryInputSynapse;
import org.joone.io.MemoryOutputSynapse;
import org.joone.net.NeuralNet;

import java.util.ArrayList;

/**
 * Created by coral on 16-5-17.
 */
public class Stock_NeuralNet implements NeuralNetListener{

    NeuralNet nnet;
    SigmoidLayer input, hidden, output;
    FullSynapse syn_IH, syn_HO;
    Monitor monitor;
    MemoryInputSynapse inputSynapse, outputSynapse;

    public void init() {
        input = new SigmoidLayer("input");
        input.setRows(25);
        hidden = new SigmoidLayer("hidden");
        hidden.setRows(10);
        output = new SigmoidLayer("output");
        output.setRows(5);

        syn_IH = new FullSynapse();
        syn_IH.setName("IH");
        syn_HO = new FullSynapse();
        syn_HO.setName("HO");

        input.addOutputSynapse(syn_IH);
        hidden.addInputSynapse(syn_IH);
        hidden.addOutputSynapse(syn_HO);
        output.addInputSynapse(syn_HO);

        inputSynapse = new MemoryInputSynapse();
        input.addInputSynapse(inputSynapse);
        outputSynapse = new MemoryInputSynapse();
        TeachingSynapse trainer = new TeachingSynapse();
        trainer.setDesired(outputSynapse);

        nnet = new NeuralNet();

        nnet.addLayer(input, NeuralNet.INPUT_LAYER);
        nnet.addLayer(hidden, NeuralNet.HIDDEN_LAYER);
        nnet.addLayer(output, NeuralNet.OUTPUT_LAYER);
        nnet.setTeacher(trainer);
        output.addOutputSynapse(trainer);
        nnet.addNeuralNetListener(this);
    }

    public void train() {
        double[][][] data = loadTrainingData("600000", 25, 5);
        double[][] arrInput = data[0];
        double[][] arrOutput = data[1];
        inputSynapse.setInputArray(arrInput);
        inputSynapse.setAdvancedColumnSelector("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25");

        outputSynapse.setInputArray(arrOutput);
        outputSynapse.setAdvancedColumnSelector("1,2,3,4,5");

        monitor = new Monitor();
        monitor.setLearningRate(0.1);
        monitor.setMomentum(0.3);
        monitor.setLearning(true);
        monitor.setTrainingPatterns(arrInput.length);
        monitor.setTotCicles(5000);
        monitor.addNeuralNetListener(this);
        monitor.setParam("singleThreadMode", true);

        long initms = System.currentTimeMillis();

        nnet.start();
        System.out.println(" Total time=  "
                + (System.currentTimeMillis() - initms) + "  ms ");    }

    public static double[][][] loadTrainingData(String stockID, int inputSize, int outputSize) {
        double[][][] result = new double[2][][];
        ArrayList<double[]> alArray = new ArrayList<>();
        DBase[] stockinfo = StockInfo.query(stockID).where("volume > 0");
        for (int i = 0; i <= stockinfo.length - inputSize - outputSize; ++i) {
            double[] row = new double[inputSize];
            for (int j = 0; j < inputSize; j++) {
                row[j] = Double.parseDouble(stockinfo[i + j].get("close").toString());
            }
            alArray.add(row);
        }
        result[0] = alArray.toArray(new double[alArray.size()][]);

        alArray = new ArrayList<>();
        stockinfo = StockInfo.query(stockID).where("volume > 0");
        for (int i = 0; i <= stockinfo.length - inputSize - outputSize; ++i) {
            double[] row = new double[outputSize];
            for (int j = inputSize; j < outputSize + inputSize; j++) {
                row[j - inputSize] = Double.parseDouble(stockinfo[i + j].get("close").toString());
            }
            alArray.add(row);
        }
        result[1] = alArray.toArray(new double[alArray.size()][]);

        return result;
    }

    public static void main(String[] args) {
        Stock_NeuralNet stockNn = new Stock_NeuralNet();
        stockNn.init();
        stockNn.train();
        stockNn.interrogate();
    }

    public void interrogate() {
        double[][] inputArray = {
                {10, 10.1, 10.2, 10.3, 10.4, 10.5, 10.6, 10.7, 10.8, 10.9, 11.0, 11.1,
                11.2, 11.3, 11.4, 11.5, 11.6, 11.7, 11.8, 11.9, 12.0, 12.1, 12.2, 12.3, 12.4}
        };
        inputSynapse.setInputArray(inputArray);
        inputSynapse.setAdvancedColumnSelector("1-25");
        Monitor monitor = nnet.getMonitor();
        monitor.setTrainingPatterns(4);
        monitor.setTotCicles(1);
        monitor.setLearning(false);
        MemoryOutputSynapse memout = new MemoryOutputSynapse();

        if (nnet != null) {
            nnet.addOutputSynapse(memout);
            System.out.println(nnet.check());
            nnet.start();

            for (int i = 0; i < 4; ++i) {
                double[] pattern = memout.getNextPattern();
                System.out.println(" Output pattern # " + (i + 1) + " = "
                        + pattern[0]);
            }
            System.out.println("finished");
        }
    }

    @Override
    public void netStarted(NeuralNetEvent neuralNetEvent) {
        Monitor mon = (Monitor) neuralNetEvent.getSource();
        System.out.print(" Network started for  ");
        if (mon.isLearning())
            System.out.println(" training. ");
        else
            System.out.println(" interrogation. ");
    }

    @Override
    public void cicleTerminated(NeuralNetEvent neuralNetEvent) {

    }

    @Override
    public void netStopped(NeuralNetEvent neuralNetEvent) {
        Monitor mon = (Monitor) neuralNetEvent.getSource();
        System.out.println(" Network stopped. Last RMSE= "
                + mon.getGlobalError());
    }

    @Override
    public void errorChanged(NeuralNetEvent neuralNetEvent) {
        Monitor mon = (Monitor) neuralNetEvent.getSource();
        if (mon.getCurrentCicle() % 100 == 0)
            System.out.println(" Epoch:  "
                    + (mon.getTotCicles() - mon.getCurrentCicle()) + "  RMSE: "
                    + mon.getGlobalError());
    }

    @Override
    public void netStoppedError(NeuralNetEvent neuralNetEvent, String s) {
        System.out.println(" Network stopped due the following error:  "
                + s);
    }

}
