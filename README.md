# Android Status Bar
![alt text](https://github.com/panos-stavrianos/StatusBar/blob/master/preview.gif "Preview")
# Usage

```xml
<gr.osnet.statusbar.StatusBar
    android:id="@+id/status_bar"
    android:layout_width="0dp"
    android:layout_height="30dp"
    android:gravity="center"
    android:text="Connected to server!"
    android:textColor="@android:color/white"
    android:textSize="18sp"
    app:bottomLeftRadius="20"
    app:bottomRightRadius="20"
    app:topLeftRadius="0"
    app:topRightRadius="0"
    app:drawableColor="@color/colorPrimary"
   />
```

```kotlin
status_bar.slide(resources.getColor(R.color.colorGreen), newText = "Connected")
```

```kotlin
status_bar.justApply(resources.getColor(R.color.colorGreen), newText = "Connected")
```
