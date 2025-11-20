-dontwarn com.google.android.gms.common.internal.Hide
-dontwarn com.google.protobuf.java_com_google_ads_interactivemedia_v3__sdk_1p_binary_b0308732GeneratedExtensionRegistryLite$Loader
-dontwarn java.beans.ConstructorProperties
-dontwarn java.beans.Transient
-dontwarn org.slf4j.impl.StaticLoggerBinder
-dontwarn org.slf4j.impl.StaticMDCBinder

# Ticketmaster
-keep class com.ticketmaster.** { *; }
-dontwarn com.ticketmaster.**
-dontwarn org.chromium.net.**
-dontwarn coil.size.Dimension$Original
-dontwarn java.beans.ConstructorProperties
-dontwarn java.beans.Transient
-keep class retrofit2.** { *; }
#  With R8 full mode generic signatures are stripped for classes that are not
#  kept. Suspend functions are wrapped in continuations where the type argument
#  is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
#  R8 full mode strips generic signatures from return types if not kept.
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>
#  With R8 full mode generic signatures are stripped for classes that are not kept.
-keep,allowobfuscation,allowshrinking class retrofit2.Response
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken

# Go Killswitch
-keep class com.mirego.gokillswitch.** { *; }
-keep class com.mirego.gohttp.** { *; }

# Crashlytics
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**

-keepnames class androidx.lifecycle.ViewModel
-keepclassmembers public class * extends androidx.lifecycle.ViewModel { public <init>(...); }
-keepclassmembers class * { public <init>(...); }
-keep class com.mirego.trikot.viewmodels.declarative.** { *; }
-keep public class * extends com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel { *; }
-keep public class * extends com.mirego.trikot.viewmodels.declarative.controller.VMDViewModelController { *; }
-keepclassmembers class * implements com.mirego.trikot.viewmodels.declarative.controller.VMDViewModelControllerFactory { *; }

# KotlinX Serialization
-if @kotlinx.serialization.Serializable class **
-keepclassmembers class <1> {
    static <1>$Companion Companion;
}
-if @kotlinx.serialization.Serializable class ** {
    static **$* *;
}
-keepclassmembers class <2>$<3> {
    kotlinx.serialization.KSerializer serializer(...);
}
-if @kotlinx.serialization.Serializable class ** {
    public static ** INSTANCE;
}
-keepclassmembers class <1> {
    public static <1> INSTANCE;
    kotlinx.serialization.KSerializer serializer(...);
}

# Koin
-keep class org.koin.core.annotation.** { *; }
-keep @org.koin.core.annotation.* class * { *; }
