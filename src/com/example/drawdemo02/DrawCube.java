package com.example.drawdemo02;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
/*
 * ������
 * 
 * ˼·��
 * 1���Ȼ�һ������
 * 2����һ���͵�һ������ƽ�еľ���
 * 3��������������֮���ĳЩ����
 */
public class DrawCube extends DrawBS {

	// �����������8�����㡣ǰ����ε�4������Ϊ1��2��3��4��������ε�4������Ϊ5��6��7��8
	Point cubePoint1, cubePoint2, cubePoint3, cubePoint4, cubePoint5,
			cubePoint6, cubePoint7, cubePoint8;
	Rect rect1, rect2;

	public DrawCube() {
		// TODO Auto-generated constructor stub
		// ʵ����
		cubePoint1 = new Point();
		cubePoint2 = new Point();
		cubePoint3 = new Point();
		cubePoint4 = new Point();
		cubePoint5 = new Point();
		cubePoint6 = new Point();
		cubePoint7 = new Point();
		cubePoint8 = new Point();
		rect1 = new Rect();
		rect2 = new Rect();
	}

	public void onTouchDown(Point point) {
		downPoint.set(point.x, point.y);
	}

	public void onTouchMove(Point point) {
		//��һ�����ε�4����
		cubePoint1.set(downPoint.x, downPoint.y);
		cubePoint2.set(point.x, point.y);
		cubePoint3.set(cubePoint1.x, cubePoint2.y);
		cubePoint4.set(cubePoint2.x, cubePoint1.y);

		// ���������ı߳���һ��
		int distance = (int) Math.abs(Math.sqrt((cubePoint4.x - cubePoint1.x)
				* (cubePoint4.x - cubePoint1.x) + (cubePoint4.y - cubePoint1.y)
				* (cubePoint4.y - cubePoint1.y))) / 2;
		// ����ǰ����ε�4�����㣬��������ε�4�����㸳ֵ
		cubePoint5.set(cubePoint1.x + distance, cubePoint1.y - distance);
		cubePoint6.set(cubePoint2.x + distance, cubePoint2.y - distance);
		cubePoint7.set(cubePoint3.x + distance, cubePoint3.y - distance);
		cubePoint8.set(cubePoint4.x + distance, cubePoint4.y - distance);

		//����rect1��rect2
		rect1.set(cubePoint1.x, cubePoint1.y, cubePoint2.x, cubePoint2.y);
		rect2.set(cubePoint5.x, cubePoint5.y, cubePoint6.x, cubePoint6.y);

	}

	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawRect(rect1, paint);
		canvas.drawRect(rect2, paint);
		canvas.drawLine(cubePoint1.x, cubePoint1.y, cubePoint5.x, cubePoint5.y,
				paint);
		canvas.drawLine(cubePoint2.x, cubePoint2.y, cubePoint6.x, cubePoint6.y,
				paint);
		canvas.drawLine(cubePoint3.x, cubePoint3.y, cubePoint7.x, cubePoint7.y,
				paint);
		canvas.drawLine(cubePoint4.x, cubePoint4.y, cubePoint8.x, cubePoint8.y,
				paint);
		
	}

}