package com.example.internalstorage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoadData extends Activity 
{
	//Define and Declare button and edittext used 
	EditText loadusername_et, loadpassword_et;
	Button loaddata_b,savedata_b;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loaddata);
		//Refer button and EditText from XML
		loadpassword_et = (EditText) findViewById(R.id.editText1);
		loadusername_et = (EditText) findViewById(R.id.editText2);
		loaddata_b = (Button) findViewById(R.id.button1);
		savedata_b = (Button) findViewById(R.id.button2);
		//To load Data from SaveData Activity
		loaddata_b.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				try 
				{
					FileInputStream fis = openFileInput("myfile.txt");
					//FileInputStream gives method to write data
					int read = -1;
					//Since we append data that is read from file we use Stringbuffer
					StringBuffer mybuffer = new StringBuffer();
					//Read data untill the byte value become -1
					while ((read = fis.read()) != -1) 
					{
						//Typecast the data read from file to char type since data read is in byte form and 
						//we stop reading till the byte value becomes -1
						mybuffer.append((char) read);
					}
					//Storing the text got from buffer 
					String loadusername = mybuffer.substring(0,mybuffer.indexOf(""));			
					String loadpass = mybuffer.substring(mybuffer.indexOf(""));
					//Set the read value to Edittext 
					loadusername_et.setText(loadusername);
					loadpassword_et.setText(loadpass);

				} 
				//When we try to load a file the file may deleted , so that we cannot open it to handle that FileNotFoundException
				catch (FileNotFoundException e) 
				{
					// TODO Auto-generated catch block
					Log.d("Excetion is ", e.toString());
				}
				//While reading file , it is not necessary that the file will be written successfully problem may arise like phone will be restarted and net disconnect so we to handle it using IOException
				//So we have surround write block with IOException		
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					Log.d("Excetion is ", e.toString());

				}

			}
		});
		//To go back to savedata Activity
		savedata_b.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				Intent i= new Intent(LoadData.this,SaveData.class);
				startActivity(i);
			}
		});

	}
}
