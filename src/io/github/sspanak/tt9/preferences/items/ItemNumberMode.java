package io.github.sspanak.tt9.preferences.items;

import android.content.Context;
import android.content.res.Resources;

import androidx.preference.DropDownPreference;

import java.util.LinkedHashMap;

import io.github.sspanak.tt9.Logger;
import io.github.sspanak.tt9.R;


public class ItemNumberMode {
	public static final String NAME = "pref_number_mode";
	public static final String KEYCODES = "k";
	public static final String SUGGESTIONS = "s";
	private final DropDownPreference item;
	private final LinkedHashMap<String, String> KEYS = new LinkedHashMap<>();

	public ItemNumberMode(DropDownPreference dropDown, Context context) {
		this.item = dropDown;

		Resources resources = context.getResources();

		KEYS.put(KEYCODES, resources.getString(R.string.char_keycodes));
		KEYS.put(SUGGESTIONS, resources.getString(R.string.char_suggestions));
	}


	public ItemNumberMode populate() {
		if (item == null) {
			Logger.w("tt9/ItemNumberMode.populate", "Cannot populate a NULL item. Ignoring.");
			return this;
		}

		item.setEntries(KEYS.values().toArray(new CharSequence[0]));
		item.setEntryValues(KEYS.keySet().toArray(new CharSequence[0]));
		previewSelection(item.getValue());

		return this;
	}


	public void activate() {
		if (item == null) {
			Logger.w("tt9/ItemNumberMode.activate", "Cannot set a click listener a NULL item. Ignoring.");
			return;
		}

		item.setOnPreferenceChangeListener((preference, newChar) -> {
			((DropDownPreference) preference).setValue(newChar.toString());
			previewSelection(newChar.toString());
			return true;
		});

	}


	private void previewSelection(String newChar) {
		if (item == null) {
			return;
		}

		item.setSummary(KEYS.get(newChar));
	}

}
