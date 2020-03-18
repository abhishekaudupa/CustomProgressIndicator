# CustomProgressIndicator

[![](https://jitpack.io/v/abhishekaudupa/CustomProgressIndicator.svg)](https://jitpack.io/#abhishekaudupa/CustomProgressIndicator)

A simple progress indicator view for Android written in Kotlin.

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
- ```app:animationStyle``` -> Two animation styles are available currently: @integer/wave and @integer/spring. Explore yourselves.
- ```app:barColor``` -> As the name suggests. Default color is @color/colorPrimary
- ```app:barCount``` -> Number of bars in the equalizer. Default is 5.
- ```app:barWidthFactor``` -> Adjusts the width of the bar. Set a number between ```0``` and ```1```. Higher the number, thicker the bar. Default value is ```0.5```.
