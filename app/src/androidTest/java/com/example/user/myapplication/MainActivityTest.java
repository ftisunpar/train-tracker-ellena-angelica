package com.example.user.myapplication;

import android.graphics.Point;
import android.graphics.PointF;
import android.test.ActivityInstrumentationTestCase2;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.user.myapplication.Fragment.DirectionFragment;
import com.robotium.solo.Solo;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by GeneralDevil X on 4/25/2018.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo solo;

    public MainActivityTest() {
        super(MainActivity.class);
    }


    @Override
    protected void setUp()throws Exception{
        super.setUp();
        solo = new Solo(getInstrumentation(),getActivity());
    }

    public void test(){
        //implementasi untuk automated testing

        //Asumsi GPS dan Internet nyala. Belom nanganin kl ada dialog GPS atau network

        ArrayList<Spinner> spin = solo.getCurrentViews(Spinner.class);
        Spinner kereta = spin.get(0);

        for (int i = 0;i< kereta.getCount();i++){
            solo.pressSpinnerItem(0,1);
            solo.sleep(1000);

            spin = solo.getCurrentViews(Spinner.class);
            Spinner asal = spin.get(1);

            for (int j = 0;j< asal.getCount();j++){
                Spinner akhir = spin.get(2);
                for (int k = 0;k< akhir.getCount();k++){
                    solo.clickOnButton("Set Stasiun");
                    solo.sleep(2000);
                    solo.pressSpinnerItem(2,-1);
                    solo.sleep(1000);
                }
                solo.pressSpinnerItem(1,1);
                solo.sleep(1000);
                spin = solo.getCurrentViews(Spinner.class);
            }
        }

        //pilih kereta sm stasiun

        /**
        solo.pressSpinnerItem(0,20);
        solo.sleep(1000);
        solo.pressSpinnerItem(1,3);
        solo.sleep(1000);
        solo.pressSpinnerItem(2,0);
        solo.sleep(1000);
        solo.clickOnButton("Set Stasiun");
        solo.sleep(4000);
        solo.sleep(1000);
         */

        //schedule
        swipeRight();
        solo.sleep(1000);
        solo.clickOnText("Jadwal");
        solo.sleep(1000);

        EditText edit = (EditText) solo.getView(R.id.et_train_search);
        solo.enterText(edit,"argo");
        solo.sleep(1000);
        solo.clearEditText(edit);
        solo.sleep(1000);
        solo.enterText(edit,"16");
        solo.sleep(1000);
        solo.clearEditText(edit);
        solo.enterText(edit,"yohoho");
        solo.sleep(1000);
        solo.clearEditText(edit);
        solo.enterText(edit,"argo");
        solo.sleep(1000);
        solo.scrollDown();
        solo.scrollToTop();
        solo.sleep(1000);
        solo.clickOnText("Argo Bromo Anggrek KA 1");
        View view = solo.getView("iv_get_schedule_btn");
        solo.clickOnView(view);
        solo.sleep(2000);

        //alarm

        swipeRight();
        solo.sleep(1000);
        solo.clickOnText("Alarm");
        solo.sleep(1000);


        solo.clickOnText("Pilih Mode");
        solo.sleep(1000);
        solo.clickOnText("Mode getar saja");
        solo.sleep(1000);
        solo.clickOnText("Mode suara saja");
        solo.sleep(1000);
        solo.clickOnText("Mode suara dan getar saja");
        solo.sleep(1000);
        solo.goBack();

        solo.clickOnText("Pilih Ringtone");
        solo.sleep(1000);
        solo.clickOnText("Lucky1");
        solo.sleep(1000);
        solo.clickOnText("Lucky2");
        solo.sleep(1000);
        solo.goBack();

        //nyalain alarm
        swipeRight();
        solo.sleep(1000);
        solo.clickOnText("Peta");
        solo.sleep(1000);
        solo.clickOnToggleButton("Alarm");
        solo.sleep(1000);
        solo.clickOnToggleButton("Alarm");

        //cek speed
        swipeRight();
        solo.sleep(1000);
        solo.clickOnText("Cek Kecepatan");
        solo.sleep(6000);

        solo.goBack();
        solo.clickOnText("Tidak");
        solo.goBack();
        solo.clickOnText("Ya");

    }

    private void swipeRight(){
        Display display = solo.getCurrentActivity().getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        float xStart = 0;
        float xEnd = width/2;
        solo.drag(xStart,xEnd,height/2,height/2,1);
    }


    public void tearDown() throws Exception{
        try{
            solo.finalize();
        }
        catch(Throwable t){
            t.printStackTrace();
        }
        getActivity().finish();
        super.tearDown();
    }
}