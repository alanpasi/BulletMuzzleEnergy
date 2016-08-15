package com.alanpasi.bulletmuzzleenergy;


import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity  {

    Toolbar mToolbar;

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

        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);

        etMass = (EditText) findViewById(R.id.etMass);
        etVelocity = (EditText) findViewById(R.id.etVelocity);
        tvEnergyResult = (TextView) findViewById(R.id.tvEnergyResult);

        etMass.addTextChangedListener(textWatcher);
        etVelocity.addTextChangedListener(textWatcher);

        if (savedInstanceState != null) {
            tvEnergyResult.setText(savedInstanceState.getString(STATE_ENERGY));
//            Toast.makeText(MainActivity.this, "savedInstanceState é != null", Toast.LENGTH_SHORT).show();
        }

    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (etMass.getText().toString().length() > 0 && etVelocity.getText().toString().length() > 0) {
                CalculateEnergy();
            }
            else{
                tvEnergyResult.setText(null);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View view = findViewById(R.id.main_toolbar);
        Snackbar.make(view, item.getTitle(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        Toast.makeText(this, "Menu selecionado " + item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    public void CalculateEnergy() {
        double mass;
        double velocity;
        double result;

        mass = Double.parseDouble(etMass.getText().toString());
        velocity = Double.parseDouble(etVelocity.getText().toString());

        result = ((1d / 2d) * (mass * (velocity * velocity))) * FOOT_POUND_FORCE;

//        tvEnergyResult.setText(etMass.getText().toString());

        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        tvEnergyResult.setText(decimalFormat.format(result));
    }
}
