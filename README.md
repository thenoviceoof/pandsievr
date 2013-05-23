pandsievr
================================================================================
A little Android project to jot notes into Evernote quickly.


SETUP
--------------------------------------------------------------------------------
Import the project into Eclipse. You'll need to also have the
evernote-sdk-android package
(https://github.com/evernote/evernote-sdk-android), which the project
by default assumes is at `../evernote-sdk-android` from this project.

Then, create an `assets/EvernoteAPIKeys.txt` and put your CONSUMER_KEY
on line 1, and CONSUMER_SECRET on line 2.

Currently this App is in sandbox mode: at the very least, I need to
get a sweet logo, and then I'll probably flip the switch from Sandbox
to production.


BUGS
--------------------------------------------------------------------------------
This project is on github! Make a github issue if there's a bug.

Tested with a Galaxy S (1) with Android 4.1.2


TODO
--------------------------------------------------------------------------------
 - Make a queue + service that can handle interruptions in the middle
   of uploads, and queue up uploads in the middle of a subway ride.
 - Remove the superfluous EvernoteLogin activity, and just open up an
   authentication thing if you're not logged in


NOTES
--------------------------------------------------------------------------------
This was a weekend project that I wanted to see done (see my previous
pensievr project, which is in dire need of work), and as a gateway app
for android development.


LICENSE
--------------------------------------------------------------------------------
Copyright (c) &lt;2013&gt; &lt;thenoviceoof&gt;

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
