package io.github.sspanak.tt9.ui.main;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.github.sspanak.tt9.R;
import io.github.sspanak.tt9.ime.TraditionalT9;
import io.github.sspanak.tt9.preferences.SettingsStore;

abstract class BaseMainLayout {
	protected SettingsStore settings;
	protected TraditionalT9 tt9;
	private final int xml;

	protected View view = null;
	private final ArrayList<SoftKey> keys = new ArrayList<>();

	public BaseMainLayout(TraditionalT9 tt9, SettingsStore settings, int xml) {
		this.tt9 = tt9;
		this.settings = settings;
		this.xml = xml;
	}


	/** setDarkTheme
	 * Changes the main view colors according to the theme.
	 *
	 * We need to do this manually, instead of relying on the Context to resolve the appropriate colors,
	 * because this View is part of the main service View. And service Views are always locked to the
	 * system context and theme.
	 *
	 * More info:
	 * https://stackoverflow.com/questions/72382886/system-applies-night-mode-to-views-added-in-service-type-application-overlay
	 */
	abstract public void setDarkTheme(boolean yes);


	/**
	 * render
	 * Do all the necessary stuff to display the View.
	 */
	abstract public void render();


	public View getView() {
		if (view == null) {
			view = View.inflate(tt9.getApplicationContext(), xml, null);
			for (SoftKey key : getKeys()) {
				key.setTT9(tt9);
			}
		}

		return view;
	}

	public void show() {
		if (view != null) {
			view.setVisibility(View.VISIBLE);
		}
	}

	public void hide() {
		if (view != null) {
			view.setVisibility(View.GONE);
		}
	}

	public int getId() {
		return view != null ? view.getId() : -1;
	}

	protected ArrayList<SoftKey> getKeys() {
		if (view != null) {
			final ViewGroup softKeyContainer = view.findViewById(R.id.main_soft_keys);
			final int childrenCount = softKeyContainer != null ? softKeyContainer.getChildCount() : 0;

			for (int i = 0; i < childrenCount; i++) {
				View child = softKeyContainer.getChildAt(i);
				if (child instanceof SoftKey) {
					keys.add((SoftKey) child);
				}
			}
		}

		return keys;
	}
}
