package com.example.drawdemo02;

import android.graphics.Canvas;
import android.graphics.Point;
/*
 * ��Բ
 * 
 * �����϶�Ҳ�ã�����Ҳ�ã���ʵ�������»�Բ��
 * ֻ�ǲ��ı仭Բ��Ҫ��ĳЩ���Խ��л��ƣ������������������ƶ����϶���
 */
public class DrawCircle extends DrawBS {

	private Point rDotsPoint;//Բ��
	private int radius = 0;//�뾶
	private Double dtance = 0.0;//��ǰ�㵽Բ�ĵľ���
	
	public DrawCircle() {
		// TODO Auto-generated constructor stub
		rDotsPoint = new Point();
	}
	  
	
	
	public void onTouchDown(Point point) {
		downPoint.set(point.x, point.y);

		if (radius != 0) {
			//���㵱ǰ������ĵ㵽Բ�ĵľ���
			dtance = Math.sqrt((downPoint.x - rDotsPoint.x)
					* (downPoint.x - rDotsPoint.x)
					+ (downPoint.y - rDotsPoint.y)
					* (downPoint.y - rDotsPoint.y));
			// �������뾶��20�ͼ�20����Χ�ڣ�����Ϊ�û������Բ��
			if (dtance >= radius - 20 && dtance <= radius + 20) {
				downState = 1;
			//�������С�ڰ뾶������Ϊ�û������Բ��
			} else if (dtance < radius) {
				downState = -1;
			// ��ǰ����ĵ���԰��
			} else if (dtance > radius) {
				downState = 0;
			}
		} else {
			downState = 0;
		}
	}   
	  
	
	
	  
	public void onTouchMove(Point point) {

		switch (downState) {
		//�����Բ�ڣ��������趨Բ������
		case -1:
			rDotsPoint.set(point.x, point.y);
			break;
		//�����Բ�ϣ���Բ�����겻�䣬�����趨Բ�İ뾶
		case 1:
			radius = (int) Math.sqrt((point.x - rDotsPoint.x)
					* (point.x - rDotsPoint.x)
					+ (point.y - rDotsPoint.y)
					* (point.y - rDotsPoint.y));
			break;
		//�����Բ�⣬���»�Բ
		default:
			rDotsPoint.set(downPoint.x, downPoint.y);
			radius = (int) Math.sqrt((point.x - rDotsPoint.x)
					* (point.x - rDotsPoint.x)
					+ (point.y - rDotsPoint.y)
					* (point.y - rDotsPoint.y));
			break;
		}
	}   
	  
	
	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawCircle(rDotsPoint.x, rDotsPoint.y, radius, paint);// ��Բ
	}
	  

}
