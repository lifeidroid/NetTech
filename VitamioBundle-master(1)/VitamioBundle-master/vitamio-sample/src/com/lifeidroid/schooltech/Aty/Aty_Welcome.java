package com.lifeidroid.schooltech.Aty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;

import com.lifeidroid.schooltech.R;

public class Aty_Welcome extends Activity {
	private LinearLayout lay_welcome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_welcome);
		lay_welcome = (LinearLayout)findViewById(R.id.lay_welcome);
		AlphaAnimation Alpha = new AlphaAnimation(1.0f, 1.0f);
		Alpha.setDuration(2000);
		lay_welcome.startAnimation(Alpha);
		Alpha.setAnimationListener(new AnimationImpl());

	}

	private class AnimationImpl implements AnimationListener {

		@Override
		public void onAnimationStart(Animation animation) {
			lay_welcome.setBackgroundResource(R.drawable.img_welcome);
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			skip(); 
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

	}

	private void skip() {
		startActivity(new Intent(this, Aty_Login.class));
		finish();
	}
}
