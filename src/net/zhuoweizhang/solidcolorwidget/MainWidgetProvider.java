package net.zhuoweizhang.solidcolorwidget;

import android.appwidget.*;
import android.app.*;
import android.content.*;
import android.widget.*;

public class MainWidgetProvider extends AppWidgetProvider {

	public static final String PREFS_NAME = "prefs";
	public static final String PREF_PREFIX_KEY = "pref_";

	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		for (int i = 0; i < appWidgetIds.length; i++) {
			updateWidget(context, appWidgetManager, appWidgetIds[i]);
		}
	}

	// Write the prefix to the SharedPreferences object for this widget
	static void saveColorPref(Context context, int appWidgetId, int color) {
		SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
		prefs.putInt(PREF_PREFIX_KEY + appWidgetId, color);
		prefs.commit();
	}

	// Read the prefix from the SharedPreferences object for this widget.
	// If there is no preference saved, get the default from a resource
	static int loadColorPref(Context context, int appWidgetId) {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		return prefs.getInt(PREF_PREFIX_KEY + appWidgetId, 0xffbebebe);
	}

	public static void updateWidget(Context context, AppWidgetManager appWidgetManager,
            int myId) {
		int color = loadColorPref(context, myId);
		// Construct the RemoteViews object.  It takes the package name (in our case, it's our
		// package, but it needs this because on the other side it's the widget host inflating
		// the layout from our package).
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_provider);
		views.setInt(R.id.appwidget_frame, "setBackgroundColor", color);

		// Tell the widget manager
		appWidgetManager.updateAppWidget(myId, views);
	}
}
