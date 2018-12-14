package com.coderstar.baseapp.datepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.coderstar.basemodule.activity.ToolBarActivity;
import com.coderstar.basemodule.listener.DatePickListener;
import com.coderstar.basemodule.view.DatePickDialog;
import com.coderstar.baseapp.R;

public class DatePickerActivity extends ToolBarActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_date_picker;
    }

    @Override
    protected void initView () {

    }

    @Override
    protected void initEvents () {

    }

    @Override
    protected void loadData () {

    }

    public void onClick (View view) {
        switch (view.getId ()){
            case R.id.date_pick_btn:
                pickDate(true);
                break;
            case R.id.only_date_pick_btn:
                pickDate (false);
                break;
        }
    }

    private void pickDate(final boolean showTime){
        new DatePickDialog (this,showTime).builder().setTitle("选择日期")
            .setPositiveButton("确认", new DatePickListener () {
                @Override
                public void onClick(View v) {
                    Log.d ("LikeIosDialogActivity","dataValue = " + this.getDateValue () + " " + this.getTimeValue ());
                    if(showTime){
                        Toast.makeText (DatePickerActivity.this, this.getDateValue () + " " + this.getTimeValue () + "",Toast.LENGTH_LONG).show ();
                    }else{
                        Toast.makeText (DatePickerActivity.this, this.getDateValue (),Toast.LENGTH_LONG).show ();
                    }

                }
            }).setNegativeButton("取消", new DatePickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }
}
