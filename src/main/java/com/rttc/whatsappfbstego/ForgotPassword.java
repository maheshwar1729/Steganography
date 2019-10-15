package com.rttc.whatsappfbstego;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ForgotPassword extends Activity implements OnItemSelectedListener
{
	private Button btn_FP;
	DBAdapterGP  dbGrPswd;
	String rad1,rad2,rad3,rad4;
	Spinner spn_ques;
	String label;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
          }
        setContentView(R.layout.forgot);
        dbGrPswd = new DBAdapterGP(this);
        spn_ques = (Spinner)findViewById(R.id.spn_question);
        spn_ques.setOnItemSelectedListener(this);
        btn_FP = (Button)findViewById(R.id.btn_forgot);
        dbGrPswd.open();
        loadSpinData();
        btn_FP.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        	
        		TextView dispMsg = (TextView) findViewById(R.id.dispMsgs);	
        		EditText txt_eml = (EditText)findViewById(R.id.txt_email);
				EditText txt_secans = (EditText)findViewById(R.id.txt_secans);
        		Cursor cur = dbGrPswd.getAllRecords();
				cur.moveToFirst();
				
				if(cur.getCount()==0)
				{
				  dispMsg.setText("No Graphical Password Set. Please Set Inside the App");
				}
				else{
					//Cursor cur = dbGrPswd.getAllRecords();
					cur.moveToFirst();
					rad1 = cur.getString(cur.getColumnIndex("arr1"));
					rad2 = cur.getString(cur.getColumnIndex("arr2"));
					rad3 = cur.getString(cur.getColumnIndex("arr3"));
					rad4 = cur.getString(cur.getColumnIndex("arr4"));
					
					if(rad1.equals("2131493009"))
					{
						rad1 ="1";
					}
					if(rad1.equals("2131493010"))
					{
						rad1 ="2";
					}
					if(rad1.equals("2131493011"))
					{
						rad1 ="3";
					}
					if(rad1.equals("2131493012"))
					{
						rad1 ="4";
					}
					
					if(rad2.equals("2131493014"))
					{
						rad2 ="1";
					}
					if(rad2.equals("2131493015"))
					{
						rad2 ="2";
					}
					if(rad2.equals("2131493016"))
					{
						rad2 ="3";
					}
					if(rad2.equals("2131493017"))
					{
						rad2 ="4";
					}
					
					if(rad3.equals("2131493019"))
					{
						rad3 ="1";
					}
					if(rad3.equals("2131493020"))
					{
						rad3 ="2";
					}
					if(rad3.equals("2131493021"))
					{
						rad3 ="3";
					}
					if(rad3.equals("2131493022"))
					{
						rad3 ="4";
					}
					
					if(rad4.equals("2131493024"))
					{
						rad4 ="1";
					}
					if(rad4.equals("2131493025"))
					{
						rad4 ="2";
					}
					if(rad4.equals("2131493026"))
					{
						rad4 ="3";
					}
					if(rad4.equals("2131493027"))
					{
						rad4 ="4";
					}
					
					//dispMsg.setText(rad1+rad2+rad3+rad4+label+" "+cur.getString(cur.getColumnIndex("secques"))+cur.getString(cur.getColumnIndex("secans")));
				
				if(txt_secans.getText().toString().equals(cur.getString(cur.getColumnIndex("secans")))&&label.equals(cur.getString(cur.getColumnIndex("secques")))){
				
					//new SendMail().sendMail(txt_eml.getText().toString(),rad1+rad2+rad3+rad4 );
					dispMsg.setText("Your Password is : "+rad1+rad2+rad3+rad4);
					
				}
				else{
					dispMsg.setText("Enter Correct details. Pls. Check Ur Details");
				}
				
					
					
				}
					
					
					
										
				
				//cur.close();
				//dbGrPswd.close();	
					
        		
        		
        	}
        
        	});
        
	}
	
	private void loadSpinData(){
  	     
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