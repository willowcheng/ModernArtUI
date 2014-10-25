package com.willowcheng.moderartui;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	static final String MOMA_URI = "http://www.moma.org/";
	private Builder mBuilder;
	private SeekBar seekBar;
	private LinearLayout horizontalLayout;
	private int countLayout;
	private TextView textView;
	private Random randInt;
	private int randomWeight;
	private LinearLayout rootLayout;
	static final int colors[] = { R.color.red_300, R.color.blue_300,
			R.color.yellow_400, R.color.green_200, R.color.grey_300, };
	static final String TAG = "Hello";
	private int color = 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		randInt = new Random();

		mBuilder = new AlertDialog.Builder(this);
		seekBar = (SeekBar) findViewById(R.id.mSeekBar);
		rootLayout = (LinearLayout) findViewById(R.id.rootlayout);
		countLayout = rootLayout.getChildCount();
		for (int i = 0; i < countLayout - 1; i++) {
			horizontalLayout = (LinearLayout) rootLayout.getChildAt(i);
			// do something with your child element

			for (int j = 0; j < 6; j++) {
				randomWeight = randInt.nextInt(3) + 1;
				textView = (TextView) getLayoutInflater().inflate(
						R.layout.color_item, horizontalLayout, false);

				textView.setBackgroundColor(randomColor());
				textView.setLayoutParams(new LinearLayout.LayoutParams(0,
						LayoutParams.MATCH_PARENT, (float) randomWeight));
				Log.i(TAG, Integer.toString(randomWeight));

				horizontalLayout.addView(textView);
			}
		}

	}

	private int randomColor() {
		int randomColor;
		do {
			randomColor = randInt.nextInt(colors.length);
		} while (randomColor == color);
		color = randomColor;
		return getResources().getColor(colors[randomColor]);
	}

	@Override
	protected void onResume() {
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
				for (int j = 0; j < countLayout - 1; j++) {
					horizontalLayout = (LinearLayout) rootLayout.getChildAt(j);
					for (int k = 0; k < 6; k++) {
						textView = (TextView) horizontalLayout.getChildAt(k);
						ColorDrawable backgroundColor = (ColorDrawable) textView
								.getBackground();
						int colorId = backgroundColor.getColor();
						if (colorId != getResources()
								.getColor(R.color.grey_300)) {
							textView.setBackgroundColor(Color.rgb(i, i, i));
						}
						;

					}
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}
		});
		super.onResume();
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
