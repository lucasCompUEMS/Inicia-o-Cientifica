package com.example.equaodoprimeirograu;

public class CalcValor {
    public Integer init;
    public Float OpValor;
    public String AuxVal;

    public void SetInit(Integer init){this.init = init;}

    public void SetOpValor(Float OpValor){this.OpValor = OpValor;}

    public void SetCalcValor(Integer IndexIgual, Integer TextSize, Integer i, String TextInput, char[] array){
        if(init >= IndexIgual){

            if(i == TextSize - 1 && (init == IndexIgual || init > IndexIgual)) {
                if(init == IndexIgual)
                    AuxVal = TextInput.substring(init + 1, i + 1);
                else
                    AuxVal = TextInput.substring(init, i + 1);

            }
            else
                AuxVal = TextInput.substring(init, i);

            OpValor -= Float.parseFloat(AuxVal);
        }
        else
        {

            AuxVal = TextInput.substring(init, i);
            OpValor += Float.parseFloat(AuxVal);

        }

        if(array[i] == '-' || array[i] == '+')
            init = i;

        if(i == IndexIgual)
            init = i+1;
    }
}