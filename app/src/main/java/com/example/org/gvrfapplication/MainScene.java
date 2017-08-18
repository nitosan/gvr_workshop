package com.example.org.gvrfapplication;

import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRMain;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;

/**
 * The Main Scene of the App
 */
public class MainScene extends GVRMain {

    @Override
    public void onInit(GVRContext gvrContext) throws Throwable {

        //Load texture
        GVRTexture texture = gvrContext.getAssetLoader().loadTexture(new GVRAndroidResource(gvrContext, R.drawable.__default_splash_screen__));

        //Create a rectangle with the texture we just loaded
        GVRSceneObject quad = new GVRSceneObject(gvrContext, 4, 2, texture);
        quad.getTransform().setPosition(0, 0, -3);

        //Add rectangle to the scene
        gvrContext.getMainScene().addSceneObject(quad);
    }

    @Override
    public SplashMode getSplashMode() {
        return SplashMode.NONE;
    }

    @Override
    public void onStep() {
        //Add update logic here
    }
}