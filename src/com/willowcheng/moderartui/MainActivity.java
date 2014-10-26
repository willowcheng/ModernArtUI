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
	static final int colors[] = { R.color.red_400, R.color.blue_400,
			R.color.yellow_400, R.color.green_400, R.color.grey_300, };
	static final String TAG = "Hello";
	private int color = 4;
	private ColorDrawable backgroundColor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mBuilder = new AlertDialog.Builder(this);

		seekBar = (SeekBar) findViewById(R.id.mSeekBar);
		seekBar.setMax(255);

		rootLayout = (LinearLayout) findViewById(R.id.rootlayout);
		countLayout = rootLayout.getChildCount();
		randInt = new Random();

		for (int i = 0; i < countLayout - 1; i++) {
			horizontalLayout = (LinearLayout) rootLayout.getChildAt(i);

			for (int j = 0; j < 6; j++) {
				randomWeight = randInt.nextInt(3) + 1;
				textView = (TextView) getLayoutInflater().inflate(
						R.layout.color_item, horizontalLayout, false);

				textView.setBackgroundColor(randomColor());
				textView.setLayoutParams(new LinearLayout.LayoutParams(0,
						LayoutParams.MATCH_PARENT, (float) randomWeight));

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
		final int red[][] = new int[12][6];
		final int green[][] = new int[12][6];
		final int blue[][] = new int[12][6];
		for (int j = 0; j < countLayout - 1; j++) {
			horizontalLayout = (LinearLayout) rootLayout.getChildAt(j);
			for (int k = 0; k < 6; k++) {
				textView = (TextView) horizontalLayout.getChildAt(k);
				backgroundColor = (ColorDrawable) textView.getBackground();
				int colorValue = backgroundColor.getColor();
				red[j][k] = (colorValue >> 16) & 0xFF;
				green[j][k] = (colorValue >> 8) & 0xFF;
				blue[j][k] = (colorValue >> 0) & 0xFF;
			}

		}
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

				for (int j = 0; j < countLayout - 1; j++) {
					horizontalLayout = (LinearLayout) rootLayout.getChildAt(j);
					for (int k = 0; k < 6; k++) {
						textView = (TextView) horizontalLayout.getChildAt(k);
						backgroundColor = (ColorDrawable) textView
								.getBackground();
						int colorValue = backgroundColor.getColor();

						if (colorValue != getResources().getColor(
								R.color.grey_300)) {
							if (colorValue == (getResources()
									.getColor(colors[0]))) {
								textView.setBackgroundColor(Color.rgb(
										red[j][k], green[j][k] + i, blue[j][k]
												+ i));
							} else if (colorValue == (getResources()
									.getColor(colors[1]))) {
								textView.setBackgroundColor(Color.rgb(red[j][k]
										+ i, green[j][k] + i, blue[j][k]));
							} else if (colorValue == (getResources()
									.getColor(colors[3]))) {
								textView.setBackgroundColor(Color.rgb(red[j][k]
										+ i, green[j][k], blue[j][k] + i));
							} else {
								textView.setBackgroundColor(Color.rgb(
										red[j][k], green[j][k], blue[j][k] + i));
							}
						}
					}
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
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
