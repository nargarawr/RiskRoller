package com.example.riskroller;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Method to enforce the number of defenders is at most the number of
	 * attackers
	 * 
	 * @param radioButton
	 *            the radio button being pressed
	 */
	public void validDefenderNumber(View radioButton) {

		switch (radioButton.getId()) {
		case R.id.rd_num_attackers_one:

			RadioButton rdOneDefender = (RadioButton) findViewById(R.id.rd_num_defenders_one);
			rdOneDefender.setChecked(true);
			break;
		case R.id.rd_num_defenders_two:

			RadioButton rdOneAttacker = (RadioButton) findViewById(R.id.rd_num_attackers_one);
			RadioButton rdTwoAttacker = (RadioButton) findViewById(R.id.rd_num_attackers_two);

			if (rdOneAttacker.isChecked()) {
				rdTwoAttacker.setChecked(true);
			}
			break;
		}

	}

	/**
	 * Method that generates the die rolls and displays the results as necessary
	 * 
	 * @param button
	 *            the roll die button that was pressed
	 */
	public void rollDie(View button) {

		TextView attackersDie = (TextView) findViewById(R.id.tw_attackers_die);
		TextView defendersDie = (TextView) findViewById(R.id.tw_defenders_die);
		
		attackersDie.setText("");
		defendersDie.setText("");
		
		int[] attackerRollsArray = createAttackerRollsArray();
		int[] defenderRollsArray = createDefenderRollsArray();
		
		Random randomGenerator = new Random();
		
		for (int i = 0; i < attackerRollsArray.length; i++) {
			if (i < defenderRollsArray.length) {
				defenderRollsArray[i] = ((Math.abs(randomGenerator.nextInt()) % 6)) + 1;
				defendersDie.setText(defenderRollsArray[i] + " " + defendersDie.getText());
			}
			attackerRollsArray[i] = ((Math.abs(randomGenerator.nextInt()) % 6)) + 1;
			attackersDie.setText(attackerRollsArray[i] + " " + attackersDie.getText());
		}
	}
	
	public int[] createDefenderRollsArray(){
		int num_defenders = 0;
		
		RadioGroup rdgrp_defender = (RadioGroup) findViewById(R.id.rdgrp_num_defenders);
		
		RadioButton selectedAttackers = (RadioButton) findViewById(rdgrp_defender
				.getCheckedRadioButtonId());
		
		num_defenders = Integer.valueOf((String) selectedAttackers.getText());
		
		return new int [num_defenders];
	}
	
	public int[] createAttackerRollsArray(){
		int num_attackers = 0;
		
		RadioGroup rdgrp_attacker = (RadioGroup) findViewById(R.id.rdgrp_num_attackers);
		
		RadioButton selectedAttackers = (RadioButton) findViewById(rdgrp_attacker
				.getCheckedRadioButtonId());
		
		num_attackers = Integer.valueOf((String) selectedAttackers.getText());
			
		return new int [num_attackers];
	}

}
