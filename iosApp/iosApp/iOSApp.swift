import SwiftUI
import SharedSDK

@main
struct iOSApp: App {
    
    init() {
        PlatformSDK().doInit(configuration: PlatformConfiguration())
        #if DEBUG
        var injectionBundlePath = "/Applications/InjectionIII.app/Contents/Resources"
        #if targetEnvironment(macCatalyst)
        injectionBundlePath = "\(injectionBundlePath)/macOSInjection.bundle"
        #elseif os(iOS)
        injectionBundlePath = "\(injectionBundlePath)/iOSInjection.bundle"
        #endif
        Bundle(path: injectionBundlePath)?.load()
        #endif
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
