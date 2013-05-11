package com.thenoviceoof.pandsievr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NoteDialog extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_dialog);
	}

	public void saveMessage(View view) {
		Intent intent = new Intent(this, EvernoteLogin.class);
		startActivity(intent);
	}
}
