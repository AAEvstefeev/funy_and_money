package com.nerdroom.fcash.help;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class MyActivity extends Activity {
	public  MyActivity mn;
	public WidgetHelp wg;
	public static SettingHelper sh;
	public boolean loud=false;
	public float density;
    public float dpHeight;
    public float dpWidth ;
    public float work_place;
    public int slot;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		  //Remove title bar
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    //Remove notification bar
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		mn=this;
		sh=new SettingHelper(mn);
		wg= new WidgetHelp(mn);

		
//		If you want the the display dimensions in pixels you can use getSize:
/**
			Display display = getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			int width = size.x;
			int height = size.y;
	//		If you're not in an Activity you can get the default Display via WINDOW_SERVICE:

			WindowManager wm = (WindowManager) this.getSystemService(this.WINDOW_SERVICE);
			Display display2 = wm.getDefaultDisplay();
		//	Before getSize was introduced (in API level 13), you could use the getWidth and getHeight methods that are now deprecated:

			Display display3 = getWindowManager().getDefaultDisplay(); 
			int width3 = display3.getWidth();  // deprecated
			int height3 = display3.getHeight();  // deprecated		
		
			DisplayMetrics metrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metrics);

			metrics.heightPixels;
			metrics.widthPixels;
			
			
			
*/			Display display4 = getWindowManager().getDefaultDisplay();
		    DisplayMetrics outMetrics = new DisplayMetrics ();
		    display4.getMetrics(outMetrics);

		    density  = getResources().getDisplayMetrics().density;
		    dpHeight = outMetrics.heightPixels / density;
		    dpWidth  = outMetrics.widthPixels / density;
		    work_place=dpWidth*2/3;
		    slot=(int) (work_place/5);
	}	
	
}
