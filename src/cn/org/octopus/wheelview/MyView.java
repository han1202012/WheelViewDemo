package cn.org.octopus.wheelview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyView extends View {

	public static final String TAG = "octopus.my.view";
	
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyView(Context context) {
		super(context);
	}

	public MyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		//获取宽度 和 高度的模式 和 大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        
        Log.i(TAG, "宽度 : widthMode : " + getMode(widthMode) + " , widthSize : " + widthSize + "\n" 
        		+ "高度 : heightMode : " + getMode(heightMode) + " , heightSize : " + heightSize);
        
        int width = 0;
        int height = 0;
        /*
         * 精准模式
         * 		精准模式下 高度就是精确的高度
         */
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        //未定义模式 和 最大模式
        } else {
        	//未定义模式下 获取布局需要的高度
            height = 100;

            //最大模式下 获取 布局高度 和 布局所需高度的最小值
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, heightSize);
            }
        }
        
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = 100;
            if (heightMode == MeasureSpec.AT_MOST) {
                width = Math.min(width, widthSize);
            }
        }

        Log.i(TAG, "最终结果 : 宽度 : " + width + " , 高度 : " + height);
        
        setMeasuredDimension(width, height);
		
	}
	
	
	public String getMode(int mode) {
		String modeName = "";
		if(mode == MeasureSpec.EXACTLY){
			modeName = "精准模式";
		}else if(mode == MeasureSpec.AT_MOST){
			modeName = "最大模式";
		}else if(mode == MeasureSpec.UNSPECIFIED){
			modeName = "未定义模式";
		}
			
		return modeName;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvas.drawColor(Color.BLUE);
	}

}
