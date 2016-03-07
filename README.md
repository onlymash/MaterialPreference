MaterialPreferenceLibrary
==================================
Material Desgin Preference UI for API 15+

Preferences with Material Design:
- **rikka.materialpreference.SwitchPreference**
- **rikka.materialpreference.EditTextPreference**
- **rikka.materialpreference.ListPreference**
- **rikka.materialpreference.MultiSelectListPreference**
- **rikka.materialpreference.DropDownPreference**

Usage
-----------
1. Add the dependency

		allprojects {
    		repositories {
	        	jcenter()
	        	maven {
            		url "https://jitpack.io"
        			}
    			}
		}
	
		dependencies {
	        compile 'com.github.RikkaW:MaterialPreference:v0.0.1'
		}  
	
2. Add `<item name="preferenceTheme">@style/PreferenceTheme.Material</item>` to your theme.
3. Do as how you use system's `PreferenceFragment` and you just need to replace `PreferenceXXX` with my `PreferenceXXX`

Screenshots
-----------
4.1

![4.1](https://github.com/RikkaW/MaterialPreference/blob/master/sample_4.1.gif)

6.0

![6.0](https://github.com/RikkaW/MaterialPreference/blob/master/sample_6.0.gif)

Note
-----------
1. You need add `preference_fallback_accent_color` in your `colors.xml` to set accent color of dialog button and category title
2. The value of `ListPreference` and `EditTextPreference` will be set to summay, add `app:showValueSummary="false"` in xml to disable it

Requirment
-----------
This library used:
 - com.android.support:appcompat-v7:23.2.0
 - com.android.support:preference-v7:23.2.0
 - com.android.support:preference-v14:23.2.0
