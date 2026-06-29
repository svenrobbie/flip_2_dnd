# Flip 2 DND

<p align="center">
  <b>A modern, open-source Android utility to automate your focus.</b>
</p>

---

## 📖 About This Fork

The original **Flip 2 DND** is a great app — it intelligently toggles Do Not Disturb mode when you flip your phone face down. Super handy!

Unfortunately, the original developer locked the most useful features behind a **paywall**. As a big believer in **#FOSS** (Free and Open Source Software), I forked the project and **removed the paywall entirely**.

**This version gives you every feature, fully unlocked, for free. Forever.**

The code is MIT licensed — use it, share it, improve it.

---

## 📋 What's Changed (Since the Fork)

A summary of all improvements made in this fork beyond simply removing the paywall:

### 🔋 v13.0.0 — Battery & Performance Overhaul

- **Replaced polling with BroadcastReceiver**: `DndRepository` no longer polls every second — it listens for `ACTION_INTERRUPTION_FILTER_CHANGED` broadcasts instead.
- **Reduced sensor sampling rate**: From `SENSOR_DELAY_UI` to `SENSOR_DELAY_NORMAL`, significantly lowering CPU wake-ups.
- **Fixed wake lock leak**: `wakeLock.release()` was missing in `onDestroy()` — now properly released.
- **Slashed service restarts**: From 52 down to 1 — only restarted when flip sensitivity actually changes.
- **Removed duplicate receiver**: Consolidated redundant screen-state broadcast receivers.

### ⚡ ANR & Coroutine Leak Fixes

- **Live sensor sensitivity**: Now reads from settings in real-time instead of a hardcoded `0.5f`.
- **Eliminated ANR**: Removed `runBlocking { delay(2000) }` that was blocking the main thread.
- **Fixed coroutine scope leak**: `SensorService.cancel()` is now called in `onDestroy()`.
- **Dead code removal**: Cleared out unused `highSensitivityMode` / `schedule` fields and collectors.
- **runBlocking → withContext**: All blocking calls in `DndService` migrated to proper `suspend`/`withContext(IO)`.
- **Suspend-friendly SoundService**: `playDndSound()` is now a `suspend` function.
- **Cleaner coroutines**: Ad-hoc `CoroutineScope(IO).launch` replaced with `withContext(IO)`.
- **Removed unused dependency**: `feedbackRepository` cleaned from `MainViewModel`.

### 🏗️ Hilt Dependency Injection (ServiceLocator Removed)

- **Full DI migration**: Replaced the static `ServiceLocator` singleton with proper Hilt `@Inject` / `@Provides`.
- **No more static context references**: Each component gets its context through proper Hilt scoping (SingletonComponent, ServiceC).
- **Better garbage collection**: Objects are no longer pinned by static references; they can be collected when no longer needed.
- **Deleted `ServiceLocator.kt`**: The entire 54-line static locator is gone.

### 🔓 v12.2.0 — Freedom Edition

- All premium features unlocked for everyone
- Package renamed to `dev.svenrobbie.flip_2_dnd`
- All paywall and Pro references removed from code, UI, and strings
- Flash controller migrated to coroutines
- Custom sound support via file picker
- Battery saver toggle via `Settings.Global`

---

## 🚀 Features (All Unlocked)

- 🔄 **Intelligent Flip Detection**: Automatically toggles DND mode based on phone orientation.
- ⏱️ **Full Activation Delay**: Configure timing from 0 to 10 seconds.
- 📅 **Advanced Scheduling**: Set DND, Sound, and Vibration schedules for different times and days.
- 📁 **Custom Sound Support**: Use any sound file from your device for DND notifications.
- 🚀 **Auto Start on Boot**: Service starts automatically after device reboot.
- 🔋 **Battery Saver Sync**: Automatically enable Battery Saver when flipped (Requires ADB permission).
- 🔍 **DND Cancellation Filters**: Prevent DND activation when flashlight is on, media is playing, or headphones are connected.
- 🎨 **Modern Material 3 UI**: Built with Jetpack Compose, with dynamic "Material You" theming.
- 🔔 **Custom Feedback**: Personalize with custom vibration patterns, sounds, and flashlight feedback.
- 🌑 **Dark Mode Support**: Fully optimized for both light and dark themes.
- 🛠️ **Quick Settings Tile**: Control the service directly from your notification shade.
- 🔒 **Privacy Focused**: No tracking, no ads, and minimal permissions.

---

## ⚡ Battery Saver Setup (Optional)

To enable automatic Battery Saver toggling, grant the `WRITE_SECURE_SETTINGS` permission via ADB:

```bash
adb shell pm grant dev.svenrobbie.flip_2_dnd android.permission.WRITE_SECURE_SETTINGS
```

---

## 📥 Installation

Download the latest APK from the [Releases](https://github.com/svenrobbie/Flip_2_DND/releases) page.

---

## 🛠️ Building from Source

```bash
git clone https://github.com/svenrobbie/Flip_2_DND.git
cd Flip_2_DND
./gradlew assembleDebug
```

APK will be at `app/build/outputs/apk/debug/app-debug.apk`.

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
