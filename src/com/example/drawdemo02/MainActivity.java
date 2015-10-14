package com.example.drawdemo02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	LinearLayout LinearLayout;
	MyView myView;

	private boolean isEraser = false;

	Button drawtoolButton;// 选择形状
	Button eraserButton;// 橡皮
	Button cleanAllButton;// 清空
	Button savePButton;// 保存图片
	Button showButton;// 查看图片

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myView = new MyView(getApplicationContext());
		myView.setBackgroundColor(Color.TRANSPARENT);

		// 将view加入到布局中
		LinearLayout = (LinearLayout) findViewById(R.id.linearlayout01);
		LinearLayout.removeAllViews();
		LinearLayout.addView(myView);

		initBottomButton();
		
	}

	// 绑定按钮，并设置监听事件
	private void initBottomButton() {

		drawtoolButton = (Button) findViewById(R.id.drawtool_btn);
		eraserButton = (Button) findViewById(R.id.eraser_btn);
		cleanAllButton = (Button) findViewById(R.id.cleanAll_btn);
		savePButton = (Button) findViewById(R.id.saveP_btn);
		showButton = (Button) findViewById(R.id.showP_btn);

		// 调用onClick(View v)方法
		drawtoolButton.setOnClickListener(this);
		eraserButton.setOnClickListener(this);
		cleanAllButton.setOnClickListener(this);
		savePButton.setOnClickListener(this);
		showButton.setOnClickListener(this);
	}

	

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.drawtool_btn:// 画图形状的选择
			selectShape();
			break;

		case R.id.eraser_btn:// 橡皮
			eraser();
			break;

		case R.id.cleanAll_btn:// 清空
			LinearLayout.removeAllViews();
			myView = new MyView(getApplicationContext());
			LinearLayout.addView(myView);
			break;

		case R.id.saveP_btn:// 保存图片
			myView.savePicture("hass", 0);
			break;

		case R.id.showP_btn:// 查看图片
			myView.editPicture("hass.jpg");
			break;

		default:
			break;
		}
	}

	/*
	 * 选择形状
	 */
	public void selectShape() {
		final String[] mItems = { "直线", "矩形", "圆形", "三角形", "立方体", "圆柱体", "涂鸦" };

		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("选择形状");

		builder.setItems(mItems, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				//调用方法setDrawTool（）进行相应的实例化
				myView.setDrawTool(which);
				Toast.makeText(getApplicationContext(),
						"选择了: " + mItems[which], Toast.LENGTH_SHORT).show();

				// 如果选择了图形，则将按钮eraserButton设置显示为“橡皮”
				eraserButton.setText("橡皮");
				isEraser = false;
			}

		}).setIcon(R.drawable.ic_launcher);

		builder.create().show();
	}

	/*
	 * 橡皮 按钮轮流显示“橡皮”与“画笔”;默认显示“橡皮”
	 */
	public void eraser() {
		if (isEraser) {
			// 当前显示为“画笔”
			// 调用view类（drawTool）中的方法setDrawTool()，传递参数
			myView.setDrawTool(6);
			// 则点击后设置按钮显示为“橡皮”
			eraserButton.setText("画笔");
			isEraser = false;
		} else {
			// 当前显示为“橡皮”
			// 调用view类（drawTool）中的方法setDrawTool()，传递参数
			myView.setDrawTool(10);
			// 点击后设置按钮显示为“画笔”
			eraserButton.setText("橡皮");
			isEraser = true;
		}
	}

}
