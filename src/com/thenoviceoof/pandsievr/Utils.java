package com.thenoviceoof.pandsievr;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.util.Log;

public class Utils {
	static public String[] getKeys(AssetManager assets) {
		String consumer_key = "";
		String consumer_secret = "";
		// fetch the API keys
		try {
			InputStream in = assets.open("EvernoteAPIKeys.txt");
			StringBuffer stream = new StringBuffer();
			byte[] b = new byte[1024];
			for(int n; (n = in.read(b)) != -1;) {
				stream.append(new String(b, 0, n));
			}
			String[] lines = stream.toString().split("\\n");
			consumer_key = lines[0];
			consumer_secret = lines[1];
		} catch (IOException e) {
			String[] s = new String[0];
			return s;
		}
		String[] s = new String[2];
		s[0] = consumer_key;
		s[1] = consumer_secret;
		return s;
	}
}
