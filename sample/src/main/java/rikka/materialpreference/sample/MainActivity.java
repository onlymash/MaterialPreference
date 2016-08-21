package rikka.materialpreference.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import moe.xing.daynightmode.BaseDayNightModeActivity;

public class MainActivity extends BaseDayNightModeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            MainActivityFragment fragment = new MainActivityFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                    fragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_night_mode:
                setNightMode(
                        AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES ?
                                AppCompatDelegate.MODE_NIGHT_NO : AppCompatDelegate.MODE_NIGHT_YES);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
