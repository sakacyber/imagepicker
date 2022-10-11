# ImagePicker

[![](https://jitpack.io/v/sakacyber/imagepicker.svg)](https://jitpack.io/#sakacyber/imagepicker)

**Android image picker** \
Show bottom sheet of image picker and handle app permission \
Update and continue improvement to adapt new privacy rule of new android release

## Quick Start

**ImagePicker** is available on jitpack.

Add dependency: build.gradle(:app)
```
implementation "com.github.sakacyber:imagepicker:0.1.2"
```

## Usage
to use **ImagePicker**:

Manifest.xml
``` 
        <application 
        ...
        
            <provider
                android:name="androidx.core.content.FileProvider"
                android:authorities="${applicationId}.provider"
                android:exported="false"
                android:grantUriPermissions="true">
                <meta-data
                    android:name="android.support.FILE_PROVIDER_PATHS"
                    android:resource="@xml/ext_file_path" />
            </provider>
        
         </application>
```

Single image picker
```
ImagePicker.Builder("${packageName}.provider")
            .setListener(this)
            .setSpanCount(3)
            .build()
            .show(supportFragmentManager, "picker")
```

Multiple image picker
```
ImagePicker.Builder("${packageName}.provider")
            .setListener(this)
            .isMultiSelect()
            .setSpanCount(3)
            .setMinSelectCount(3)
            .build()
            .show(supportFragmentManager, "picker")
```

Image picker listener 
```
    override fun onCancelled(isMultiSelecting: Boolean) {
        Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
    }

    override fun onSingleSelect(uri: Uri?) {
       //
    }

    override fun onMultipleSelect(uriList: List<Uri>?) {
       // 
    }
```