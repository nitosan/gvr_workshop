/*
 * Copyright 2015 Samsung Electronics Co., LTD
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */

package com.samsung.gearvrf.focus;

import org.gearvrf.GVRSceneObject;

import java.util.ArrayList;

public final class FocusableController {

    public static ArrayList<FocusableSceneObject> interactiveObjects = new ArrayList<FocusableSceneObject>();

    public static void process(GVRSceneObject pickedObject) {
        ArrayList<FocusableSceneObject> needToDisableFocus = new ArrayList<FocusableSceneObject>();

        for (FocusableSceneObject obj : interactiveObjects) {
            needToDisableFocus.add(obj);
        }

        if (pickedObject == null) {
            // GazeController.disableInteractiveCursor();
        } else {
            FocusableSceneObject object = (FocusableSceneObject) pickedObject;
            object.setFocus(true);
            object.dispatchInFocus();
            needToDisableFocus.remove(object);
        }

        for (FocusableSceneObject obj : needToDisableFocus) {
            obj.setFocus(false);
        }

    }

    public static boolean clickProcess(GVRSceneObject pickedObject) {
        if(pickedObject != null) {
            FocusableSceneObject object = (FocusableSceneObject) pickedObject;
            object.dispatchInClick();
            return true;
        }
        return false;
    }

}
