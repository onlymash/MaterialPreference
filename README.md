MaterialPreference
==================

A simple Preference UI for AppCompat, modified from support library, make it more easier to use and more feature.

Screenshot
----------

![demo](https://github.com/RikkaW/MaterialPreference/blob/master/demo.gif)

Usage
-----

1.  Add dependencies
2.  Add `<item name="preferenceTheme">@style/PreferenceThemeOverlay</item>` in your theme
3.  Do as how you use `PreferenceFragment` (you can also check this [sample project](https://github.com/RikkaW/MaterialPreference/tree/master/sample) or play with [demo app](https://github.com/RikkaW/MaterialPreference/releases) to see how to do)

Contents
--------

`Preference`,`SwitchPreference`,`CheckBoxPreference`,`EditTextPreference`,`ListPreference`,`MultiSelectListPreference`,`DropDownPreference`,`SimpleMenuPreference`

Feature
-------

#### SimpleMenuPreference

A version of `ListPreference` that show a simple menu instead of dialog, looking and behavior follow [Simple Menus](https://material.google.com/components/menus.html#menus-simple-menus) of Material Design guideline

> Menu is vertical aligned to selected item and show in the parent view

> Menu width is a a multiple of a unit (56dp on mobile)

> When item is too long use a simple dialog

#### Small changes

-   Animation of `Switch` in `SwitchPreference` now works well
    -   use system's `Switch` on 5.0+ so we can get full animation
-   Add `setDefaultPackages` method for `PreferenceManager` so if you add your `Preference` you don't need to write `<xxx.xxx.xxxPreference>` in XML

        @Override
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                getPreferenceManager().setDefaultPackages(new String[]{""});
        }

-   Add `onCreateItemDecoration` for `PreferenceFragment` so we can change Divder behavior more easily

        @Override
        public DividerDecoration onCreateItemDecoration() {
            return new CategoryDivideDividerDecoration();
        }

    -   `DefaultDivideDividerDecoration` (default)
    -   `CategoryDivideDividerDecoration` (divide by categories)
-   Add more attributes for `EditTextPreference`

| Attribute                  | Values           | Description                                |
|----------------------------|------------------|--------------------------------------------|
| `android:singleLine`       | `true` / `false` | `singleLine` of `EditText` inside          |
| `android:selectAllOnFocus` | `true` / `false` | Auto select all when open dialog           |
| `android:inputType`        | inputType        | `inputType` of `EditText` inside           |
| `app:commitOnEnter`        | `true` / `false` | Auto click OK when click Enter of keyboard |

Note
----

You must use it in AppCompatActivity.

Download
--------

        allprojects {
            repositories {
                jcenter()
                maven {
                    url "https://jitpack.io"
                    }
                }
        }

        dependencies {
            compile 'com.github.RikkaW:MaterialPreference:v1.2'
        }  

DemoApp
-------

<https://github.com/RikkaW/MaterialPreference/releases>

License
-------

Apache 2.0
