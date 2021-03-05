package com.example.eindopdracht_ferran_nick;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import androidx.annotation.RequiresApi;

public class SettingsFragment extends PreferenceFragment {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.activity_settings_screen);
        CheckBoxPreference checkbox = (CheckBoxPreference) findPreference("checkBox");
        SharedPreferences sharedpreferences = this.getContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        checkbox.setOnPreferenceChangeListener(new CheckBoxPreference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange (final Preference preference, final Object newValue){
                SharedPreferences.Editor editor = sharedpreferences.edit();
                if(newValue.toString() == "true"){
                    editor.putString("check", "true");
                } else{
                    editor.putString("check", "false");
                }
                editor.commit();
                return true;
            }
        });
    }
}
