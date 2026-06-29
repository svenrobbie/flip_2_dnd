# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# Keep Dagger/Hilt
-keep class dev.svenrobbie.flip_2_dnd.di.** { *; }
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class javax.annotation.** { *; }

# Retrofit
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }
-keepattributes Signature, InnerClasses, EnclosingMethod
-keepattributes *Annotation*
-dontwarn okhttp3.**
-dontwarn retrofit2.**

# Retrofit 2.x Coroutines support
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembernames class kotlinx.coroutines.android.HandlerContext {
    java.lang.String name;
}

# Preserve Generic Signatures for suspend functions
-keepclassmembers,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-keepattributes Signature
-keep class kotlin.coroutines.Continuation

