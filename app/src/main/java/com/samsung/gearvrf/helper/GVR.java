package com.samsung.gearvrf.helper;

import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRMesh;
import org.gearvrf.GVRPhongShader;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;
import org.gearvrf.scene_objects.GVRCubeSceneObject;

/**
 * Created by nite.luo on 8/28/2017.
 */

public class GVR {
    private static GVRContext s_Context = null;

    public static void init(GVRContext context) {
        s_Context = context;
    }

    public static GVRCubeSceneObject createCube(){
        GVRCubeSceneObject cube = new GVRCubeSceneObject(s_Context);
        cube.getRenderData().setShaderTemplate(GVRPhongShader.class);
        return cube;
    }

    public static GVRSceneObject createMesh(int meshID, int textureID) {
        GVRMesh mesh = s_Context.getAssetLoader().loadMesh(new GVRAndroidResource(s_Context, meshID));
        GVRTexture texture = s_Context.getAssetLoader().loadTexture(new GVRAndroidResource(s_Context, textureID));
        GVRSceneObject sceneObject = new GVRSceneObject(s_Context, mesh, texture);

        return sceneObject;
    }
}
