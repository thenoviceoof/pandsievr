/*
 * Copyright (c) <2013> <thenoviceoof>
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.thenoviceoof.pandsievr;

import com.evernote.client.android.EvernoteSession;
import com.evernote.client.android.EvernoteUtil;
import com.evernote.client.android.OnClientCallback;
import com.evernote.edam.type.Note;
import com.evernote.thrift.transport.TTransportException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class NoteDialog extends Activity {
	private static final EvernoteSession.EvernoteService EVERNOTE_SERVICE = EvernoteSession.EvernoteService.SANDBOX;

	public EditText noteText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_dialog);
		noteText = (EditText)findViewById(R.id.note);
		// populate the text
		SharedPreferences prefs = getPreferences(MODE_PRIVATE);
		String note = prefs.getString("note", "");
		noteText.setText(note);
		// get us a keyboard right away
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
	}

	public void onStop() {
		super.onStop();
		// save the text
		SharedPreferences prefs = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor prefsEditor = prefs.edit();
		String text = noteText.getText().toString();
		if(text.length() > 0) {
			Toast t = Toast.makeText(getApplicationContext(), "Saving draft...", Toast.LENGTH_SHORT);
			t.show();
		}
		prefsEditor.putString("note", text);
		prefsEditor.commit();
	}

	public void saveMessage(View view) {
		// check if we're logged in yet
		String[] s = Utils.getKeys(getAssets());
		String consumer_key = s[0];
		String consumer_secret = s[1];
		EvernoteSession mEvernoteService = EvernoteSession.getInstance(this, consumer_key, consumer_secret, EVERNOTE_SERVICE);
		if(mEvernoteService.isLoggedIn()) {
			// upload the note
			String contents = noteText.getText().toString();
			Note note = new Note();
			note.setTitle("Untitled");
			note.setContent(EvernoteUtil.NOTE_PREFIX + contents + EvernoteUtil.NOTE_SUFFIX);
			try {
				final Activity noteDialog = this;
				Toast t = Toast.makeText(getApplicationContext(), "Trying to upload thought...", Toast.LENGTH_LONG);
				t.show();
				mEvernoteService.getClientFactory().createNoteStoreClient().createNote(note, new OnClientCallback<Note>() {
					@Override
					public void onSuccess(final Note data) {
						Toast.makeText(getApplicationContext(), "Thought uploaded", Toast.LENGTH_SHORT).show();
						// delete the note
						EditText t = (EditText)noteDialog.findViewById(R.id.note);
						t.setText("");
					}

					@Override
					public void onException(Exception exception) {
						Log.e("NoteDialog", "Error uploading note", exception);
						Toast.makeText(getApplicationContext(), "Thought couldn't be uploaded", Toast.LENGTH_SHORT).show();
					}
				});
			} catch (TTransportException exception) {
				Log.e("NoteDialog", "Error uploading note", exception);
				Toast.makeText(getApplicationContext(), "Thought couldn't be uploaded", Toast.LENGTH_SHORT).show();
			}
		} else {
			// save the note locally
			// ...
			// have the user login with Evernote
			mEvernoteService.authenticate(this);
			Intent intent = new Intent(this, EvernoteLogin.class);
			startActivity(intent);
		}
	}
}
