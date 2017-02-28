package com.example.sample;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.ActionBar;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

	 private EditText editTextName;
	    private EditText editTextAdd;
	 
	 
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	 
	        editTextName = (EditText) findViewById(R.id.editTextName);
	        editTextAdd = (EditText) findViewById(R.id.editTextAddress);
	 
	 
	    }
	 
	    public void insert(View view){
	        String name = editTextName.getText().toString();
	        String add = editTextAdd.getText().toString();
	 
	        insertToDatabase(name,add);
	    }
	 
	    private void insertToDatabase(String name, String add){
	        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
	            @Override
	            protected String doInBackground(String... params) {
	                String paramUsername = params[0];
	                String paramAddress = params[1];
	 
	              
	                String name = editTextName.getText().toString();
	                String add = editTextAdd.getText().toString();
	 
	                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	                nameValuePairs.add(new BasicNameValuePair("name", name));
	                nameValuePairs.add(new BasicNameValuePair("address", add));
	 
	                try {
	                    HttpClient httpClient = new DefaultHttpClient();
	                    HttpPost httpPost = new HttpPost(
	                            "http://mytollfree.site11.com/insertdata.php");
	                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	 
	                    HttpResponse response = httpClient.execute(httpPost);
	 
	                    HttpEntity entity = response.getEntity();
	 
	          
	                } catch (ClientProtocolException e) {
	 
	                } catch (IOException e) {
	 
	                }
	                return "success";
	            }
	 
	            @Override
	            protected void onPostExecute(String result) {
	                super.onPostExecute(result);
	 
	                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
	                TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
	                textViewResult.setText("Inserted");
	            }
	        }
	        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
	        sendPostReqAsyncTask.execute(name, add);
	    }
	 
	   
	    
	}