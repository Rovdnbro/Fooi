package hogent.fooi.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.BindView;
import hogent.fooi.R;
import hogent.fooi.util.Calculator;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.billEditText)
    EditText billEditText;

    @BindView(R.id.tip10TextView)
    TextView tip10TextView;
    @BindView(R.id.tip15TextView)
    TextView tip15TextView;
    @BindView(R.id.tip20TextView)
    TextView tip20TextView;

    @BindView(R.id.tipBar)
    SeekBar tipBar;
    @BindView(R.id.percentageTextView)
    TextView percentageTextView;

    @BindView(R.id.tipFlexTextView)
    TextView tipFlexTextView;
    @BindView(R.id.totalTextView)
    TextView totalTextView;

    private Double bill;
    private int percentage;

    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Butterknife.bind(this);

        calculator = new Calculator();
        bill = 0.0;

        billEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                bill = Double.parseDouble(billEditText.getText().toString());
                calculateTips();
            }
        });

        tipBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percentage = progress;
                percentageTextView.setText(progress + "%");
                calculateFlexTip();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void calculateTips (){
        Double[] tips = calculator.calculateTips(bill);
        tip10TextView.setText("" + (bill + tips[0]));
        tip15TextView.setText("" + (bill + tips[1]));
        tip20TextView.setText("" + (bill + tips[2]));

    }

    private void calculateFlexTip(){
        Double tip = calculator.calculateFlexTip(bill, percentage);
        tipFlexTextView.setText("Custom tip: " + tip);
        totalTextView.setText("Total with custom tip: " + (bill + tip));
    }
}
