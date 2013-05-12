package com.thenoviceoof.pandsievr;

import com.evernote.client.android.EvernoteSession;
import com.evernote.client.android.EvernoteUtil;
import com.evernote.client.android.OnClientCallback;
import com.evernote.edam.type.Note;
import com.evernote.thrift.transport.TTransportException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NoteDialog extends Activity {
	private static final EvernoteSession.EvernoteService EVERNOTE_SERVICE = EvernoteSession.EvernoteService.SANDBOX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_dialog);
	}

	public void saveMessage(View view) {
		// check if we're logged in yet
		String[] s = Utils.getKeys(getAssets());
		String consumer_key = s[0];
		String consumer_secret = s[1];
		EvernoteSession mEvernoteService = EvernoteSession.getInstance(this, consumer_key, consumer_secret, EVERNOTE_SERVICE);
		if(mEvernoteService.isLoggedIn()) {
			// upload the note
			EditText noteText = (EditText)findViewById(R.id.note);
			String contents = noteText.getText().toString();
			Note note = new Note();
			note.setTitle("Untitled");
			note.setContent(EvernoteUtil.NOTE_PREFIX + contents + EvernoteUtil.NOTE_SUFFIX);
			try {
				final Activity noteDialog = this;
				mEvernoteService.getClientFactory().createNoteStoreClient().createNote(note, new OnClientCallback<Note>() {
					@Override
					public void onSuccess(final Note data) {
						Toast.makeText(getApplicationContext(), "Thought uploaded", Toast.LENGTH_SHORT).show();
						// delete the note
						// ...
						noteDialog.finish();
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
