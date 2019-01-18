package com.codename1.ext.codescan;

import com.codename1.ui.Display;
import java.util.Map;

public class NativeCodeScannerImpl implements com.codename1.ext.codescan.NativeCodeScanner{
    public void scanQRCode() {
        checkCameraUsageDescription();
    }

    public void scanBarCode() {
        checkCameraUsageDescription();
    }

    public boolean isSupported() {
        checkCameraUsageDescription();
        return false;
    }
    
    private static boolean cameraUsageDescriptionChecked;
    
    private void checkCameraUsageDescription() {
        if (!cameraUsageDescriptionChecked) {
            cameraUsageDescriptionChecked = true;
            
            Map<String, String> m = Display.getInstance().getProjectBuildHints();
            if(m != null) {
                if(!m.containsKey("ios.NSCameraUsageDescription")) {
                    Display.getInstance().setProjectBuildHint("ios.NSCameraUsageDescription", "Some functionality of the application requires access to your camera");
                }
            }
        }
    }

}
