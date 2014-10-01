package edu.sjsu.pingpong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class GameView extends ImageView implements Runnable {

	private int side;

	private static int x = -1;
	private static int y = -1;
	private static int xVelocity = 10;
	private static int yVelocity = 5;
	private final int FRAME_RATE = 30;

	private Context mContext;
	private Handler h;
	private GcmHttpClient gcmHC;
	private boolean drawBall = false, gameOver = false;

	private BitmapDrawable ball;
	private BitmapDrawable bar;
	private static int bx = -1, by = -1;
	private static int barW, barH, ballR;

	// GcmHttpClient gClient = new GcmHttpClient();

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		h = new Handler();

		this.setOnTouchListener(new BarTouchListener());
	}

	@Override
	public void run() {
		invalidate();
	}

	public synchronized void init(int s) {
		invalidate();
		
		//set side
		side = s;
		if (side == Main.LEFT)
			drawBall = true; // start game from left side
		
		//reset
		xVelocity = 10;
		yVelocity = 5;
		x = -1;
		y = -1;
		bx = -1;
		by = -1;
		gameOver = false;
		
		ball = (BitmapDrawable) mContext.getResources().getDrawable(
				R.drawable.ball);

		bar = (BitmapDrawable) mContext.getResources().getDrawable(
				R.drawable.scroll_bar_vertical);

		barW = bar.getBitmap().getWidth();	//bar width
		barH = bar.getBitmap().getHeight();	//bar height
		ballR = ball.getBitmap().getHeight();//ball radius
		
		Log.i("CUSTOM", "" + barH + " " + barW);
		
	}

	private synchronized void setBarCoord(final int x, final int y) {
		by = y;
	}

	public synchronized void setCoordinates(int x, int y, int xV, int yV) {
		GameView.x = x;
		GameView.y = y;
		GameView.xVelocity = xV;
		GameView.yVelocity = yV;

		// reenable drawing of ball
		if (!drawBall)
			drawBall = true;
	}

	protected void onDraw(Canvas c) {

		if(gameOver) {
			Thread.currentThread().interrupt();
		}
		
		if(bx < 0 && by < 0) {
			// setup bar
			if (side == Main.LEFT) {
				bx = 0;
			} else if (side == Main.RIGHT) {
				Log.i(">>>", this.getMaxWidth() + " " + this.getWidth() + " "
						+ bar.getBitmap().getWidth());
				bx = this.getWidth() - bar.getBitmap().getWidth();
			}

			by = this.getHeight() / 2;
		}
		if (x < 0 && y < 0) {
			x = this.getWidth() / 2;
			y = this.getHeight() / 2;
		} else {
			x += xVelocity;
			y += yVelocity;

			// Log.i(">>>", "" + x + " " + y);
			// check if hit right/left edge
			if (drawBall
					&& (((x > this.getWidth() - ball.getBitmap().getWidth()) && side == Main.LEFT) || (x < 0 && side == Main.RIGHT))) {
				// send data
				// choose side
				int otherside = (side == Main.LEFT) ? Main.RIGHT : Main.LEFT;

				// recompute x-coordinate for other device
				int xT = (otherside == Main.RIGHT) ? 0
						: (this.getWidth() - ball.getBitmap().getWidth());

				// send coordinates
				new GcmHttpClient(Main.regIds[otherside]).execute(xT, y,
						xVelocity, yVelocity);

				// disable drawing of ball
				drawBall = false;

			} else if (drawBall) {
				if (side == Main.LEFT) {
					if (y > (by - ballR) && y < (by + barH) && x < barW) {
							xVelocity = xVelocity * -1;
					}
				} else if (side == Main.RIGHT) {
					if (y > by && y < (by + barH) && x > (this.getWidth() - ball.getBitmap().getWidth() - barW)) {
						xVelocity = xVelocity * -1;
					}
				}

			}
			
			if (!gameOver && drawBall && ((x < 0 && side == Main.LEFT) || (x > this.getWidth() - barW && side == Main.RIGHT))) {
				Log.i(">>>", "" + x + " " + y + " " + barW + " "
						+ (by + barH));
				Log.i(">>>", "Game Over");
				drawBall = false;
				xVelocity = 0;
				yVelocity = 0;
				gameOver = true;
				Toast.makeText(mContext, "Game Over!!!. Press ESC to start New Game", Toast.LENGTH_SHORT).show();
			}

			if (drawBall
					&& ((y > this.getHeight() - ball.getBitmap().getHeight()) || (y < 0))) {
				yVelocity = yVelocity * -1;
			}
		}

		if (drawBall)
			c.drawBitmap(ball.getBitmap(), x, y, null);

		if (by > (this.getHeight() - bar.getBitmap().getHeight()))
			by = this.getHeight() - bar.getBitmap().getHeight();

		c.drawBitmap(bar.getBitmap(), bx, by, null);

		h.postDelayed(this, FRAME_RATE);

	}

	private class BarTouchListener implements OnTouchListener {

		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			int eid = event.getAction();
			switch (eid) {
			case MotionEvent.ACTION_MOVE:
				
				setBarCoord((int) event.getRawX(), (int) event.getRawY());
				break;

			default:
				break;
			}
			return true;
		}
	}

}