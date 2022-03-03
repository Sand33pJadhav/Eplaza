package com.example.eplaza;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eplaza.R;
import com.google.android.filament.Colors;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Pose;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;


public class Sceneform_chandelier2_activity extends AppCompatActivity {
    private static final String TAG = Sceneform_chandelier_activity.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;

    private ArFragment arFragment;
    private ModelRenderable andyRenderable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }

        setContentView(R.layout.activity_sceneform_chandelier_activity);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);


        // When you build a Renderable, Sceneform loads its resources in the background while returning
        // a CompletableFuture. Call thenAccept(), handle(), or check isDone() before calling get().
        ModelRenderable.builder()
                .setSource(this, R.raw.c2)
                .build()
                .thenAccept(renderable -> andyRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast toast =
                                    Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            return null;
                        });

        arFragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                    if (andyRenderable == null) {
                        return;
                    }

                    // Create the Anchor.
                    float[] pos = { 0,0,-1 };
                    float[] rotation = {0,0,0,1};
                    Anchor anchor =  hitResult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());


                    // Create the transformable andy and add it to the anchor.
                    TransformableNode andy = new TransformableNode(arFragment.getTransformationSystem());
                    andy.setParent(anchorNode);
                    andy.setRenderable(andyRenderable);
                    andy.select();
                });
    }

    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (Build.VERSION.SDK_INT < VERSION_CODES.N) {
            Log.e(TAG, "Sceneform requires Android N or later");
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                    .show();
            activity.finish();
            return false;
        }
        return true;
    }
}

//public class Sceneform_chandelier_activity extends AppCompatActivity implements View.OnClickListener {
//    private static final String TAG = Sceneform_chandelier_activity.class.getSimpleName();
//    private static final double MIN_OPENGL_VERSION = 3.0;
//    ArFragment arFragment;
//    private ModelRenderable chandelierRenderable,
//                            chairRenderable,
//                            tableRenderable,
//                            sofaRenderable;
//
//    ImageView table,chair,chandelier,sofa;
//
//    View arrayView[];
//
//    int selected = 1;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (!checkIsSupportedDeviceOrFinish(this)) {
//            return;
//        }
//        setContentView(R.layout.activity_sceneform_chandelier_activity);
//
//        arFragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
//
//        table = (ImageView)findViewById(R.id.tablem);
//        chandelier = (ImageView)findViewById(R.id.chandelierm);
//        sofa = (ImageView)findViewById(R.id.sofam);
//        chair = (ImageView)findViewById(R.id.chairm);
//
//        setArrayView();
//        setClickListener();
//        setupModel();
//
//        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
//            @Override
//            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
//                if(selected==1){
//                    Anchor anchor = hitResult.createAnchor();
//                    AnchorNode anchorNode = new AnchorNode(anchor);
//                    anchorNode.setParent(arFragment.getArSceneView().getScene());
//
//                    createModel (anchorNode,selected);
//                }
//                else if(selected==2){
//                    Anchor anchor = hitResult.createAnchor();
//                    AnchorNode anchorNode = new AnchorNode(anchor);
//                    anchorNode.setParent(arFragment.getArSceneView().getScene());
//
//                    createModel (anchorNode,selected);
//                }
//                else if(selected==3){
//                    Anchor anchor = hitResult.createAnchor();
//                    AnchorNode anchorNode = new AnchorNode(anchor);
//                    anchorNode.setParent(arFragment.getArSceneView().getScene());
//
//                    createModel (anchorNode,selected);
//                }
//                else if(selected==4){
//                    Anchor anchor = hitResult.createAnchor();
//                    AnchorNode anchorNode = new AnchorNode(anchor);
//                    anchorNode.setParent(arFragment.getArSceneView().getScene());
//
//                    createModel (anchorNode,selected);
//                }
//            }
//        });
//    }
//
//    private void setupModel() {
//        ModelRenderable.builder()
//                .setSource(this, R.raw.desk)
//                .build()
//                .thenAccept(renderable -> tableRenderable = renderable)
//                .exceptionally(
//                        throwable -> {
//                            Toast toast =
//                                    Toast.makeText(this, "Unable to load table  ", Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
//                            return null;
//                        });
//        ModelRenderable.builder()
//                .setSource(this, R.raw.chair)
//                .build()
//                .thenAccept(renderable -> chairRenderable = renderable)
//                .exceptionally(
//                        throwable -> {
//                            Toast toast =
//                                    Toast.makeText(this, "Unable to load chair  ", Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
//                            return null;
//                        });
//
//        ModelRenderable.builder()
//                .setSource(this, R.raw.model)
//                .build()
//                .thenAccept(renderable -> sofaRenderable = renderable)
//                .exceptionally(
//                        throwable -> {
//                            Toast toast =
//                                    Toast.makeText(this, "Unable to load sofa  ", Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
//                            return null;
//                        });
//
//        ModelRenderable.builder()
//                .setSource(this, R.raw.chandelier)
//                .build()
//                .thenAccept(renderable -> chandelierRenderable = renderable)
//                .exceptionally(
//                        throwable -> {
//                            Toast toast =
//                                    Toast.makeText(this, "Unable to load chandelier  ", Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
//                            return null;
//                        });
//
//    }
//
//    private void createModel(AnchorNode anchorNode, int selected) {
//        if(selected==1)
//        {
//            TransformableNode table = new TransformableNode(arFragment.getTransformationSystem());
//            table.setParent(anchorNode);
//            table.setRenderable(tableRenderable);
//            table.select();
//        }
//        else if(selected==2)
//        {
//            TransformableNode chair = new TransformableNode(arFragment.getTransformationSystem());
//            chair.setParent(anchorNode);
//            chair.setRenderable(chairRenderable);
//            chair.select();
//        }
//        else if(selected==3)
//        {
//            TransformableNode sofa = new TransformableNode(arFragment.getTransformationSystem());
//            sofa.setParent(anchorNode);
//            sofa.setRenderable(sofaRenderable);
//            sofa.select();
//        }
//        else if(selected==4)
//        {
//            TransformableNode chandelier = new TransformableNode(arFragment.getTransformationSystem());
//            chandelier.setParent(anchorNode);
//            chandelier.setRenderable(chandelierRenderable);
//            chandelier.select();
//        }
//    }
//
//    private void setClickListener(){
//        for(int i=0;i<arrayView.length;i++){
//            arrayView[i].setOnClickListener(this);
//        }
//    }
//
//    private void setArrayView(){
//        arrayView = new View[]{table,chair,sofa,chandelier};
//
//    }
//
//    @Override
//    public void onClick(View view){
//        if(view.getId() == R.id.table){
//            selected = 1;
//            //setBackground(view.getId());
//        }
//        else if(view.getId() == R.id.chair){
//            selected = 2;
//            //setBackground(view.getId());
//        }
//        else if(view.getId() == R.id.sofa){
//            selected = 3;
//            //setBackground(view.getId());
//        }
//        else if(view.getId() == R.id.chandelier){
//            selected = 4;
//            //setBackground(view.getId());
//        }
//    }
//
////    private void setBackground(int id) {
////        for(int i=0;i<arrayView.length;i++){
////            if(arrayView[i].getId()==id) {
////                arrayView[i].setBackgroundColor(0x803336);
////            }
////            else {
////                arrayView[i].setBackgroundColor(Integer.parseInt("0x803336"));
////            }
////        }
////    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
//    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
//        if (Build.VERSION.SDK_INT < VERSION_CODES.N) {
//            Log.e(TAG, "Sceneform requires Android N or later");
//            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
//            activity.finish();
//            return false;
//        }
//
//        String openGlVersionString =
//                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
//                        .getDeviceConfigurationInfo()
//                        .getGlEsVersion();
//        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
//            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
//            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
//                    .show();
//            activity.finish();
//            return false;
//        }
//        return true;
//    }
//}