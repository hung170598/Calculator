package kirito.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.regex.*;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DaThuc daThuc = new DaThuc();

    private TextView inText;
    private TextView resText;

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn0;

    private Button btnOpen;
    private Button btnClose;
    private Button btnC;
    private Button btnAC;
    private Button btnDot;

    private Button btnResult;

    private Button btnAdd;
    private Button btnSub;
    private Button btnMul;
    private Button btnDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initWidget();
        this.initClickEvent();
        this.resText.setText("0");
    }

    public void initWidget(){
        inText = findViewById(R.id.inText);
        resText = findViewById(R.id.resText);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn0 = (Button) findViewById(R.id.btn0);
        btnC = (Button) findViewById(R.id.btnC);
        btnAC = (Button) findViewById(R.id.btnAC);
        btnOpen = (Button) findViewById(R.id.btnOpen);
        btnClose = (Button) findViewById(R.id.btnClose);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnDot = (Button) findViewById(R.id.btnDot);
        btnResult = (Button) findViewById(R.id.btnResult);
    }

    public void initClickEvent(){
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnAC.setOnClickListener(this);
        btnOpen.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        char c = ' ';
        int err = 0;
        switch (v.getId()){
            case R.id.btn0:{
                c = '0';
                break;
            }
            case R.id.btn1:{
                c = '1';
                break;
            }
            case R.id.btn2:{
                c = '2';
                break;
            }
            case R.id.btn3:{
                c = '3';
                break;
            }
            case R.id.btn4:{
                c = '4';
                break;
            }
            case R.id.btn5:{
                c = '5';
                break;
            }
            case R.id.btn6:{
                c = '6';
                break;
            }
            case R.id.btn7:{
                c = '7';
                break;
            }
            case R.id.btn8:{
                c = '8';
                break;
            }
            case R.id.btn9:{
                c = '9';
                break;
            }
            case R.id.btnAdd:{
                c = '+';
                break;
            }
            case R.id.btnSub:{
                c = '-';
                break;
            }
            case R.id.btnMul:{
                c = '*';
                break;
            }
            case R.id.btnDiv:{
                c = '/';
                break;
            }
            case R.id.btnDot:{
                c = '.';
                break;
            }
            case R.id.btnOpen:{
                c = '(';
                break;
            }
            case R.id.btnClose:{
                c = ')';
                break;
            }
            case R.id.btnC:{
                err = this.daThuc.delChar();
                break;
            }
            case R.id.btnAC:{
                this.daThuc.clear();
                break;
            }
            case R.id.btnResult:{
                //result
                break;
            }
        }

        if(c != ' '){
            err = this.daThuc.addChar(c);
        }
        this.inText.setText(this.daThuc.getDaThuc());
        if(err == 1) {
            this.resText.setText(R.string.syntax_err);
        } else if(err ==2) this.resText.setText(R.string.math_err);
        else this.resText.setText(this.daThuc.getResult());
    }
}
