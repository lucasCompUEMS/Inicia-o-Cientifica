package com.example.equaodoprimeirograu;

public class CalcInc {
    public Float OpIncog;
    public Integer init;
    public String AuxInc;

    public void SetInit(Integer init) {
        this.init = init;
    }

    public void SetOpIncog(Float OpIncog){
        this.OpIncog = OpIncog;
    }

    public void SetCalcInc(String TextInput, char[] array, Integer IndexIgual, Integer i){
        AuxInc = TextInput.substring(init, i);
        if(init > IndexIgual){

            if(array[i - 1] == '-' || array[i - 1] == '+' || array[i - 1] == '=') {

                if(array[i - 1] == '+' || array[i - 1] == '=')
                    OpIncog -= 1;

                else
                    OpIncog += 1;

            }
            else
                OpIncog -= Float.parseFloat(AuxInc);

        }
        else{
            if("".equals(AuxInc) || array[i - 1] == '-' || array[i - 1] == '+') {
                if ("".equals(AuxInc) || array[i - 1] == '+')
                    OpIncog += 1;

                else
                    OpIncog -= 1;

            }
            else
                OpIncog += Float.parseFloat(AuxInc);
        }
        init = i + 1;

        if(i + 1 == IndexIgual)
            init++;
    }
}
