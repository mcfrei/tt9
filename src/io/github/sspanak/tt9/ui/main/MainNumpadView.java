package io.github.sspanak.tt9.ui.main;

import androidx.core.content.ContextCompat;

import io.github.sspanak.tt9.R;
import io.github.sspanak.tt9.ime.TraditionalT9;
import io.github.sspanak.tt9.preferences.SettingsStore;

public class MainNumpadView extends BaseMainView {
	public MainNumpadView(TraditionalT9 tt9, SettingsStore settings) {
		super(tt9, settings, R.layout.main_numpad);
	}

	@Override
	public void render() {
		getView();
	}

	@Override
	final public void setDarkTheme(boolean darkEnabled) {
		if (view == null) {
			return;
		}

		// background
		view.setBackground(ContextCompat.getDrawable(
			view.getContext(),
			darkEnabled ? R.color.dark_candidate_background : R.color.candidate_background
		));
	}
}
