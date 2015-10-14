package com.example.drawdemo02;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
/*
 * ���߶�
 * 
 * �쳤�����̡��ƶ���ʵ�������»���
 */
public class DrawLine extends DrawBS {

	private Point cenPoint;//ֱ�ߵ��м��
	private Point lPoint1, lPoint2;//ֱ�ߵ�������յ�
	private Rect lPoint1Rect, lPoint2Rect;//��ֱ�������յ�Ϊ���ĵľ���
	
	public DrawLine() {
		// TODO Auto-generated constructor stub
		cenPoint = new Point();
		lPoint1 = new Point();
		lPoint2 = new Point();
		lPoint1Rect = new Rect();
		lPoint2Rect = new Rect();
	}
	  
	
	
	public void onTouchDown(Point point) {
		downPoint.set(point.x, point.y);

		//���������
		if (lPoint1Rect.contains(point.x, point.y)) {
			Log.i("onTouchDown", "downState = 1");
			downState = 1;
		//�������յ�
		} else if (lPoint2Rect.contains(point.x, point.y)) {
			Log.i("onTouchDown", "downState = 2");
			downState = 2;
		//�����ֱ����
		} else if (panduan(point)) {
			Log.i("onTouchDown", "downState = 3");
			downState = 3;
		//��ֱ����
		} else {
			Log.i("onTouchDown", "downState = 0");
			downState = 0;
		}
	}   
	  
	
	
	  
	public void onTouchMove(Point point) {

		switch (downState) {
		//����϶�ֱ����㣬��ֱ�ߵ��յ㲻��
		case 1:
			lPoint1.set(point.x, point.y);
			moveState = 1;
			break;
		//����϶�ֱ���յ㣬��ֱ�ߵ���㲻��
		case 2:
			lPoint2.set(point.x, point.y);
			moveState = 2;
			break;
		//�����סֱ�߽����϶���������ƶ��ľ��������趨ֱ�ߵ�������յ�
		case 3:
			//����ֱ�ߵ��м��
			cenPoint.x = (lPoint2.x + lPoint1.x) / 2;
			cenPoint.y = (lPoint2.y + lPoint1.y) / 2;
			//�ƶ�����
			int movedX = point.x - cenPoint.x;
			int movedY = point.y - cenPoint.y;

			lPoint1.x = lPoint1.x + movedX;
			lPoint1.y = lPoint1.y + movedY;
			lPoint2.x = lPoint2.x + movedX;
			lPoint2.y = lPoint2.y + movedY;
			moveState = 3;
			break;
		//�������ֱ���ϣ������»�ֱ��
		default:
			lPoint1.set(downPoint.x, downPoint.y);
			lPoint2.set(point.x, point.y);
			break;
		}
	}   
	  
	  
	
	public void onTouchUp(Point point) {
		//�����趨ֱ�������յ�Ϊ���ĵľ���
		lPoint1Rect.set(lPoint1.x - 25, lPoint1.y - 25, lPoint1.x + 25,
				lPoint1.y + 25);
		lPoint2Rect.set(lPoint2.x - 25, lPoint2.y - 25, lPoint2.x + 25,
				lPoint2.y + 25);
	} 
	  
	  
	 /*
	  * �жϵ�ǰ������ĵ��Ƿ���ֱ����
	  * 
	  * �����û�������ĵ㵽�߶������˵�ľ���֮��
	  * ���߶εľ�����бȽ� ���ж�
	  */
	public boolean panduan(Point point) {

		double lDis = Math.sqrt((lPoint1.x - lPoint2.x)
				* (lPoint1.x - lPoint2.x) + (lPoint1.y - lPoint2.y)
				* (lPoint1.y - lPoint2.y));

		double lDis1 = Math.sqrt((point.x - lPoint1.x) * (point.x - lPoint1.x)
				+ (point.y - lPoint1.y) * (point.y - lPoint1.y));
		double lDis2 = Math.sqrt((point.x - lPoint2.x) * (point.x - lPoint2.x)
				+ (point.y - lPoint2.y) * (point.y - lPoint2.y));


		if (lDis1 + lDis2 >= lDis + 0.00f && lDis1 + lDis2 <= lDis + 5.00f) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		// ��ֱ��
		canvas.drawLine(lPoint1.x, lPoint1.y, lPoint2.x, lPoint2.y, paint);
	}


}
