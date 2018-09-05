[![Release](https://jitpack.io/v/User/Repo.svg)](https://jitpack.io/#panos-stavrianos/StatusBar)

# Android Status Bar (Under Development)
![alt text](https://github.com/panos-stavrianos/StatusBar/blob/master/preview.gif "Preview")

## Import to your project
Put this into your `app/build.gradle`:
```
repositories {
  maven {
    url "https://jitpack.io"
  }
}

dependencies {
    implementation 'com.github.panos-stavrianos:StatusBar:0.0.6'
}
```
# Usage
StatusBar is actually a TextView with the ability to have round corners and slide up and down.

The added attributes are:
1. bottomLeftRadius :Dimension
2. bottomRightRadius :Dimension
3. topLeftRadius :Dimension
4. topRightRadius :Dimension
5. drawableColor :Color
6. showOnStart :Boolean

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
//slide up then down with the new values
status_bar.slideUpDown(resources.getColor(R.color.colorGreen),resources.getColor(R.color.white)  "Connected")

//slide down then up with the new values
status_bar.slideDownUp(resources.getColor(R.color.colorGreen),resources.getColor(R.color.white)  "Connected")

//slide up and hide
status_bar.hideUp()

//slide down and hide
status_bar.hideDown()
```
# License

      Copyright (c) 2018 panos-stavrianos

      Licensed under the Apache License, Version 2.0 (the "License");
      you may not use this file except in compliance with the License.
      You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

      Unless required by applicable law or agreed to in writing, software
      distributed under the License is distributed on an "AS IS" BASIS,
      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
      See the License for the specific language governing permissions and
      limitations under the License.

