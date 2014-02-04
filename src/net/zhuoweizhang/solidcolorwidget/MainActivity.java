package net.zhuoweizhang.solidcolorwidget;

import android.app.Activity;
import android.os.Bundle;
import android.content.*;
import android.appwidget.*;

import com.larswerkman.holocolorpicker.*;

public class MainActivity extends Activity
{
	private int mAppWidgetId;

	private ColorPicker picker;
	private SVBar svBar;
	private OpacityBar opacityBar;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
			mAppWidgetId = extras.getInt(
					AppWidgetManager.EXTRA_APPWIDGET_ID, 
					AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		Intent resultValue = new Intent();
		resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
		setResult(RESULT_OK, resultValue);

		picker = (ColorPicker) findViewById(R.id.picker);
		svBar = (SVBar) findViewById(R.id.svbar);
		opacityBar = (OpacityBar) findViewById(R.id.opacitybar);
		
		picker.addSVBar(svBar);
		picker.addOpacityBar(opacityBar);

	}
	@Override
	protected void onPause() {
		if (isFinishing()) {
			savePrefs();
		}
		super.onPause();
	}

	private void savePrefs() {
		int color = picker.getColor();
		MainWidgetProvider.saveColorPref(this, mAppWidgetId, color);
		MainWidgetProvider.updateWidget(this, AppWidgetManager.getInstance(this), mAppWidgetId);
	}
}
