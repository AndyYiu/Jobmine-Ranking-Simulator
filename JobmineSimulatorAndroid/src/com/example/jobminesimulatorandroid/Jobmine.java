package com.example.jobminesimulatorandroid;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Jobmine extends Activity {

	Button match, addinfo;
	EditText name, trank, yrank;
	TextView workPlace;
	ArrayList<Jobs> list = new ArrayList<Jobs>();
	int id = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jobmine);
		match = (Button) findViewById(R.id.match_button);
		addinfo = (Button) findViewById(R.id.add_button);
		name = (EditText) findViewById(R.id.company_name);
		trank = (EditText) findViewById(R.id.trank);
		yrank = (EditText) findViewById(R.id.yrank);
		workPlace = (TextView) findViewById(R.id.match_text);
		
		addinfo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String n = name.getText().toString();
				String tr = trank.getText().toString();
				String yr = yrank.getText().toString();
				
				if(!n.equals("") && !tr.equals("") && !yr.equals("")){
					addToList(id, n, Integer.parseInt(tr), Integer.parseInt(yr));
					name.setText("");
					trank.setText("");
					yrank.setText("");
				} else {
					workPlace.setText("Empty Fields!");
				}
			}
		});
		
		match.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doMagic(list);
				name.setText("");
				trank.setText("");
				yrank.setText("");
			}
		});
		
	}

	private void addToList(int id, String name, int trank, int yrank){
			list.add(new Jobs(id, name, trank, yrank));
			id++;
	}
	
	private void doMagic(ArrayList<Jobs> jobs){
		if(!jobs.isEmpty()){
			int jobId = 0;
			int lowest = score(jobs.get(0));
			int jobScore;
			Random random = new Random();
		
			ArrayList<Integer> idList = new ArrayList<Integer>();
			idList.add(jobId);
		
			for(int i = 1; i < jobs.size(); i++){
				jobScore = score(jobs.get(i));
				if(jobScore < lowest){
					lowest = jobScore;
					jobId = i;
					idList.clear();
					idList.add(jobId);
				} else if(jobScore == lowest){
					jobId = i;
					idList.add(jobId);
				}
			}		
			jobId = idList.get(random.nextInt(idList.size()));
			workPlace.setText(jobs.get(jobId).name);
		} else {
			workPlace.setText("NO INFO!");
		}
	}
	
	public static int score(Jobs jobs){
		int jobScore = jobs.trank + jobs.yrank;
		return jobScore;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_jobmine, menu);
		return true;
	}

}
