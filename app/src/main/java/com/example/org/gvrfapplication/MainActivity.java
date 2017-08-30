package com.example.org.gvrfapplication;

import android.os.Bundle;
import android.util.Log;

import com.samsung.gearvrf.gaze.GVRControllerManager;

import org.gearvrf.GVRActivity;
import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRMain;
import org.gearvrf.GVRMaterial;
import org.gearvrf.GVRMesh;
import org.gearvrf.GVRPhongShader;
import org.gearvrf.GVRPicker;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRSphereCollider;
import org.gearvrf.GVRTexture;
import org.gearvrf.IPickEvents;
import org.gearvrf.scene_objects.GVRCubeSceneObject;

public class MainActivity extends GVRActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Set Main Scene
         * It will be displayed when app starts
         */
        setMain(new Main());

    }

    private final class Main extends GVRMain {

        private GVRSceneObject mCube;
        private IPickEvents mPickHandler = new PickHandler();
        private GVRPicker mPicker;
        private GVRSceneObject pickedObject = null;

        public class PickHandler implements IPickEvents
        {
            public void onEnter(GVRSceneObject sceneObj, GVRPicker.GVRPickedObject pickInfo){}
            public void onExit(GVRSceneObject sceneObj){}
            public void onNoPick(GVRPicker picker) {

                if (pickedObject != null) {
                    pickedObject.getRenderData().getMaterial().setDiffuseColor(1, 1, 1, 1);
                }

                pickedObject = null;
            }
            public void onPick(GVRPicker picker) {
                GVRSceneObject obj = picker.getPicked()[0].getHitObject();
                if(pickedObject != obj) {
                    pickedObject = obj;
                    GVRMaterial m = pickedObject.getRenderData().getMaterial();
                    String name = pickedObject.getName();
                    pickedObject.getRenderData().getMaterial().setDiffuseColor(1, 0, 0, 1);
                    Log.i("DEBUG", obj.getName());
                }
            }
            public void onInside(GVRSceneObject sceneObj, GVRPicker.GVRPickedObject pickInfo) { }
        }

        @Override
        public void onInit(GVRContext gvrContext) throws Throwable {

            GVRSceneObject env = createEnv();
            env.getTransform().rotateByAxisWithPivot(-90, 1, 0, 0, 0, 0, 0);
            env.getTransform().setPosition(0, -1.6f, 0);
            gvrContext.getMainScene().addSceneObject(env);

            GVRSceneObject dino = createDino();
            gvrContext.getMainScene().addSceneObject(dino);

            mCube = createCube();
            mCube.getTransform().setPosition(.4f, 0, -2);
            mCube.getTransform().setScale(0.5f, 0.5f, 0.5f);
            gvrContext.getMainScene().addSceneObject(mCube);
            mCube.setName("TestCube");

//            GVRBoxCollider boxCollider = new GVRBoxCollider(gvrContext);
//            boxCollider.setHalfExtents(0.5f, 0.5f, 0.5f);
//            mCube.attachCollider(boxCollider);


            GVRSphereCollider collider = new GVRSphereCollider(gvrContext);
            collider.setRadius(1.0f);
            mCube.attachCollider(collider);

            //Enable gaze cursor
            GVRControllerManager.enableController(gvrContext);

            //Picker
            mPicker = new GVRPicker(gvrContext, gvrContext.getMainScene());

            gvrContext.getMainScene().getEventReceiver().addListener(mPickHandler);
        }

        @Override
        public void onStep() {
            //Add update logic here
            mCube.getTransform().rotateByAxis(1, 0, 1, 0);
        }

        private GVRCubeSceneObject createCube(){
            GVRCubeSceneObject cube = new GVRCubeSceneObject(getGVRContext());
            cube.getRenderData().setShaderTemplate(GVRPhongShader.class);
            return cube;
        }

        private GVRSceneObject createMesh(int meshID, int textureID) {
            GVRMesh mesh = getGVRContext().getAssetLoader().loadMesh(new GVRAndroidResource(getGVRContext(), meshID));
            GVRTexture texture = getGVRContext().getAssetLoader().loadTexture(new GVRAndroidResource(getGVRContext(), textureID));
            GVRSceneObject sceneObject = new GVRSceneObject(getGVRContext(), mesh, texture);

            return sceneObject;
        }

        private GVRSceneObject createEnv(){

            GVRSceneObject envBuilding = createMesh(R.raw.environment_walls_mesh, R.raw.environment_walls_tex_diffuse);

            GVRSceneObject envGround = createMesh(R.raw.environment_ground_mesh, R.raw.environment_ground_tex_diffuse);

            GVRSceneObject envWindow = createMesh(R.raw.windows_fx_mesh, R.raw.windows_fx_tex_diffuse);

            envBuilding.addChildObject(envWindow);
            envBuilding.addChildObject(envGround);
            return envBuilding;
        }

        private GVRSceneObject createDino() {
            GVRSceneObject trex = createMesh(R.raw.t_rex_mesh, R.raw.t_rex_texture_diffuse);
            trex.getTransform().setPosition(0, -1.6f, -7f);
            trex.getTransform().rotateByAxis(-90, 1, 0, 0);
            trex.getTransform().rotateByAxis(90, 0, 1, 0);
            return trex;
        }

    }
}
