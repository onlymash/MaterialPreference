MaterialPreferenceLibrary
==================================
Material Desgin Preference UI

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
	        compile 'com.github.RikkaW:MaterialPreference:v0.0.12'
		}  
	
2. Add `<item name="preferenceTheme">@style/PreferenceThemeOverlay</item>` to your theme.
3. Do as how you use system's `PreferenceFragment`

Screenshots
-----------
4.1

![4.1](https://github.com/RikkaW/MaterialPreference/blob/master/sample_4.1.gif)

6.0

![6.0](https://github.com/RikkaW/MaterialPreference/blob/master/sample_6.0.gif)

Note
-----------
You need add `preference_fallback_accent_color` in your `colors.xml` to set accent color of dialog button and category title
