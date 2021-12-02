package com.iadlpc.mazesolver;

import java.util.Random;

public class Network {
    private Neuron[] hiddenLayer;     //rede neural 8x8x4 -> topologia sugerida em aula
    private Neuron[] outputLayer;
    private double[] output;          //valores de saída da propagacao

    public Network(int hiddenNeuronCount, int outputNeuronCount){
        if(hiddenNeuronCount <= 0 || outputNeuronCount <= 0){
            hiddenNeuronCount = 8;
            outputNeuronCount = 4;
        }
        hiddenLayer = new Neuron[hiddenNeuronCount];
        outputLayer = new Neuron[outputNeuronCount];
    }

    public void balanceNetwork(int inputCount, double[] weights){
        int k=0;
        double []tmp ;

        //occult layer
        for(int i = 0; i < hiddenLayer.length; i++){
            tmp = new double[inputCount + 1];
            for(int j = 0; j < inputCount + 1; j++){
                tmp[j] = weights[k];
                k++;
            }
            hiddenLayer[i] = new Neuron(tmp);
        }

        //output layer
        for(int i = 0; i< outputLayer.length; i++){
            tmp = new double[hiddenLayer.length + 1];
            for(int j = 0; j < hiddenLayer.length + 1; j++){
                tmp[j] = weights[k];
                k++;
            }
            outputLayer[i] = new Neuron(tmp);
        }
    }


    public double[] propagate(double []x){
        if (x == null) { return null; }

        double [] hiddenOutput = new double[hiddenLayer.length];
        output = new double[outputLayer.length];
        for(int i = 0; i< hiddenLayer.length; i++){
            hiddenOutput[i]= hiddenLayer[i].calculateY(x);
        }
        for(int i = 0; i< outputLayer.length; i++){
            output[i]= outputLayer[i].calculateY(hiddenOutput);
        }
        return output;
    }

    public String toString(){
        String msg = "Pesos da rede\n";
        msg = msg + "Camada Oculta\n";
        for(int i = 0; i< hiddenLayer.length; i++){
            msg = msg + "Neurônio " + i + ": " + hiddenLayer[i] + "\n";
        }
        msg = msg + "Camada Saida\n";
        for(int i = 0; i< outputLayer.length; i++){
            msg = msg + "Neuron " + i + ": " + outputLayer[i] + "\n";
        }
        return msg;
    }
}
