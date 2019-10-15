package com.rttc.whatsappfbstego;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class NewGrPassword extends Activity implements OnItemSelectedListener {
	Button btnSet;
	//EditText etNumber;
	private RadioGroup radioGroup1,radioGroup2,radioGroup3,radioGroup4;
	DBAdapterGP dbGrPswd;
	Spinner spn_ques;
	EditText txt_answer;
	String label;
	boolean flag;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grnewpassword);
		dbGrPswd = new DBAdapterGP(this);
		btnSet = (Button) findViewById(R.id.btnSetGrPswd);
		//etNumber = (EditText) findViewById(R.id.etBlockNumber);
		radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
        radioGroup2 = (RadioGroup)findViewById(R.id.radioGroup2);
        radioGroup3 = (RadioGroup)findViewById(R.id.radioGroup3);
        radioGroup4 = (RadioGroup)findViewById(R.id.radioGroup4);
        spn_ques = (Spinner)findViewById(R.id.spn_ques);
        spn_ques.setOnItemSelectedListener(this);
        txt_answer =  (EditText)findViewById(R.id.txt_ans);
		dbGrPswd.open();
		loadSpinnerData();
		flag = false;
		btnSet.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				    dbGrPswd.deleteRecord();
					dbGrPswd
							.insertRecord(radioGroup1.getCheckedRadioButtonId(),radioGroup2.getCheckedRadioButtonId() ,radioGroup3.getCheckedRadioButtonId(),radioGroup4.getCheckedRadioButtonId(),label,txt_answer.getText().toString());
					displayMessage("Graphical Password Set for Your App");
					Intent i = new Intent(NewGrPassword.this, HomeActivity.class);
					startActivity(i);
				
				//etNumber.setText("");
			}
		});
		dbGrPswd.open();
	}

	public void displayMessage(String msg) {
		Toast.makeText(NewGrPassword.this, msg, Toast.LENGTH_SHORT).show();
	}
	
	private void loadSpinnerData() {
     
        String[] lables = {"What is your favorite Hobby?","What is your first school?","What is your favourite number?","What is your favourite book?"};
 
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables );
 
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        spn_ques.setAdapter(dataAdapter);
    }
 
    public void onItemSelected(AdapterView<?> parent, View view, int position,
            long id) {
        // On selecting a spinner item
         label = parent.getItemAtPosition(position).toString();
 
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();
 
    }
 
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
 
    }
	
	
	
}
