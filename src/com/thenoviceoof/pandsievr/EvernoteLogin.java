package com.thenoviceoof.pandsievr;

import java.io.IOException;
import java.io.InputStream;

import com.evernote.client.android.EvernoteSession;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class EvernoteLogin extends Activity {
	private static final EvernoteSession.EvernoteService EVERNOTE_SERVICE = EvernoteSession.EvernoteService.SANDBOX;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evernote_login_dialog);
	}

	public void loginEvernote(View view) {
		String consumer_key = "";
		String consumer_secret = "";
		// fetch the API keys
		try {
			InputStream in = getAssets().open("EvernoteAPIKeys.txt");
			StringBuffer stream = new StringBuffer();
			byte[] b = new byte[1024];
			for(int n; (n = in.read(b)) != -1;) {
				stream.append(new String(b, 0, n));
			}
			String[] lines = stream.toString().split("\\n");
			consumer_key = lines[0];
			consumer_secret = lines[1];
		} catch (IOException e) {
			Log.e("Evernote", "API keys not found");
			// also let the user know there's an error
			Toast t = Toast.makeText(getApplicationContext(), "Evernote configured incorrectly", Toast.LENGTH_SHORT);
			t.show();
			return;
		}
		// try to authenticate
		EvernoteSession mEvernoteService = EvernoteSession.getInstance(this, consumer_key, consumer_secret, EVERNOTE_SERVICE);
		mEvernoteService.authenticate(this);
		// check: if successful, route back to note and post
	}
}
