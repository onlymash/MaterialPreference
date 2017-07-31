MaterialPreference
==================

Based on support-preference from Android Support Library, adding a lot of exciting features.

#### Sample

![sample](https://github.com/RikkaW/MaterialPreference/blob/master/art/sample.gif)

#### How to use

1. add to line below into root project ```build.gradle``` 
```
allprojects {
    repositories {
		// add this
        maven { url 'https://dl.bintray.com/rikkaw/MaterialPreference/' }
    }
}
```
2. add dependencies
```
implementation 'moe.shizuku.preference:preference:2.0'
implementation 'moe.shizuku.preference:preference-dialog-android:2.0'
//implementation 'moe.shizuku.preference:preference-dialog-appcompat:2.0' // if you want to use appcompat version dialog
//implementation 'moe.shizuku.preference:preference-simplemenu:2.0' // if you want try SimpleMenuPreference
```