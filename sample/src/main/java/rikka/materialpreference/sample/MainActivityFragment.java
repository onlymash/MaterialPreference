package rikka.materialpreference.sample;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.Locale;

import rikka.materialpreference.DropDownPreference;
import rikka.materialpreference.PreferenceFragment;
import rikka.materialpreference.PreferenceViewHolder;
import rikka.materialpreference.SimpleMenuPreference;

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

        SimpleMenuPreference dropDownPreference3 = (SimpleMenuPreference) findPreference("drop_down3");
        dropDownPreference3.setEntries(new CharSequence[]{"Item 1", "Item 1 Item 1 Item 1 Item 1 Item 1 Item 1", "Item 1 Item 1 Item"});
        dropDownPreference3.setEntryValues(new CharSequence[]{"0", "1", "2"});
        if (dropDownPreference3.getValue() == null) {
            dropDownPreference3.setValueIndex(1);
        }
    }

    @Override
    public DividerDecoration onCreateItemDecoration() {
        return new DefaultDividerDecoration() {
            @Override
            public boolean shouldDrawDividerAbove(View view, RecyclerView parent) {
                PreferenceViewHolder holder =
                        (PreferenceViewHolder) parent.getChildViewHolder(view);

                boolean nextAllowed = false;
                int index = parent.indexOfChild(view);
                if (index < parent.getChildCount() - 1) {
                    View nextView = parent.getChildAt(index + 1);
                    PreferenceViewHolder nextHolder =
                            (PreferenceViewHolder) parent.getChildViewHolder(nextView);
                    nextAllowed = nextHolder.isDividerAllowedAbove();
                }
                return nextAllowed && !holder.isDividerAllowedAbove() && index != 0;
            }

            @Override
            public boolean shouldDrawDividerBelow(View view, RecyclerView parent) {
                return false;
            }
        };
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
