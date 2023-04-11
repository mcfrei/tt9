package io.github.sspanak.tt9.ui.main;

import android.content.Context;
import android.util.AttributeSet;

import io.github.sspanak.tt9.Logger;
import io.github.sspanak.tt9.R;
import io.github.sspanak.tt9.ime.modes.InputMode;

public class SoftPunctuationKey extends SoftKey {
	public SoftPunctuationKey(Context context) {
		super(context);
	}

	public SoftPunctuationKey(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SoftPunctuationKey(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	protected boolean handlePress(int keyId) {
		if (tt9 == null) {
			Logger.w(getClass().getCanonicalName(), "Traditional T9 handler is not set. Ignoring key press.");
			return false;
		}

		if (tt9.getSettings().getInputMode() == InputMode.MODE_123) {
			if (keyId == R.id.soft_key_punctuation_1) tt9.onStar();
			if (keyId == R.id.soft_key_punctuation_2) tt9.onPound();
		} else {
			if (keyId == R.id.soft_key_punctuation_1) tt9.onExclamationMark();
			if (keyId == R.id.soft_key_punctuation_2) tt9.onQuestionMark();
		}

		return true;
	}

	private String getLabel(int keyId) {
		if (tt9.getSettings().getInputMode() == InputMode.MODE_123) {
			if (keyId == R.id.soft_key_punctuation_1) return "✱";
			if (keyId == R.id.soft_key_punctuation_2) return "#";
		} else {
			if (keyId == R.id.soft_key_punctuation_1) return "!";
			if (keyId == R.id.soft_key_punctuation_2) return "?";
		}

		return "PUNC";
	}

	@Override
	public void render() {
		setText(getLabel(getId()));
	}
}
