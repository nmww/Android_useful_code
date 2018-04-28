package com.demo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class FragmentComm extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(android.R.id.content, new ProgressFragment()).commit();
		}
	}

	public static class ProgressFragment extends Fragment {
		UpdateFragment mWorkFragment;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.progress, container, false);

			// Watch for button clicks.
			Button button = (Button) v.findViewById(R.id.restart);
			button.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					mWorkFragment.restart();
				}
			});

			return v;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);

			FragmentManager fm = getFragmentManager();

			// Check to see if we have retained the worker fragment.
			mWorkFragment = (UpdateFragment) fm.findFragmentByTag("work");

			// If not retained (or first time running), we need to create it.
			if (mWorkFragment == null) {
				mWorkFragment = new UpdateFragment();
				// Tell it who it is working with.
				mWorkFragment.setTargetFragment(this, 0);
				fm.beginTransaction().add(mWorkFragment, "work").commit();
			}
		}

	}

	public static class UpdateFragment extends Fragment {
		ProgressBar mProgressBar;
		int mPosition;
		boolean mReady = false;
		boolean mQuiting = false;

		final Thread mThread = new Thread() {
			@Override
			public void run() {

				int max = 1000;

				while (true) {

					// Update our shared state with the UI.
					synchronized (this) {

						while (!mReady || mPosition >= max) {
							if (mQuiting) {
								return;
							}
							try {
								wait();
							} catch (InterruptedException e) {
							}
						}

						mPosition++;
						max = mProgressBar.getMax();
						mProgressBar.setProgress(mPosition);
					}

					synchronized (this) {
						try {
							wait(50);
						} catch (InterruptedException e) {
						}
					}
				}
			}
		};

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			setRetainInstance(true);

			mThread.start();
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			Fragment temp = getFragmentManager().findFragmentById(
					android.R.id.content);

			mProgressBar = (ProgressBar) temp.getView().findViewById(
					R.id.progress_horizontal);

			synchronized (mThread) {
				mReady = true;
				mThread.notify();
			}
		}

		@Override
		public void onDestroy() {
			// Make the thread go away.
			synchronized (mThread) {
				mReady = false;
				mQuiting = true;
				mThread.notify();
			}

			super.onDestroy();
		}

		@Override
		public void onDetach() {

			synchronized (mThread) {
				mProgressBar = null;
				mReady = false;
				mThread.notify();
			}

			super.onDetach();
		}

		public void restart() {
			synchronized (mThread) {
				mPosition = 0;
				mThread.notify();
			}
		}
	}
}
