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

	Button drawtoolButton;// ѡ����״
	Button eraserButton;// ��Ƥ
	Button cleanAllButton;// ���
	Button savePButton;// ����ͼƬ
	Button showButton;// �鿴ͼƬ

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myView = new MyView(getApplicationContext());
		myView.setBackgroundColor(Color.TRANSPARENT);

		// ��view���뵽������
		LinearLayout = (LinearLayout) findViewById(R.id.linearlayout01);
		LinearLayout.removeAllViews();
		LinearLayout.addView(myView);

		initBottomButton();
		
	}

	// �󶨰�ť�������ü����¼�
	private void initBottomButton() {

		drawtoolButton = (Button) findViewById(R.id.drawtool_btn);
		eraserButton = (Button) findViewById(R.id.eraser_btn);
		cleanAllButton = (Button) findViewById(R.id.cleanAll_btn);
		savePButton = (Button) findViewById(R.id.saveP_btn);
		showButton = (Button) findViewById(R.id.showP_btn);

		// ����onClick(View v)����
		drawtoolButton.setOnClickListener(this);
		eraserButton.setOnClickListener(this);
		cleanAllButton.setOnClickListener(this);
		savePButton.setOnClickListener(this);
		showButton.setOnClickListener(this);
	}

	

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.drawtool_btn:// ��ͼ��״��ѡ��
			selectShape();
			break;

		case R.id.eraser_btn:// ��Ƥ
			eraser();
			break;

		case R.id.cleanAll_btn:// ���
			LinearLayout.removeAllViews();
			myView = new MyView(getApplicationContext());
			LinearLayout.addView(myView);
			break;

		case R.id.saveP_btn:// ����ͼƬ
			myView.savePicture("hass", 0);
			break;

		case R.id.showP_btn:// �鿴ͼƬ
			myView.editPicture("hass.jpg");
			break;

		default:
			break;
		}
	}

	/*
	 * ѡ����״
	 */
	public void selectShape() {
		final String[] mItems = { "ֱ��", "����", "Բ��", "������", "������", "Բ����", "Ϳѻ" };

		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("ѡ����״");

		builder.setItems(mItems, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				//���÷���setDrawTool����������Ӧ��ʵ����
				myView.setDrawTool(which);
				Toast.makeText(getApplicationContext(),
						"ѡ����: " + mItems[which], Toast.LENGTH_SHORT).show();

				// ���ѡ����ͼ�Σ��򽫰�ťeraserButton������ʾΪ����Ƥ��
				eraserButton.setText("��Ƥ");
				isEraser = false;
			}

		}).setIcon(R.drawable.ic_launcher);

		builder.create().show();
	}

	/*
	 * ��Ƥ ��ť������ʾ����Ƥ���롰���ʡ�;Ĭ����ʾ����Ƥ��
	 */
	public void eraser() {
		if (isEraser) {
			// ��ǰ��ʾΪ�����ʡ�
			// ����view�ࣨdrawTool���еķ���setDrawTool()�����ݲ���
			myView.setDrawTool(6);
			// ���������ð�ť��ʾΪ����Ƥ��
			eraserButton.setText("����");
			isEraser = false;
		} else {
			// ��ǰ��ʾΪ����Ƥ��
			// ����view�ࣨdrawTool���еķ���setDrawTool()�����ݲ���
			myView.setDrawTool(10);
			// ��������ð�ť��ʾΪ�����ʡ�
			eraserButton.setText("��Ƥ");
			isEraser = true;
		}
	}

}
