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
