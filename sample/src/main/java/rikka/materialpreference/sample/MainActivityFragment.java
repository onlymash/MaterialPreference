package rikka.materialpreference.sample;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.Locale;

import rikka.materialpreference.DropDownPreference;
import rikka.materialpreference.PreferenceFragment;

/**
 * PreferenceFragment example include set DropDownPreference entries programmatically
 */
public class MainActivityFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        getPreferenceManager().setSharedPreferencesName("settings");
        getPreferenceManager().setSharedPreferencesMode(Context.MODE_PRIVATE);

        setPreferencesFromResource(R.xml.settings, null);

        DropDownPreference dropDownPreference = (DropDownPreference) findPreference("drop_down2");
        dropDownPreference.setEntries(new CharSequence[]{Locale.CHINESE.getDisplayName(), Locale.ENGLISH.getDisplayName()});
        dropDownPreference.setEntryValues(new CharSequence[]{Locale.CHINESE.toString(), Locale.ENGLISH.toString()});
        if (dropDownPreference.getValue() == null) {
            dropDownPreference.setValueIndex(1);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getPreferenceManager().setDefaultPackages(new String[]{"rikka."});
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d("MainActivityFragment", "onSharedPreferenceChanged " + key);
    }
}
