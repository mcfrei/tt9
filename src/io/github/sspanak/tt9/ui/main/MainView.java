package io.github.sspanak.tt9.ui.main;

import android.view.View;

import io.github.sspanak.tt9.ime.TraditionalT9;
import io.github.sspanak.tt9.preferences.SettingsStore;

public class MainView {
	private final TraditionalT9 tt9;
	private final SettingsStore settings;
	private BaseMainLayout main;

	public MainView(TraditionalT9 tt9, SettingsStore settings) {
		this.tt9 = tt9;
		this.settings = settings;

		createView();
	}

	public boolean createView() {
		if (this.settings.getShowSoftNumpad() && !(main instanceof MainLayoutNumpad)) {
			main = new MainLayoutNumpad(tt9, this.settings);
			main.render();
			return true;
		} else if (!this.settings.getShowSoftNumpad() && !(main instanceof MainLayoutSmall)) {
			main = new MainLayoutSmall(tt9, this.settings);
			main.render();
			return true;
		}

		return false;
	}

	public View getView() {
		return main.getView();
	}

	public void show() {
		main.render();
		main.show();
	}

	public void hide() {
		main.hide();
	}

	public void setDarkTheme(boolean darkEnabled) {
		main.setDarkTheme(darkEnabled);
	}
}
