package io.github.sspanak.tt9.ui.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.content.ContextCompat;

import io.github.sspanak.tt9.Logger;
import io.github.sspanak.tt9.R;
import io.github.sspanak.tt9.ime.TraditionalT9;
import io.github.sspanak.tt9.ui.UI;

public class SoftKey extends androidx.appcompat.widget.AppCompatButton implements View.OnTouchListener {
	protected TraditionalT9 tt9;

	public SoftKey(Context context) {
		super(context);
	}

	public SoftKey(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SoftKey(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void setTT9(TraditionalT9 tt9) {
		this.tt9 = tt9;
	}

	public void setDarkTheme(boolean darkEnabled) {
		int textColor = ContextCompat.getColor(
			getContext(),
			darkEnabled ? R.color.dark_button_text : R.color.button_text
		);
		setTextColor(textColor);
	}


	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		getRootView().setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		super.onTouchEvent(event);

		if (event.getAction() == MotionEvent.ACTION_UP) {
			return handlePress(view.getId());
		}

		return false;
	}

	protected boolean handlePress(int buttonId) {
		if (tt9 == null) {
			Logger.w(getClass().getCanonicalName(), "Traditional T9 handler is not set. Ignoring key press.");
			return false;
		}


		if (buttonId == R.id.soft_key_ok) return tt9.onOK();
		if (buttonId == R.id.soft_key_settings) {
			UI.showSettingsScreen(tt9);
			return true;
		}

		return false;
	}
}
