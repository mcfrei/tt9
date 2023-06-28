package io.github.sspanak.tt9.ime.modes;

import android.view.KeyEvent;

import androidx.annotation.NonNull;

import io.github.sspanak.tt9.ime.helpers.Key;

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
