package com.iadlpc.mazesolver;

public class Neuron {

    private double[] weight;    //Número de pesos = número de entradas + 1
    private int defineFuncao;   //0: é a default -> logistica

    public Neuron(double []pesos){
        setWeight(pesos);
        defineFuncao = 0;
    }

    public void setWeight(double [] weight){
        this.weight = weight;
    }

    public void setFuncao(int defineFuncao){
        this.defineFuncao = defineFuncao;
    }
    public double calculateY(double []x){
        double v = 0;
        int i;
        for( i = 0; i < x.length; i++){
            v = v + weight[i] * x[i];
        }
        v = v + weight[i];  //bias

        return (defineFuncao == 1 ? Math.tanh(v) : 1 / (1 + Math.exp(-v)));
    }

    public String toString(){
        String msg="";
        for(int i = 0; i< weight.length; i++) msg = msg + weight[i]+ " ";
        return msg;
    }
}
