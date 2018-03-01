package com.albertou.study.doubantop250;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Top250Activity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return Top250Fragment.newInstance();
    }

}
