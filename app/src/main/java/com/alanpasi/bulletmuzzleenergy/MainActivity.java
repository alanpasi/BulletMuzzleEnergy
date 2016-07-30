package com.alanpasi.bulletmuzzleenergy;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etMass;
    EditText etVelocity;
    TextView tvEnergyResult;

    final double ACCELERATION_DUE_TO_GRAVITY = 7000d;
    final double FOOT_POUND_FORCE = (1d / (ACCELERATION_DUE_TO_GRAVITY * 32.13d));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMass = (EditText) findViewById(R.id.etMass);
        etVelocity = (EditText) findViewById(R.id.etVelocity);
        tvEnergyResult = (TextView) findViewById(R.id.tvEnergyResult);

        tvEnergyResult.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        double mass;
        double velocity;
        double result;

        mass = Double.parseDouble(etMass.getText().toString());
        velocity = Double.parseDouble(etVelocity.getText().toString());

        result = ((1d/2d) * (mass * (velocity * velocity))) * FOOT_POUND_FORCE;

//        tvEnergyResult.setText(etMass.getText().toString());

        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        tvEnergyResult.setText(decimalFormat.format(result));

    }
}
