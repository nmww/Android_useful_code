package com.simplewidget.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class RatingBarDemo extends Activity {

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.ratingbar);

		final RatingBar rb1 = (RatingBar) findViewById(R.id.ratingBar1);

		final RatingBar rb2 = (RatingBar) findViewById(R.id.ratingBar2);

		final RatingBar rb3 = (RatingBar) findViewById(R.id.ratingBar3);

		OnRatingBarChangeListener orbcl = new OnRatingBarChangeListener() {

			public void onRatingChanged(RatingBar ratingBar, float rating,

			boolean fromUser) {

				switch (ratingBar.getId()) {

				case R.id.ratingBar1:

					// 把第一个评分条的值取出来设置给其他评分条

					rb2.setRating(rb1.getRating());

					rb3.setRating(rb1.getRating() * 2);// 十颗星所以乘以2

					break;

				case R.id.ratingBar2:

					rb1.setRating(rb2.getRating());

					rb3.setRating(rb2.getRating() * 2);

					break;

				case R.id.ratingBar3:

					rb1.setRating(rb3.getRating() / 2);

					rb2.setRating(rb3.getRating() / 2);

					break;

				}

			}

		};

		// 绑定监听器

		rb1.setOnRatingBarChangeListener(orbcl);

		rb2.setOnRatingBarChangeListener(orbcl);

		rb3.setOnRatingBarChangeListener(orbcl);

	}

}
