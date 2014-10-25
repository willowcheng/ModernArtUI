package com.willowcheng.moderartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	static final String MOMA_URI = "http://www.moma.org/";
	private Builder mBuilder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mBuilder = new AlertDialog.Builder(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.more_information) {
			moreInformationBuilderSet();
			mBuilder.create().show();
		}
		return super.onOptionsItemSelected(item);
	}

	private void moreInformationBuilderSet() {
		// TODO Auto-generated method stub

		mBuilder.setIcon(R.drawable.ic_launcher)
				.setTitle(R.string.dialog_title)
				.setMessage(R.string.dialog_message)
				.setNegativeButton("Visit MOMA",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Intent intent = new Intent(Intent.ACTION_VIEW,
										Uri.parse(MOMA_URI));
								startActivity(intent);
							}
						})
				.setPositiveButton("Not Now",
						new DialogInterface.OnClickListener() {
							public void onClick(final DialogInterface dialog,
									int id) {
								dialog.dismiss();
							}
						});
	}
}
