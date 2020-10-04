# ImagePicker

[![](https://jitpack.io/v/SakaGamer/imagepicker.svg)](https://jitpack.io/#SakaGamer/imagepicker)

Just image picker

Update and contiue improvement to adapter new privacy rule of new android release 

## Quick Start
**ImagePicker** is availble on jitpack.

Add dependency:

```
implementation 'com.github.SakaGamer:imagepicker:0.1.0'
```

## Usage
to use **ImagePicker**:

```
// Single pick
// In Activity or Fragment

ImagePicker.Builder("here.your.fileprovider")
            .setListener(this)
            .setSpanCount(3)
            .build()
            .show(supportFragmentManager, "picker")
```

```
// Multi pick
// In Activity or Fragment

ImagePicker.Builder("here.your.fileprovider")
            .setListener(this)
            .isMultiSelect()
            .setSpanCount(3)
            .setMinSelectCount(3)
            .build()
            .show(supportFragmentManager, "picker")
```
