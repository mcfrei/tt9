package io.github.sspanak.tt9.ui.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;

import io.github.sspanak.tt9.Logger;
import io.github.sspanak.tt9.R;

public class SoftNumberKey extends SoftKey {
	public SoftNumberKey(Context context) {
		super(context);
	}

	public SoftNumberKey(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SoftNumberKey(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	protected boolean handlePress(int keyId) {
		if (tt9 == null) {
			Logger.w(getClass().getCanonicalName(), "Traditional T9 handler is not set. Ignoring key press.");
			return false;
		}

		int number = getNumber(keyId);
		if (number < 0) {
			return false;
		}

		int keyCode = KeyEvent.KEYCODE_0 + number;

		tt9.onKeyDown(keyCode, new KeyEvent(KeyEvent.ACTION_DOWN, keyCode));
		tt9.onKeyUp(keyCode, new KeyEvent(KeyEvent.ACTION_DOWN, keyCode));

		return true;
	}

	private int getNumber(int keyId) {
		if (keyId == R.id.soft_key_0) return 0;
		if (keyId == R.id.soft_key_1) return 1;
		if (keyId == R.id.soft_key_2) return 2;
		if (keyId == R.id.soft_key_3) return 3;
		if (keyId == R.id.soft_key_4) return 4;
		if (keyId == R.id.soft_key_5) return 5;
		if (keyId == R.id.soft_key_6) return 6;
		if (keyId == R.id.soft_key_7) return 7;
		if (keyId == R.id.soft_key_8) return 8;
		if (keyId == R.id.soft_key_9) return 9;

		return -1;
	}
}
