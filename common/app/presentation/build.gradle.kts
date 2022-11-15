plugins {
    id("android-setup")
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:app:api"))
                implementation(project(":common:core"))
                api(Dependencies.MokoMVVM.core)
                api(Dependencies.MokoMVVM.livedata)
                api(Dependencies.MokoMVVM.livedata_resources)
                api(Dependencies.MokoMVVM.state)
                implementation(Dependencies.MokoMVVM.flow)
                implementation(Dependencies.MokoMVVM.flow_resources)
            }
        }
        androidMain {
            dependencies {
                implementation(Dependencies.MokoMVVM.Android.databinding)
                implementation(Dependencies.MokoMVVM.Android.flow_compose)
                implementation(Dependencies.MokoMVVM.Android.livedata_compose)
                implementation(Dependencies.MokoMVVM.Android.livedata_glide)
                implementation(Dependencies.MokoMVVM.Android.livedata_material)
                implementation(Dependencies.MokoMVVM.Android.livedata_swiperefresh)
                implementation(Dependencies.MokoMVVM.Android.viewbinding)
            }
        }
    }
}
