package io.github.sspanak.tt9.ime;

import android.view.View;

import io.github.sspanak.tt9.R;
import io.github.sspanak.tt9.ui.main.BaseMainView;
import io.github.sspanak.tt9.ui.main.MainNumpadView;
import io.github.sspanak.tt9.ui.main.MainSmallView;

class SoftKeyHandler {
	private final TraditionalT9 tt9;
	private BaseMainView main;

	public SoftKeyHandler(TraditionalT9 tt9) {
		this.tt9 = tt9;

		createView();
	}

	boolean createView() {
		int currentView = main != null ? main.getId() : -1;

		if (tt9.settings.getShowSoftNumpad() && currentView != R.id.main_numpad) {
			main = new MainNumpadView(tt9, tt9.settings);
			main.render();
			return true;
		} else if (currentView != R.id.main_small) {
			main = new MainSmallView(tt9, tt9.settings);
			main.render();
			return true;
		}

		return false;
	}

	View getView() {
		return main.getView();
	}

	void show() {
		main.render();
		main.show();
	}

	void hide() {
		main.hide();
	}

	void setDarkTheme(boolean darkEnabled) {
		main.setDarkTheme(darkEnabled);
	}
}
