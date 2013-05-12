package com.thenoviceoof.pandsievr;

import java.io.IOException;
import java.io.InputStream;

import com.evernote.client.android.EvernoteSession;

import android.app.Activity;
import android.content.Intent;
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
		String[] keys = Utils.getKeys(getAssets());
		if(keys.length == 0) {
			Log.e("Evernote", "API keys not found");
			// also let the user know there's an error
			Toast t = Toast.makeText(getApplicationContext(), "Evernote configured incorrectly", Toast.LENGTH_SHORT);
			t.show();
			return;
		}
		String consumer_key = keys[0];
		String consumer_secret = keys[1];
		// try to authenticate
		EvernoteSession mEvernoteService = EvernoteSession.getInstance(this, consumer_key, consumer_secret, EVERNOTE_SERVICE);
		mEvernoteService.authenticate(this);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) {
		// Update UI when oauth activity returns result
		case EvernoteSession.REQUEST_CODE_OAUTH:
			if (resultCode == Activity.RESULT_OK) {
				// check: if successful, route back to note and post
				finish();
			} else {
				Toast t = Toast.makeText(getApplicationContext(), "Evernote could not authenticate", Toast.LENGTH_SHORT);
				t.show();
			}
			break;
		}
	}
}
