package io.github.sspanak.tt9.ime.modes;

import androidx.annotation.NonNull;

import io.github.sspanak.tt9.ime.helpers.Key;
import io.github.sspanak.tt9.preferences.SettingsStore;

// see: InputType.isDialer()
public class ModeDialer extends Mode123 {
	ModeDialer(SettingsStore settings) {
		super(settings);
	}

	@Override public int getId() { return MODE_DIALER; }
	@Override public boolean is123() { return false; }
	@Override final public boolean isDialer() { return true; }

	@NonNull
	@Override
	public String toString() {
		return "Dialer";
	}
}
