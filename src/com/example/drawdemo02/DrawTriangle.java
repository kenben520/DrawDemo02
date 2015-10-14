package com.example.drawdemo02;


import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;

/*
 * ��������
 * 
 * ��Ҫȷ��3���㣺
 * ��ʼ�㡢��ǰ�ƶ��㣬����ǰ������������һ����
 * 
 * ����3����ȷ��һ��������
 */
public class DrawTriangle extends DrawBS {

	private Path path;
	private Point point1, point2, point3;
	private Rect point1Rect, point2Rect, point3Rect;

	public DrawTriangle() {
		// TODO Auto-generated constructor stub
		point1 = new Point();
		point2 = new Point();
		point3 = new Point();

		point1Rect = null;
		point2Rect = null;
		point3Rect = null;

		path = new Path();
	}

	
	
	public void onTouchDown(Point point) {
		downPoint.set(point.x, point.y);

		/*
		 * �ж�point3Ϊ���ĵľ���point3Rect�Ƿ�Ϊnull��
		 * ���=null�����û���û�п�ʼ�������Σ������=null�����û��Ѿ�������������
		 * 
		 * �ж��û����λ��
		 */
		if (point1Rect != null) {
			if (point1Rect.contains(downPoint.x, downPoint.y)) {
				downState = 1;
			} else if (point2Rect.contains(downPoint.x, downPoint.y)) {
				downState = 2;
			} else if (point3Rect.contains(downPoint.x, downPoint.y)) {
				downState = 3;
			} else if (panduan(point1, point2, point3, downPoint)) {
				downState = 4;
				
			}else {
				downState = 0;
			}
		}
	}

	
	public void onTouchMove(Point point) {

		switch (downState) {
		case 1:
			point1.set(point.x, point.y);
			setPath();
			moveState = 1;
			break;
		case 2:
			point2.set(point.x, point.y);
			setPath();
			moveState = 2;
			break;
		case 3:
			point3.set(point.x, point.y);
			setPath();
			moveState = 3;
			break;
		case 4:
			/*
			 * �������������ڣ���ʼ�ƶ�������
			 * 
			 * �����������ε����ĵ�coreTanriglePointΪ���ĵ�����ƶ�
			 * 
			 */
			//���������ε�����
			Point coreTanriglePoint = new Point(
					(point1.x + point2.x + point3.x) / 3,
					(point1.y + point2.y + point3.y) / 3);
			//�����Ƶ��ľ���
			int movedX = point.x - coreTanriglePoint.x;
			int movedY = point.y - coreTanriglePoint.y;
			//�����趨�����ε�3������
			point1.set(point1.x + movedX, point1.y + movedY);
			point2.set(point2.x + movedX, point2.y + movedY);
			point3.set(point3.x + movedX, point3.y + movedY);
			moveState = 4;
			setPath();
			break;
		default:
			//���������ε�������
			point1.set(downPoint.x, downPoint.y);
			point2.set(point.x, point.y);
			//����point1��point2���м��
			Point cenPoint = new Point();
			cenPoint.x = (point2.x + point1.x) / 2;
			cenPoint.y = (point2.y + point1.y) / 2;
			//���õ�point3
			point3.set(cenPoint.x+100, cenPoint.y-100);
			setPath();
			break;
		}
		
	}



	/*
	 * �ж��û�������ĵ��Ƿ�����������
	 * 
	 * �ж�ԭ��
	 * �����û�������ĵ�������ε���������������ɵ�3�������ε����
	 * ��ԭ�����ε�������бȽ�
	 * 
	 * �����ȣ������������ڣ�
	 * �������ȣ�������������
	 */
	public static boolean panduan(Point a, Point b, Point c, Point p) {

		double abc = triangleArea(a, b, c);
		double abp = triangleArea(a, b, p);
		double acp = triangleArea(a, c, p);
		double bcp = triangleArea(b, c, p);

		if (abc == abp + acp + bcp) {
			return true;
		} else {
			return false;
		}
	}

	// ������������������ε����
	private static double triangleArea(Point a, Point b, Point c) {

		double result = Math.abs((a.x * b.y + b.x * c.y + c.x * a.y - b.x * a.y
				- c.x * b.y - a.x * c.y) / 2.0D);
		return result;
	}
	
	
	public void setPath() {
		path = new Path();
		path.moveTo(point1.x, point1.y);
		path.lineTo(point2.x, point2.y);
		path.lineTo(point3.x, point3.y);
		path.close();

		point1Rect = new Rect(point1.x-20, point1.y-20, point1.x+20, point1.y+20);
		point2Rect = new Rect(point2.x-20, point2.y-20, point2.x+20, point2.y+20);
		point3Rect = new Rect(point3.x-20, point3.y-20, point3.x+20, point3.y+20);
	}
	
	
	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawPath(path, paint);
	}
	
}
