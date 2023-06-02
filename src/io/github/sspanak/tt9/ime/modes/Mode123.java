package io.github.sspanak.tt9.ime.modes;

import android.provider.Settings;
import android.view.KeyEvent;

import androidx.annotation.NonNull;

import io.github.sspanak.tt9.ime.helpers.Key;
import io.github.sspanak.tt9.preferences.SettingsStore;
import io.github.sspanak.tt9.preferences.items.ItemNumberMode;

public class Mode123 extends InputMode {
	protected final SettingsStore settings;

	public int getId() {
		return MODE_123;
	}

	Mode123(SettingsStore settings) {
		this.settings = settings;
		allowedTextCases.add(CASE_LOWER);
	}

	@Override
	public boolean onNumber(int number, boolean hold, int repeat) {
		reset();
		String numbersMode = settings.getNumbersMode();
		if (numbersMode == ItemNumberMode.KEYCODES) {
			keyCode = (number == 0 && hold) ? KeyEvent.KEYCODE_PLUS : Key.numberToCode(number);
			return true;
		} else if (numbersMode == ItemNumberMode.SUGGESTIONS) {
			if (number == 0 && hold)
				suggestions.add("+");
			else
				suggestions.add(String.valueOf(number));
			autoAcceptTimeout = 0;
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean onOtherKey(int key, boolean hold) {
		reset();
		String numbersMode = settings.getNumbersMode();
		if (numbersMode == ItemNumberMode.KEYCODES) {
			if (Key.isDecimalSeparator(key)) {
				return SetKeyCode(key);
			} else if (key == KeyEvent.KEYCODE_POUND) {
				return SetKeyCode( hold ? KeyEvent.KEYCODE_SEMICOLON : key);
			} else if (key == KeyEvent.KEYCODE_STAR) {
				return SetKeyCode(key = hold ? KeyEvent.KEYCODE_PLUS : key);
			}
		} else if (numbersMode == ItemNumberMode.SUGGESTIONS) {
			if (Key.isDecimalSeparator(key)) {
				return AddSuggestionAndAccept(Key.decimalSeparatorToSuggestion(key));
			} else if (key == KeyEvent.KEYCODE_POUND) {
				return AddSuggestionAndAccept(hold ? ";" : "#");
			} else if (key == KeyEvent.KEYCODE_STAR) {
				return AddSuggestionAndAccept(hold ? "+" : "*");
			}
		}
		return false;
	}

	private boolean SetKeyCode(int code) {
		keyCode = code;
		return true;
	}

	private boolean AddSuggestionAndAccept(String suggestion) {
		suggestions.add(suggestion);
		autoAcceptTimeout = 0;
		return true;
	}

	@Override
	public boolean is123() {
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
