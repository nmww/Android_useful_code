/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.demo.fragment;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FragmentStack extends Activity {
    int mStackLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_stack);
            
        Button button = (Button)findViewById(R.id.new_fragment);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                addFragmentToStack();
            }
        });
        Button button11 = (Button)findViewById(R.id.lastbutton);
        button11.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	getFragmentManager().popBackStack();
           
               
            }
        });

        if (savedInstanceState == null) {
            // Do first time initialization -- add initial fragment.
            Fragment newFragment = NumberFragment.newInstance(mStackLevel);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.simple_fragment, newFragment).commit();
        } else {
            mStackLevel = savedInstanceState.getInt("level");
        }
        //根据mStackLevel来确定导航按钮是否可见
       if(mStackLevel<2){
        	 Button button1 = (Button)findViewById(R.id.lastbutton);
        	 
        	button1.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("level", mStackLevel);
    }


    void addFragmentToStack() {
        mStackLevel++;
    
        Fragment newFragment = NumberFragment.newInstance(mStackLevel);
        
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.simple_fragment, newFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //以序号为名称加入backstack
        ft.addToBackStack(Integer.toString(mStackLevel));
        ft.commit();
        /////////////////
       Fragment current= getFragmentManager().findFragmentById(R.id.simple_fragment);
       int temp=current.getArguments().getInt("num");
       this.setTitle("当前fragment包含的参数是"+Integer.toString(temp));
        
    }



    public static class NumberFragment extends Fragment {
        int mNum;

        static NumberFragment newInstance(int num) {
            NumberFragment f = new NumberFragment();

            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            return f;
        }

        
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments().getInt("num");
            if(mNum>1){
           	 Button button1 = (Button)getActivity().findViewById(R.id.lastbutton);
           	 button1.setVisibility(View.VISIBLE);
           
           }
        }

       
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.firstfragment, container, false);
            View tv = v.findViewById(R.id.text);
            ((TextView)tv).setText("Fragment #" + mNum);
            tv.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.gallery_thumb));
            return v;
        }
    }
   

}
