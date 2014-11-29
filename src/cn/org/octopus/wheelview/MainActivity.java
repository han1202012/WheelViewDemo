package cn.org.octopus.wheelview;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import cn.org.octopus.wheelview.widget.ArrayWheelAdapter;
import cn.org.octopus.wheelview.widget.OnWheelChangedListener;
import cn.org.octopus.wheelview.widget.OnWheelScrollListener;
import cn.org.octopus.wheelview.widget.WheelView;

public class MainActivity extends Activity{

	public static final String TAG = "octopus.activity";
	
	private static Button bt_click;
	
	public String province[] = new String[] { "  河北省  ", "  山西省  ", "  内蒙古  ", "  辽宁省  ", "  吉林省  ", "  黑龙江  ", "  江苏省  " };

    public String city[][] = new String[][] {
            new String[] {"  石家庄  ", "唐山", "秦皇岛", "邯郸", "邢台", "保定", "张家口", "承德", "沧州", "廊坊", "衡水"},
            new String[] {"太原", "大同", "阳泉", "长治", "晋城", "朔州", "晋中", "运城", "忻州", "临汾", "吕梁"},
            new String[] {"呼和浩特", "包头", "乌海", "赤峰", "通辽", "鄂尔多斯", "呼伦贝尔", "巴彦淖尔", "乌兰察布", "兴安", "锡林郭勒", "阿拉善"},
            new String[] {"沈阳", "大连", "鞍山", "抚顺", "本溪", "丹东", "锦州", "营口", "阜新", "辽阳", "盘锦", "铁岭", "朝阳", "葫芦岛"},
            new String[] {"长春", "吉林", "四平", "辽源", "通化", "白山", "松原", "白城", "延边"},
            new String[] {"哈尔滨", "齐齐哈尔", "鸡西", "鹤岗", "双鸭山", "大庆", "伊春", "佳木斯", "七台河", "牡丹江", "黑河", "绥化", "大兴安岭"},
            new String[] {"南京", "无锡", "徐州", "常州", "苏州", "南通", "连云港", "淮安", "盐城", "扬州", "镇江", "泰州", "宿迁"} };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	/*
	 * 点击事件
	 */
	public void onClick(View view) {
		showSelectDialog(this, "选择地点", province, city);
	}

	
	private void showSelectDialog(Context context, String title, final String[] left, final String[][] right) {
    	//创建对话框
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        //为对话框设置标题
        dialog.setTitle(title);
        //创建对话框内容, 创建一个 LinearLayout 
        LinearLayout llContent = new LinearLayout(context);
        //将创建的 LinearLayout 设置成横向的
        llContent.setOrientation(LinearLayout.HORIZONTAL);
        //创建 WheelView 组件
        final WheelView wheelLeft = new WheelView(context);
        //设置 WheelView 组件最多显示 5 个元素
        wheelLeft.setVisibleItems(5);
        //设置 WheelView 元素是否循环滚动
        wheelLeft.setCyclic(false);
        //设置 WheelView 适配器
        wheelLeft.setAdapter(new ArrayWheelAdapter<String>(left));
        //设置右侧的 WheelView
        final WheelView wheelRight = new WheelView(context);
        //设置右侧 WheelView 显示个数
        wheelRight.setVisibleItems(5);
        //设置右侧 WheelView 元素是否循环滚动
        wheelRight.setCyclic(true);
        //设置右侧 WheelView 的元素适配器
        wheelRight.setAdapter(new ArrayWheelAdapter<String>(right[0]));
        //设置 LinearLayout 的布局参数
        LinearLayout.LayoutParams paramsLeft = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, 4);
        paramsLeft.gravity = Gravity.LEFT;
        LinearLayout.LayoutParams paramsRight = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, 6);
        paramsRight.gravity = Gravity.RIGHT;
        //将 WheelView 对象放到左侧 LinearLayout 中
        llContent.addView(wheelLeft, paramsLeft);
        //将 WheelView 对象放到 右侧 LinearLayout 中
        llContent.addView(wheelRight, paramsRight);
        
        //为左侧的 WheelView 设置条目改变监听器
        wheelLeft.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
            	//设置右侧的 WheelView 的适配器
                wheelRight.setAdapter(new ArrayWheelAdapter<String>(right[newValue]));
                wheelRight.setCurrentItem(right[newValue].length / 2);
            }
        });
        
        wheelLeft.addScrollingListener(new OnWheelScrollListener() {
			
			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				
			}
		});
        
        //设置对话框点击事件 积极
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int leftPosition = wheelLeft.getCurrentItem();
                String vLeft = left[leftPosition];
                String vRight = right[leftPosition][wheelRight.getCurrentItem()];
                bt_click.setText(vLeft + "-" + vRight);
                dialog.dismiss();
            }
        });
        
        //设置对话框点击事件 消极
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //将 LinearLayout 设置到 对话框中
        dialog.setView(llContent);
        //显示对话框
        if (!dialog.isShowing()) {
            dialog.show();
        }
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			bt_click = (Button)rootView.findViewById(R.id.bt_click);
			return rootView;
		}
	}

}
