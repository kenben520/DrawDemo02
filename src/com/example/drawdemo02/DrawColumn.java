package com.example.drawdemo02;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.RectF;
/*
 * ��Բ��
 * 
 * android�Լ�û�л�Բ���ķ�����������Ҫ�Լ�����취ʵ�֡�
 * ˼·��
 * 1���Ȼ�һ����Բ��
 * 2�����ݵ�һ����Բ���ڶ�����Բ����������Բ����ƽ��
 * 3����������Բ����ߺ��ұ�������������������������Բ����
 */
public class DrawColumn extends DrawBS {

	//������Բ1��2��4��������
	private Point ovalPoint1, ovalPoint2, ovalPoint3, ovalPoint4;
	private Point LeftPoint_oval1, RightPoint_oval1, LeftPoint_oval2,
			RightPoint_oval2;
	private RectF rectF1, rectF2;

	public DrawColumn() {
		// TODO Auto-generated constructor stub
		// ʵ����
		LeftPoint_oval1 = new Point();
		RightPoint_oval1 = new Point();
		LeftPoint_oval2 = new Point();
		RightPoint_oval2 = new Point();
		ovalPoint1 = new Point();
		ovalPoint2 = new Point();
		ovalPoint3 = new Point();
		ovalPoint4 = new Point();
		rectF1 = new RectF();
		rectF2 = new RectF();
	}

	public void onTouchDown(Point point) {
		downPoint.set(point.x, point.y);
	}

	public void onTouchMove(Point point) {

		/*
		 * ����Բ�뻭��������
		 * ��ȷ��������1��Ҫ�������������oval1Point1��oval1Point2
		 */
		ovalPoint1.set(downPoint.x, downPoint.y);
		ovalPoint2.set(point.x, point.y);
		
		/*
		 * ��Բ1 ��ߺ��ұߵĵ������LeftPoint_oval1��RightPoint_oval1
		 */
		//������
		int y1 = ovalPoint1.y + (ovalPoint2.y - ovalPoint1.y) / 2;
		LeftPoint_oval1 = new Point(ovalPoint1.x, y1);
		RightPoint_oval1 = new Point(ovalPoint2.x, y1);

		//����һ�����룬
		int distance = (int) Math.abs(Math.sqrt((ovalPoint2.x - ovalPoint1.x)
				* (ovalPoint2.x - ovalPoint1.x) + (ovalPoint2.y - ovalPoint1.y)
				* (ovalPoint2.y - ovalPoint1.y)));


		/*
		 * ������Բ1����Բ2
		 * ��Բ1����Բ2����ƽ�У������겻�䣬�ı�������
		 * ȷ������Բ2��Ҫ������������oval2Point1��oval2Point2
		 */
		ovalPoint3.set(ovalPoint1.x, ovalPoint1.y + distance);
		ovalPoint4.set(ovalPoint2.x, ovalPoint2.y + distance);
		/*
		 * ��Բ2 ��ߺ��ұߵĵ������LeftPoint_oval1��RightPoint_oval1
		 */
		//������
		int y2 = ovalPoint3.y + (ovalPoint4.y - ovalPoint3.y) / 2;
		LeftPoint_oval2 = new Point(ovalPoint1.x, y2);
		RightPoint_oval2 = new Point(ovalPoint2.x, y2);

		rectF1.set(ovalPoint1.x, ovalPoint1.y, ovalPoint2.x, ovalPoint2.y);
		rectF2.set(ovalPoint3.x, ovalPoint3.y, ovalPoint4.x, ovalPoint4.y);

	}

	
	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawOval(rectF1, paint);
		canvas.drawOval(rectF2, paint);
		canvas.drawLine(LeftPoint_oval1.x, LeftPoint_oval1.y,
				LeftPoint_oval2.x, LeftPoint_oval2.y, paint);
		canvas.drawLine(RightPoint_oval1.x, RightPoint_oval1.y,
				RightPoint_oval2.x, RightPoint_oval2.y, paint);
		
	}

}
