package com.samsung.gearvrf.gaze;

import org.gearvrf.GVRContext;

/**
 * Created by nite.luo on 8/28/2017.
 */

public class GVRControllerManager {

    private static GazeCursorSceneObject sInstance;

    public static void enableController(GVRContext gvrContext) {
        if (sInstance == null) {
            sInstance = new GazeCursorSceneObject(gvrContext);
        }

        gvrContext.getMainScene().getMainCameraRig().addChildObject(sInstance);
    }
}
