package io.github.sspanak.tt9.ime.modes;

import android.view.KeyEvent;

import androidx.annotation.NonNull;

public class Mode123 extends InputMode {

	public int getId() {
		return MODE_123;
	}

	Mode123() {
		allowedTextCases.add(CASE_LOWER);
	}

	@Override
	public boolean onNumber(int number, boolean hold, int repeat) {
		reset();
		if (number == 0 && hold)
			suggestions.add("+");
		else
			suggestions.add(String.valueOf(number));

		return true;
	}

	@Override
	public boolean onOtherKey(int key, boolean hold) {
		reset();

		if (hold) {
			if (key == KeyEvent.KEYCODE_POUND) {
				return suggestions.add(";");
			} else if (key == KeyEvent.KEYCODE_STAR) {
				return suggestions.add("+");
			}
		}

		int keyChar = new KeyEvent(KeyEvent.ACTION_DOWN, key).getUnicodeChar();
		if ((keyChar > 31 && key < 65) || (keyChar > 90 && keyChar < 97) || (keyChar > 122 && keyChar < 127)) {
			suggestions.add(String.valueOf((char) keyChar));
			return true;
		}

		return false;
	}

	@Override
	public void reset() {
		super.reset();
		autoAcceptTimeout = 0;
	}

	@Override
	final public boolean is123() {
		return true;
	}

	@Override
	final public boolean isNumeric() {
		return true;
	}

	@Override
	public int getSequenceLength() {
		return 0;
	}

	@NonNull
	@Override
	public String toString() {
		return "123";
	}
}
