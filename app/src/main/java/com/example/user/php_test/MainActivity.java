package com.example.user.php_test;


import android.annotation.TargetApi;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

	String st = "HELLO";
	TextView textview;
	StringBuilder jsonHtml;


	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */
	private GoogleApiClient client;

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		textview = (TextView) findViewById(R.id.txt1);
		textview.setText(st);

		Button btn2 = (Button) findViewById(R.id.btn);
		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//new phpDown().execute("http://oioi9i.synology.me/select_test.php");
				new phpDown().execute("http://oioi9i.synology.me/select_test.php");
			}
		});

		Button btn1 = (Button) findViewById(R.id.btn1);
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new phpUP().execute("http://oioi9i.synology.me/insert_test.php");
			}
		});
		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
	}




	@Override
	public void onStart() {
		super.onStart();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client.connect();
		Action viewAction = Action.newAction(
				Action.TYPE_VIEW, // TODO: choose an action type.
				"Main Page", // TODO: Define a title for the content shown.
				// TODO: If you have web page content that matches this app activity's content,
				// make sure this auto-generated web page URL is correct.
				// Otherwise, set the URL to null.
				Uri.parse("http://host/path"),
				// TODO: Make sure this auto-generated app deep link URI is correct.
				Uri.parse("android-app://com.example.user.php_test/http/host/path")
		);
		AppIndex.AppIndexApi.start(client, viewAction);
	}

	@Override
	public void onStop() {
		super.onStop();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		Action viewAction = Action.newAction(
				Action.TYPE_VIEW, // TODO: choose an action type.
				"Main Page", // TODO: Define a title for the content shown.
				// TODO: If you have web page content that matches this app activity's content,
				// make sure this auto-generated web page URL is correct.
				// Otherwise, set the URL to null.
				Uri.parse("http://host/path"),
				// TODO: Make sure this auto-generated app deep link URI is correct.
				Uri.parse("android-app://com.example.user.php_test/http/host/path")
		);
		AppIndex.AppIndexApi.end(client, viewAction);
		client.disconnect();
	}

	private class phpDown extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... arg0) {
			String line_ex = "";
			try {
				URL url = new URL(arg0[0]);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();

				if (conn != null) {
					conn.setConnectTimeout(10000);
					conn.setUseCaches(false);
					conn.setDoInput(true);
					conn.setDoOutput(true);

					if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
						BufferedReader br = new BufferedReader(new InputStreamReader
								(conn.getInputStream(), "UTF-8"));
						line_ex = br.readLine();
						br.close();
					}
					conn.disconnect();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return line_ex;
		}

		protected void onPostExecute(String str) {
			textview.setText(str);
		}
	}

	private class phpUP extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... arg0) {
			String line_ex = "";
			try {
				URL url = new URL(arg0[0]);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();

				if (conn != null) {
					conn.setConnectTimeout(10000);
					conn.setUseCaches(false);
					conn.setDoInput(true);
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("charset", "UTF-8");

					//보내주는 부분
					String id = "한글";
					int pw = 1234;
					StringBuffer buffer = new StringBuffer();
					buffer.append("id").append("=").append(id).append("&");
					buffer.append("pw").append("=").append(pw);

					OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
					PrintWriter writer = new PrintWriter(out);
					writer.write(buffer.toString());
					writer.flush();
					writer.close();
					out.close();

					//읽어오는 부분
					BufferedReader br = new BufferedReader(new InputStreamReader
							(conn.getInputStream()));
					line_ex = br.readLine();
					br.close();
					conn.disconnect();

				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return line_ex;
		}

		protected void onPostExecute(String str) {
			textview.setText(str);
		}
	}


}