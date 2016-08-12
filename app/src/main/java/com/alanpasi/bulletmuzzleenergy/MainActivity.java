package com.alanpasi.bulletmuzzleenergy;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etMass;
    private EditText etVelocity;
    private TextView tvEnergyResult;

    private static final String STATE_ENERGY = "energyResult";

    private static final double ACCELERATION_DUE_TO_GRAVITY = 7000d;
    private static final double FOOT_POUND_FORCE = (1d / (ACCELERATION_DUE_TO_GRAVITY * 32.13d));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMass = (EditText) findViewById(R.id.etMass);
        etVelocity = (EditText) findViewById(R.id.etVelocity);
        tvEnergyResult = (TextView) findViewById(R.id.tvEnergyResult);


        if (savedInstanceState != null) {
            tvEnergyResult.setText(savedInstanceState.getString(STATE_ENERGY));
//            Toast.makeText(MainActivity.this, "savedInstanceState é != null", Toast.LENGTH_SHORT).show();
        }

        tvEnergyResult.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        CalculateEnergy();

//        Toast.makeText(MainActivity.this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstantState) {

        savedInstantState.putString(STATE_ENERGY, tvEnergyResult.getText().toString());

        super.onSaveInstanceState(savedInstantState);
    }

    @Override
    public void onClick(View view) {

        CalculateEnergy();

    }

    public void CalculateEnergy() {
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
