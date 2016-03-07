package rikka.materialpreference.sample;

import android.os.Bundle;

import rikka.materialpreference.PreferenceFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends PreferenceFragment {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferencesFromResource(R.xml.settings, null);
    }
}
