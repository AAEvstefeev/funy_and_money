package com.nerdroom.fcash.help;

import com.nerdroom.funy.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

public class MyGIFView extends View {
// The resource id of the GIF
private int mResourceId;
private int mDrawLeftPos;
// Movie to be shown
private Movie mMovie;
private long mStartTime;

// Size of this View
private int mHeight, mWidth;
private  Context ctx;
/**
 * Constructor
 * 
 * @param context
 * @param attributes
 */
public void setGIF(int id)
{
	 
	      //  mResourceId = t.getResourceId ( R.styleable.GIFView_resourceId, -1 );

	        mMovie = Movie.decodeStream( ctx.getResources().openRawResource( id ) );
	        if( mMovie != null )
	        {
	            mWidth = mMovie.width();
	            mHeight = mMovie.height();
	        }
	    	
}
public MyGIFView( Context context, AttributeSet attributes ) {
    super( context, attributes );
    ctx=context;
    // Get the attribute for resource id
    TypedArray t = context.getTheme().obtainStyledAttributes( 
            attributes, R.styleable.GIFView, 0, 0 );
    setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    mResourceId = -1;
    mMovie = null;
    mStartTime = 0;
    mHeight = 0;
    mWidth = 0;

    // This call might fail
   
    
        t.recycle();
    
}


@Override
protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec )
{   
    int p_top = this.getPaddingTop(), p_bottom = this.getPaddingBottom();

    // Calculate new desired height
    final int desiredHSpec = MeasureSpec.makeMeasureSpec( mHeight + p_top + p_bottom , MeasureSpec.EXACTLY );

    setMeasuredDimension( widthMeasureSpec, desiredHSpec );
    super.onMeasure( widthMeasureSpec, desiredHSpec );

    // Update the draw left position
    mDrawLeftPos = Math.max( ( this.getWidth() - mWidth ) / 2, 0) ;
}
@Override
protected void onDraw( Canvas canvas )
{
    super.onDraw( canvas );

    // Return if we have no movie
    if( mMovie == null ) return;

    // Catch the time now
    long now = System.currentTimeMillis();

    // Catch the start time if needed
    if( mStartTime == 0 ) mStartTime =(int) now;
int dur=mMovie.duration();
    int relTime = (int)( (now- mStartTime) % mMovie.duration() );
    mMovie.setTime( relTime );
    mMovie.draw( canvas, mDrawLeftPos, this.getPaddingTop() );
    this.invalidate();
    
    
}
}