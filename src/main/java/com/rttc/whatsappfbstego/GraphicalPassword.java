package com.rttc.whatsappfbstego;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class GraphicalPassword extends Activity 
{
	//SendMail sm;
	private TextView mChoice;
    private RadioGroup radioGroup1,radioGroup2,radioGroup3,radioGroup4;
    //private RadioButton localSupport;
    //private RadioButton webSupport;
    
    private Button 	   goButton,btn_FP;
    DBAdapterGP dbGrPswd;
    String rad1="1",rad2="3",rad3="1",rad4="1",id="";
    
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphical_password);
        dbGrPswd = new DBAdapterGP(this);
        radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
        radioGroup2 = (RadioGroup)findViewById(R.id.radioGroup2);
        radioGroup3 = (RadioGroup)findViewById(R.id.radioGroup3);
        radioGroup4 = (RadioGroup)findViewById(R.id.radioGroup4);
        
        dbGrPswd.open();
        
        goButton = (Button)findViewById(R.id.btnEnt);
        btn_FP = (Button) findViewById(R.id.btnFP);
        
        btn_FP.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		
        	Intent ifp = new Intent(GraphicalPassword.this,ForgotPassword.class);
			startActivity(ifp);
        	       		
        	}
        	      	
        });
        
        goButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				//dbGrPswd.deleteRecord();
				Cursor cur = dbGrPswd.getAllRecords();
				//cur.moveToFirst();
				
				if(cur.getCount()==0)
				{
					Intent inew = new Intent(GraphicalPassword.this,HomeActivity.class);
					startActivity(inew);
				}
				else{
					//Cursor cur = dbGrPswd.getAllRecords();
					//cur.moveToFirst();
				
					
					rad1 = cur.getString(cur.getColumnIndex("arr1"));
					rad2 = cur.getString(cur.getColumnIndex("arr2"));
					rad3 = cur.getString(cur.getColumnIndex("arr3"));
					rad4 = cur.getString(cur.getColumnIndex("arr4"));

				if(Integer.parseInt(rad1)==radioGroup1.getCheckedRadioButtonId()
						&&Integer.parseInt(rad2)==radioGroup2.getCheckedRadioButtonId()
						&&Integer.parseInt(rad3)==radioGroup3.getCheckedRadioButtonId()
						&&Integer.parseInt(rad4)==radioGroup4.getCheckedRadioButtonId()
						
						)
				{
					
					Intent i = new Intent(GraphicalPassword.this, HomeActivity.class) ;
					startActivity(i);
					
				}
				else 
				{
					Intent i = new Intent(GraphicalPassword.this,GraphicalPassword.class) ;
					startActivity(i);
					Toast.makeText(GraphicalPassword.this, "Invalid Graphical Password!!!!!!", Toast.LENGTH_LONG).show();
				}
				cur.close();
				dbGrPswd.close();
				}
			}
		});
        
        
    }
 
    public void onBackPressed() {
        	    return; //Do nothing!    	 
    }
  
	


  
    
}