package io.github.sspanak.tt9.ui.main;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import io.github.sspanak.tt9.R;
import io.github.sspanak.tt9.ime.TraditionalT9;
import io.github.sspanak.tt9.preferences.SettingsStore;
import io.github.sspanak.tt9.ui.UI;

class MainLayoutSmall extends BaseMainLayout implements View.OnTouchListener {
	private final int[] buttons = { R.id.main_left, R.id.main_mid, R.id.main_right };
	private long lastBackspaceCall = 0;


	public MainLayoutSmall(TraditionalT9 tt9, SettingsStore settings) {
		super(tt9, settings, R.layout.main_small);
	}


	private void setSoftKeysVisibility(boolean visible) {
		if (view != null) {
			view.findViewById(R.id.main_soft_keys).setVisibility(visible ? LinearLayout.VISIBLE : LinearLayout.GONE);
		}
	}

	@Override
	public void render() {
		getView();
		setSoftKeysVisibility(settings.getShowSoftKeys());

		/* ******************************* */
		for (int buttonId : buttons) {
			view.findViewById(buttonId).setOnTouchListener(this);
		}
		/* ******************************* */
	}

	@Override
	final public void setDarkTheme(boolean darkEnabled) {
		if (view == null) {
			return;
		}

		// background
		view.findViewById(R.id.main_soft_keys).setBackground(ContextCompat.getDrawable(
			view.getContext(),
			darkEnabled ? R.drawable.button_background_dark : R.drawable.button_background
		));

		// text
		int textColor = ContextCompat.getColor(
			view.getContext(),
			darkEnabled ? R.color.dark_button_text : R.color.button_text
		);

		for (int buttonId : buttons) {
			Button button = view.findViewById(buttonId);
			button.setTextColor(textColor);
		}

		// separators
		Drawable separatorColor = ContextCompat.getDrawable(
			view.getContext(),
			darkEnabled ? R.drawable.button_separator_dark : R.drawable.button_separator
		);

		view.findViewById(R.id.main_separator_left).setBackground(separatorColor);
		view.findViewById(R.id.main_separator_right).setBackground(separatorColor);
	}

	/* ******************************* */
	private boolean handleBackspaceHold() {
		if (System.currentTimeMillis() - lastBackspaceCall < settings.getSoftKeyRepeatDelay()) {
			return true;
		}

		boolean handled = tt9.onBackspace();

		long now = System.currentTimeMillis();
		lastBackspaceCall = lastBackspaceCall == 0 ? settings.getSoftKeyInitialDelay() + now : now;

		return handled;
	}


	private boolean handleBackspaceUp() {
		lastBackspaceCall = 0;
		return true;
	}


	@Override
	public boolean onTouch(View view, MotionEvent event) {
		int action = event.getAction();
		int buttonId = view.getId();

		if (buttonId == R.id.main_left && action == MotionEvent.ACTION_UP) {
			UI.showSettingsScreen(tt9);
			return view.performClick();
		}

		if (buttonId == R.id.main_mid && action == MotionEvent.ACTION_UP) {
			tt9.onOK();
			return view.performClick();
		}

		if (buttonId == R.id.main_right) {
			if (action == MotionEvent.AXIS_PRESSURE) {
				return handleBackspaceHold();
			} else if (action == MotionEvent.ACTION_UP) {
				return handleBackspaceUp();
			}
		}

		return false;
	}
	/* ******************************* */
}
