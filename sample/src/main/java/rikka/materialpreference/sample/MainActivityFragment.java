package rikka.materialpreference.sample;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.Locale;

import rikka.materialpreference.ListPreference;
import rikka.materialpreference.PreferenceFragment;
import rikka.materialpreference.PreferenceViewHolder;

/**
 * PreferenceFragment example include set DropDownPreference entries programmatically
 */
public class MainActivityFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        getPreferenceManager().setDefaultPackages(new String[]{"rikka.materialpreference.sample."});

        getPreferenceManager().setSharedPreferencesName("settings");
        getPreferenceManager().setSharedPreferencesMode(Context.MODE_PRIVATE);

        setPreferencesFromResource(R.xml.settings, null);

        ListPreference listPreference;
        listPreference = (ListPreference) findPreference("drop_down2");
        listPreference.setEntries(new CharSequence[]{"Item 1", "Item 2"});
        listPreference.setEntryValues(new CharSequence[]{Locale.CHINESE.toString(), Locale.ENGLISH.toString()});
        if (listPreference.getValue() == null) {
            listPreference.setValueIndex(1);
        }

        listPreference = (ListPreference) findPreference("drop_down3");
        listPreference.setEntries(new CharSequence[]{"Vertical align selected item", "(｡>﹏<｡)", "(っ╹ ◡ ╹ )っ\uD83D\uDC8A\uFEFF ヽ(✿ﾟ▽ﾟ)ノ", "⁄(⁄ ⁄•⁄ω⁄•⁄ ⁄)⁄"});
        listPreference.setEntryValues(new CharSequence[]{"0", "1", "2", "3"});
        if (listPreference.getValue() == null) {
            listPreference.setValueIndex(2);
        }

        listPreference = (ListPreference) findPreference("drop_down5");
        listPreference.setEntries(new CharSequence[]{"This is a very long item. It will use a simple dialog if its text may wraps to more than one line", "Don't put too many item when use simple dialog", "（<ゝω・）☆"});
        listPreference.setEntryValues(new CharSequence[]{"0", "1", "2"});
        if (listPreference.getValue() == null) {
            listPreference.setValueIndex(2);
        }
    }

    @Override
    public DividerDecoration onCreateItemDecoration() {
        return new CategoryDivideDividerDecoration();
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
