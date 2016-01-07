package com.jeck.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.jeck.demo.R;
import com.jeck.demo.view.ArcDiagramView;

/**
 * Author:liyang
 * Email:jidishengguang123@163.com
 * Date: 2016-01-07
 * Time: 16:10
 * Description:
 */
public class ArcDiagramActivity extends Activity{
    private ArcDiagramView mArcDiagram;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_arcdiagram);
        mArcDiagram = (ArcDiagramView) findViewById(R.id.arc_diagram);
//        mArcDiagram.setSweepValue(270);
    }
}
