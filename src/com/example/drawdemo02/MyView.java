package com.example.drawdemo02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/*
 * ʵ�ַ�ʽ��
 * ����������bitmap�ķ�ʽ��
 * �ײ�����bitmap��floorBitmap, surfaceBitmap;���bitmapΪ͸��ɫ������Ḳ�ǵ��ײ�bitmap��ͼ��
 * ��ǰ��ͼ�����ڱ��bitmap��surfaceBitmap������ı仭�ʣ��򽫵�ǰsurfaceBitmap�����ݻ��Ƶ��ײ�bitmap��floorBitmap
 * ���ѡ����Ƥ������Ҫ�ڵײ�bitmap�Ͻ��л��ƣ�
 * �鿴ԭͼƬ��Ҳ�ǽ�ͼƬ���Ƶ��ײ�bitmap
 */
public class MyView extends View {

	private DrawBS drawBS = null;
	private Point evevtPoint;
	private Bitmap floorBitmap, surfaceBitmap;// �ײ�����bitmap
	private Canvas floorCanvas, surfaceCanvas;// bitmap��Ӧ��canvas

	private boolean isEraser = false;
	
	Bitmap newbm;

	@SuppressLint("ParserError")
	public MyView(Context context) {
		super(context);

		// ��ʼ��drawBS����drawBSĬ��ΪDrawPath��
		drawBS = new DrawPath();
		evevtPoint = new Point();

		// �ײ�bitmap��canvas��
		floorBitmap = Bitmap.createBitmap(480, 700, Bitmap.Config.ARGB_8888);
		floorCanvas = new Canvas(floorBitmap);

		
		// ����bitmap�����ڵײ�bitmap֮�ϣ����ڸ�ֵ���Ƶ�ǰ��������ͼ�Σ���Ҫ����Ϊ͸�������򸲸ǵײ�bitmap
		surfaceBitmap = Bitmap.createBitmap(480, 700, Bitmap.Config.ARGB_8888);
		surfaceCanvas = new Canvas(surfaceBitmap);
		surfaceCanvas.drawColor(Color.TRANSPARENT);

	}

	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		// ���ײ�bitmap��ͼ�λ��Ƶ�������
		
		canvas.drawBitmap(floorBitmap, 0, 0, null);

		// �ж�ѡ���ͼ���Ƿ�Ϊ��Ƥ
		if (isEraser) {
			// �������Ƥ���û����ڵײ�bitmap�Ͻ��в�����
			/*
			 * ���ݵײ�Canvas������ ������Ӧ�Ļ�ͼ�����෽��,�ڵײ�bitmap��ʹ��floorCanvas���л�ͼ
			 */
			drawBS.onDraw(floorCanvas);
			canvas.drawBitmap(floorBitmap, 0, 0, null);

		} else {
			// ���������Ƥ�����û����ڱ��bitmap�Ͻ��в�����
			/*
			 * ���ݱ��Canvas������ ������Ӧ��ͼ�����෽��,�ڱ��bitmap��ʹ��surfaceCanvas���л�ͼ
			 */
			drawBS.onDraw(surfaceCanvas);
			canvas.drawBitmap(surfaceBitmap, 0, 0, null);
		}

	}

	// �����¼���������Ӧ�Ļ�ͼ��������в���
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		evevtPoint.set((int) event.getX(), (int) event.getY());

		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN:
			drawBS.onTouchDown(evevtPoint);
			break;

		case MotionEvent.ACTION_MOVE:
			drawBS.onTouchMove(evevtPoint);
			/*
			 * �϶������в�ͣ�Ľ�bitmap����ɫ����Ϊ͸������ձ��bitmap��
			 * ���������϶����̵Ĺ켣���ử����
			 */
			surfaceBitmap.eraseColor(Color.TRANSPARENT);
			invalidate();
			break;

		case MotionEvent.ACTION_UP:
			drawBS.onTouchUp(evevtPoint);
			break;
		default:
			break;
		}
		return true;
	}

	// ѡ��ͼ�Σ�ʵ������Ӧ����
	public void setDrawTool(int i) {
		switch (i) {
		case 0:
			drawBS = new DrawLine();
			break;
		case 1:
			drawBS = new DrawRectangle();
			break;
		case 2:
			drawBS = new DrawCircle();
			break;
		case 3:
			drawBS = new DrawTriangle();
			break;
		case 4:
			drawBS = new DrawCube();
			break;
		case 5:
			drawBS = new DrawColumn();
			break;
		case 10:
			// �����Ҫ��Ƥ����ʵ�����������û��ʵĹ��췽��
			drawBS = new DrawPath(10);// ��Ƥ
			break;
		default:
			drawBS = new DrawPath();
			break;
		}

		// ���ѡ����Ƥ��isEraser = true
		if (i == 10) {
			isEraser = true;
		} else {
			isEraser = false;
		}
		// �������ѡ����ͼ�Σ�����Ҫ�����bitmap�ϵ�ͼ����Ƶ��ײ�bitmap�Ͻ��б���
		floorCanvas.drawBitmap(surfaceBitmap, 0, 0, null);
	}

	// ��ͼƬ�����ڴ濨
	public void savePicture(String draw_name, int alpha) {
		FileOutputStream fos = null;
		String type = null;

		if (alpha == 0) {// ��͸��
			type = ".jpg";
		} else {
			type = ".png";
		}
		try {
			String strPath = new String("/sdcard/HBImg/");
			File fPath = new File(strPath);
			if (!fPath.exists()) {
				fPath.mkdir();
			}

			File f = new File(strPath + draw_name.trim() + type);
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			fos = new FileOutputStream(f);
			Bitmap b = null;
			destroyDrawingCache();
			setDrawingCacheEnabled(true);

			buildDrawingCache();
			b = getDrawingCache();

			if (b != null) {
				b.compress(Bitmap.CompressFormat.PNG, 100, fos);
				if (!b.isRecycled())
					b.recycle();
				b = null;
				System.gc();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
					Log.e("send picture to dbserver", "�ر��ϴ�ͼƬ��������ʧ�ܣ�");
				}
			}
		}
	}

	// ȡ��ͼƬ
	public void editPicture(String draw_name) {
		// �½�һ����ʱ��bitmap���ڷ��ö�ȡ����ͼƬ
		Bitmap tempBitmap = Bitmap.createBitmap(floorBitmap.getWidth(),
				floorBitmap.getHeight(), Bitmap.Config.ARGB_8888);
		if (!tempBitmap.isRecycled()) {
			tempBitmap.recycle();
			tempBitmap = null;
			System.gc();
		}

		tempBitmap = getLoacalBitmap(draw_name);
		// ��tempBitmap�е�ͼ�λ��Ƶ�floorBitmap
		floorCanvas.drawBitmap(tempBitmap, 0, 0, null);
		invalidate();
	}

	private Bitmap getLoacalBitmap(String draw_name) {
		try {
			BitmapFactory.Options opt = new BitmapFactory.Options();
			opt.inSampleSize = 1;
			opt.inPreferredConfig = Bitmap.Config.RGB_565;
			opt.inPurgeable = true;
			opt.inInputShareable = true; // ��ȡ��ԴͼƬ
			FileInputStream fis = new FileInputStream(
					"/sdcard/HBImg/" + draw_name);
			return BitmapFactory.decodeStream(fis, null, opt);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
