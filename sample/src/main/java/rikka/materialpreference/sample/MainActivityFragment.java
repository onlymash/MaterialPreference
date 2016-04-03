package rikka.materialpreference.sample;

import android.content.Context;
import android.os.Bundle;

import rikka.materialpreference.PreferenceFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends PreferenceFragment {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        getPreferenceManager().setSharedPreferencesName("settings");
        getPreferenceManager().setSharedPreferencesMode(Context.MODE_PRIVATE);

        setPreferencesFromResource(R.xml.settings, null);
    }
}
