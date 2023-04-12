package io.github.sspanak.tt9.ui.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.content.ContextCompat;

import io.github.sspanak.tt9.Logger;
import io.github.sspanak.tt9.R;
import io.github.sspanak.tt9.ime.TraditionalT9;

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

		if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
			return handlePress(view.getId());
		}

		return false;
	}

	protected boolean handlePress(int keyId) {
		if (tt9 == null) {
			Logger.w(getClass().getCanonicalName(), "Traditional T9 handler is not set. Ignoring key press.");
			return false;
		}

		if (keyId == R.id.soft_key_add_word) return tt9.onKeyAddWord();
		if (keyId == R.id.soft_key_input_mode) return tt9.onKeyNextInputMode();
		if (keyId == R.id.soft_key_language) return tt9.onKeyNextLanguage();
		if (keyId == R.id.soft_key_ok) return tt9.onOK();
		if (keyId == R.id.soft_key_settings) return tt9.onKeyShowSettings();

		return false;
	}

	/**
	 * render
	 * Does extra work on the layout, to make the key look properly (if needed).
	 */
	public void render() {}
}
