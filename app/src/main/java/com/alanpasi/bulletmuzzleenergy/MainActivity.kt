package com.alanpasi.bulletmuzzleenergy


import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private var mToolbar: Toolbar? = null

    private var etMass: EditText? = null
    private var etVelocity: EditText? = null
    private var tvEnergyResult: TextView? = null

    private var textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

        }

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            if (etMass!!.text.toString().length > 0 && etVelocity!!.text.toString().length > 0) {
                CalculateEnergy()
            } else {
                tvEnergyResult!!.text = null
            }

        }

        override fun afterTextChanged(editable: Editable) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById(R.id.main_toolbar) as Toolbar
        setSupportActionBar(mToolbar)

        etMass = findViewById(R.id.etMass) as EditText
        etVelocity = findViewById(R.id.etVelocity) as EditText
        tvEnergyResult = findViewById(R.id.tvEnergyResult) as TextView

        etMass!!.addTextChangedListener(textWatcher)
        etVelocity!!.addTextChangedListener(textWatcher)

        if (savedInstanceState != null) {
            tvEnergyResult!!.text = savedInstanceState.getString(STATE_ENERGY)
            //            Toast.makeText(MainActivity.this, "savedInstanceState é != null", Toast.LENGTH_SHORT).show();
        }

    }

    override fun onSaveInstanceState(savedInstantState: Bundle) {

        savedInstantState.putString(STATE_ENERGY, tvEnergyResult!!.text.toString())

        super.onSaveInstanceState(savedInstantState)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val view = findViewById(R.id.main_toolbar)
        Snackbar.make(view, item.title, Snackbar.LENGTH_LONG).setAction("Action", null).show()
        Toast.makeText(this, "Menu selecionado " + item.title, Toast.LENGTH_SHORT).show()
        return super.onOptionsItemSelected(item)
    }

    fun CalculateEnergy() {
        val mass: Double
        val velocity: Double
        val result: Double

        mass = java.lang.Double.parseDouble(etMass!!.text.toString())
        velocity = java.lang.Double.parseDouble(etVelocity!!.text.toString())

        result = 1.0 / 2.0 * (mass * (velocity * velocity)) * FOOT_POUND_FORCE

        //        tvEnergyResult.setText(etMass.getText().toString());

        val decimalFormat = DecimalFormat("#,###.##")
        tvEnergyResult!!.text = decimalFormat.format(result)
    }

    companion object {

        private val STATE_ENERGY = "energyResult"

        private val ACCELERATION_DUE_TO_GRAVITY = 7000.0
        private val FOOT_POUND_FORCE = 1.0 / (ACCELERATION_DUE_TO_GRAVITY * 32.13)
    }
}
