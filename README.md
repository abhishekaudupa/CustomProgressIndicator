# CustomProgressIndicator

[![](https://jitpack.io/v/abhishekaudupa/CustomProgressIndicator.svg)](https://jitpack.io/#abhishekaudupa/CustomProgressIndicator)

A simple progress indicator view for Android written in Kotlin.
![alt text](https://github.com/abhishekaudupa/CustomProgressIndicator/blob/master/device-2020-03-18-165850.png)

![alt text](https://github.com/abhishekaudupa/CustomProgressIndicator/blob/master/device-2020-03-18-212019.gif)
![alt text](https://github.com/abhishekaudupa/CustomProgressIndicator/blob/master/device-2020-03-18-212310.gif)

# Gradle
Dependencies as below

Add to Project Level Gradle file:
```sh
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

Add to Module level Gradle file:
```sh
dependencies {
    implementation 'com.github.abhishekaudupa:CustomProgressIndicator:1.0.0'
}
```
# Usage
Below xml code shows how to use the CustomProgressIndicator.

```sh
<com.abhishekaudupa.android.customprogressindicator.CustomProgressIndicator
        android:layout_width="100dp"
        android:layout_height="100dp"
        app:animationSpeedFactor="70"
        app:animationStyle="@integer/wave"
        app:barColor="@android:color/holo_red_dark"
        app:barCount="5"
        app:barWidthFactor="0.6"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```
- ```app:animationSpeedFactor``` -> Higher the number, slower the animation speed.
- ```app:animationStyle``` -> Two animation styles are available currently: ```@integer/wave``` and ```@integer/spring```. Explore yourselves.
- ```app:barColor``` -> As the name suggests. Default color is ```@color/colorPrimary```.
- ```app:barCount``` -> Number of bars in the equalizer. Default is ```5```. Recommended to keep the count an odd number. Looks good that way.
- ```app:barWidthFactor``` -> Adjusts the width of the bar. Set a number between ```0``` and ```1```. Higher the number, thicker the bar. Default value is ```0.5```.

# Licence

Copyright 2020 Abhishek A Udupa

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
