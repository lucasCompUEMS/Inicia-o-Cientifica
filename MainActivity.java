package com.example.equaodoprimeirograu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //criando obj privado
    private final SetId mObjId = new SetId();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //aqui vai pegar o conteudo das tag com os id de identificacao
        mObjId.Input = findViewById(R.id.input);
        mObjId.Output = findViewById(R.id.output);
        mObjId.ButtonCalc = findViewById(R.id.IButton);
        mObjId.ButtonClean = findViewById(R.id.IButtonClean);
        mObjId.ButtonCalc.setOnClickListener(this);//registra o metodo onClick do listener de eventos OnclickListener desta aplicação do atributo mObjId.ButtonCalc
        mObjId.ButtonClean.setOnClickListener(this);//registra o metodo onClick do listener de eventos OnclickListener desta aplicação do atributo mObjId.ButtonClean
    }


    //chama funcao onClick() quando clicado pelo usuario e identifica qual botao foi clicado
    @SuppressLint("DefaultLocale")
    public void onClick(View v) {
        if(v.getId() == R.id.IButton){
            String TextInput = mObjId.Input.getText().toString().replace(" ", "").toLowerCase();
            Integer IndexIgual = TextInput.indexOf("="), i, init;
            Float OpValor = 0.0f, OpIncog = 0.0f;
            boolean b = TextInput.matches(".*[.]\\D+.*|.*[-+]=.*|\\D+[.].*|.*_.*|.*=.*=.*|.*[-+]{2,}.*|.*[x]{2,}.*|.*[x]\\d+.*|.*[a-wyz].*|.*[\\W&&[^-+=.]].*|.*[\\W+]$");
            char[] array = TextInput.toCharArray();//converter em vetor de caracteres para manipulacao

            CalcInc ResInc = new CalcInc();
            CalcValor ResValor = new CalcValor();

            if(b || IndexIgual == -1){//verifica se n é vazia ou se nao tem igual "="
                mObjId.Output.setText("");
                Toast.makeText(this, getString(R.string.invalidez), Toast.LENGTH_SHORT).show();//CRIAR MENSAGEM COM UM DETERMINADO TEMPO E MOSTRAR

            }else {

                int TextSize = TextInput.length();
                init = 0;

                for (i = 0; i < TextSize; i++){

                    if (array[i] == 'x' || ((array[i] == '-' || array[i] == '+' || array[i] == '=') && init < i) || i == TextSize - 1) {

                        if (array[i] == 'x') {
                            ResInc.SetOpIncog(OpIncog);
                            ResInc.SetInit(init);
                            ResInc.SetCalcInc(TextInput, array, IndexIgual, i);
                            init = ResInc.init;
                            OpIncog = ResInc.OpIncog;

                        }
                        else
                        {
                            ResValor.SetOpValor(OpValor);
                            ResValor.SetInit(init);
                            ResValor.SetCalcValor(IndexIgual, TextSize, i, TextInput, array);
                            init = ResValor.init;
                            OpValor = ResValor.OpValor;
                        }

                    }

                }

                float res = -OpValor/OpIncog;
                String OpIncogStr = OpIncog + "x", OpValorStr;
                String CalcResultante = OpIncogStr;

                if(OpIncog == 0) {

                    if(OpValor != 0)
                        Toast.makeText(this, "SOLUCAO IMPOSSÍVEL!!", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(this, "SOLUCAO POSSIVEL INDETERMINADO!!", Toast.LENGTH_LONG).show();
                }
                else {
                    if (OpValor >= 0) {
                        if(OpValor > 0) {
                            OpValorStr = "+ ";
                            OpValorStr += OpValor;
                            CalcResultante += " " + OpValorStr + " = 0";
                        }
                        else {
                            OpValorStr = "0";

                            CalcResultante += " + " + OpValorStr + " = 0";
                        }
                    } else {
                        OpValorStr = " - " + -OpValor;
                        CalcResultante += OpValorStr + " = 0";
                    }

                    if (OpValor != 0)
                        OpValorStr = String.valueOf(-OpValor);
                    else
                        res = 0.0f;


                    mObjId.Output.setText(String.format("\n%s\n %s = %s\n\n\n  x = %.3f", CalcResultante, OpIncogStr, OpValorStr, res));
                }

            }
        }
        else{
            mObjId.Input.setText("");
            mObjId.Output.setText("");
        }

    }

    private static class SetId{
        EditText Input;
        TextView Output;
        Button ButtonCalc, ButtonClean;
    }
}