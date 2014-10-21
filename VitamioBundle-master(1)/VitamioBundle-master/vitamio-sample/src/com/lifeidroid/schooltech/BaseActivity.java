package com.lifeidroid.schooltech;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BaseActivity extends FragmentActivity {
         
        private ActivityManager manager = ActivityManager.getActivityManager(this);
         
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                manager.putActivity(this);
        }

        @Override
        protected void onDestroy() {
                super.onDestroy();
                manager.removeActivity(this);
        }
         
        public void exit(){
                manager.exit();
        }
         
}
