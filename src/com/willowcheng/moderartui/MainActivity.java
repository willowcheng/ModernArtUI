package com.willowcheng.moderartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	static final String MOMA_URI = "http://www.moma.org/";
	private static DialogFragment mDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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

			mDialog = AlertDialogFragment.newInstance();
			mDialog.show(getFragmentManager(), "Alert");
		}
		return super.onOptionsItemSelected(item);
	}

	public static class AlertDialogFragment extends DialogFragment {

		public static AlertDialogFragment newInstance() {
			return new AlertDialogFragment();
		}

		// Build AlertDialog using AlertDialog.Builder
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {

			return new AlertDialog.Builder(getActivity())
					.setTitle(R.string.dialog_title)
					.setMessage(R.string.dialog_message).setIcon(R.drawable.ic_launcher)

					// User cannot dismiss dialog by hitting back button
					.setCancelable(false)

					// Set up No Button
					.setNegativeButton("Visit MOMA",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									Intent intent = new Intent(
											Intent.ACTION_VIEW, Uri
													.parse(MOMA_URI));
									startActivity(intent);
								}
							})

					// Set up Yes Button
					.setPositiveButton("Not Now",
							new DialogInterface.OnClickListener() {
								public void onClick(
										final DialogInterface dialog, int id) {
									mDialog.dismiss();
								}
							}).create();

		}
	}
}
