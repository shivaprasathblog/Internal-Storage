package com.example.internalstorage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SaveData extends Activity
 {

	/**
	 * Data stored in internal application can be accessed by the applicaton
	 * only other applications cannot acess it
	 * 
	 * If the app is unistalled the data stored for that app will be deleted
	 * Example : if Opera browser app is installed and the datastored(bookmarks)
	 * in Opera is internally stored if we uninstall Opera all the data
	 * associated(bookmarks) will be atomatically deleted other app eg chrome
	 * cannot access the bookmarks of chrome unless we explicitly give
	 * permission in Opera to share data with chrome
	 * 
	 * MODE_PRIVATE is given to give access only to the app MODE-APPEND is used
	 * add data to the previous data openFileOutput is used to write data in
	 * binary form to the file system openFileInput is used to read data from
	 * file
	 **/
	
	//Define Button and Edittext 
	Button save_b,showdatasaved_b;
	EditText username_et,password_et;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.savedata);
		//Refer button and EditText from XML
		save_b=(Button)findViewById(R.id.button1);
		showdatasaved_b=(Button)findViewById(R.id.button2);
		username_et=(EditText)findViewById(R.id.editText1);
		password_et=(EditText)findViewById(R.id.editText2);
		
		//To Save Data internally on button click
		save_b.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				String usernmaetxt=username_et.getText().toString();
				String passwordtxt=password_et.getText().toString();
				//openFileOutput(name, mode)
				//FileOutputStream gives method to write data
				FileOutputStream fos=null;
				File myfile=null;
				//fos gives methods to write data we can write data by getting one byte ata a time or bunch of data at a time using byte array
				//to convert bytes to data tostring()
				//fos.write(byte[]buffer)
				//fos.write(int one byte) 
				//fos.write(buffer, byteOffset, byteCount)
				try 
				{
					//When we try to open a file the file may deleted , so that we cannot open it to handle that FileNotFoundException
					fos = openFileOutput("myfile.txt",Context.MODE_PRIVATE);
					//While writing file , it is not necessary that the file will be written successfully problem may arise like phone will be restarted and net disconnect so we to handle it using IOException
					//So we have surround write block with IOException				
					fos.write( usernmaetxt.getBytes());
					fos.write( passwordtxt.getBytes());
					//To get in which folder our file is written
					myfile=getFilesDir();
				}
				catch (FileNotFoundException e)
				{
					// TODO Auto-generated catch block
					//e.printStackTrace();
					Log.d("Excetion is ", e.toString());
				}
					
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					//e.printStackTrace();
					Log.d("Excetion is ", e.toString());
				}
				finally
				{
					try 
					{
						fos.close();
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						//Log.d("Excetion is ", e.toString());
						e.printStackTrace();
					}
				} 
				//To show the folder our file is stored internally 
				Toast.makeText(SaveData.this,"Sucessfully saved"+myfile+"myfile.txt",Toast.LENGTH_LONG).show();
			}
			});
		showdatasaved_b.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent i= new Intent(SaveData.this,LoadData.class);
				startActivity(i);
				
			}
		});
	}


}
