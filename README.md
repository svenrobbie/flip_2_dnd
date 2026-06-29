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

Download the latest APK from the [Releases](https://github.com/YOUR_USERNAME/Flip_2_DND/releases) page.

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
