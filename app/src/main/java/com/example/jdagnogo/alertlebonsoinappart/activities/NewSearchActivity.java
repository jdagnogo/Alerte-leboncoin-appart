package com.example.jdagnogo.alertlebonsoinappart.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.customlibs.SmoothCheckBox;
import com.example.jdagnogo.alertlebonsoinappart.enums.*;
import com.example.jdagnogo.alertlebonsoinappart.models.NewSearchView;
import com.example.jdagnogo.alertlebonsoinappart.models.swipeItem.NbRoomSwipeItem;
import com.example.jdagnogo.alertlebonsoinappart.models.swipeItem.RentSwipeItem;
import com.example.jdagnogo.alertlebonsoinappart.models.swipeItem.SurfaceSwipeItem;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.GlobalBus;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.UpdateSwipeViewBus;
import com.example.jdagnogo.alertlebonsoinappart.utils.MinMaxAlertDialog;

import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewSearchActivity extends AppCompatActivity {
    @Bind(R.id.rent_min)
    TextView rentMin;
    @Bind(R.id.rent_max)
    TextView rentMax;
    @Bind(R.id.surface_min)
    TextView surfaceMin;
    @Bind(R.id.surface_max)
    TextView surfaceMax;
    @Bind(R.id.nb_room_min)
    TextView nbRoomMin;
    @Bind(R.id.nb_room_max)
    TextView nbRoomMax;
    private NewSearchView newSearchView;
    @Bind(R.id.validate)
    Button validate;
    SmoothCheckBox appart,house,meuble,nonMeuble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_search);
        ButterKnife.bind(this);
        newSearchView = new NewSearchView();
        GlobalBus.getBus().register(this);
        initLayout();
        initCheckBoxes();

    }

    private void initCheckBoxes() {
        appart = (SmoothCheckBox) findViewById(R.id.appart);
        house = (SmoothCheckBox) findViewById(R.id.house);
        meuble = (SmoothCheckBox) findViewById(R.id.meuble);
        nonMeuble = (SmoothCheckBox) findViewById(R.id.non_meuble);

    }

    @OnClick(R.id.validate)
    public void setOnclickValidate(){
       // save
        if (house.isChecked()){
            if (appart.isChecked()){
                newSearchView.setType(Type.APPARTEMENT_DEFAULT);
            }else {
                newSearchView.setType(Type.MAISON);
            }
        }else {
            newSearchView.setType(Type.APPARTEMENT);
        }
        if (nonMeuble.isChecked()){
            if (appart.isChecked()){
                newSearchView.setMeuble(Meuble.MEUBLE_DEFAULT);
            }else {
                newSearchView.setMeuble(Meuble.NON_MEUBLE);
            }
        }else {
            newSearchView.setMeuble(Meuble.MEUBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GlobalBus.getBus().unregister(this);
    }

    private void initLayout() {
        LinearLayout rentLayout = (LinearLayout) findViewById(R.id.rent);
        rentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RentSwipeItem rentSwipeItem = new RentSwipeItem();
                MinMaxAlertDialog minMaxAlertDialog = new MinMaxAlertDialog(rentSwipeItem.createBeans(), NewSearchActivity.this, SwipeItemEnum.RENT,newSearchView.getRent(),
                        rentSwipeItem.getPostitonMin(),rentSwipeItem.getPositionMax());
                minMaxAlertDialog.createDialog();
            }
        });

        LinearLayout surfaceLayout = (LinearLayout) findViewById(R.id.surface);
        surfaceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SurfaceSwipeItem surfaceSwipeItem = new SurfaceSwipeItem();
                MinMaxAlertDialog minMaxAlertDialog = new MinMaxAlertDialog(surfaceSwipeItem.createBeans(), NewSearchActivity.this,SwipeItemEnum.SURFACE,newSearchView.getSurface()
                ,surfaceSwipeItem.getPostitonMin(),surfaceSwipeItem.getPositionMax());
                minMaxAlertDialog.createDialog();
            }
        });

        LinearLayout nbRoomLayout = (LinearLayout) findViewById(R.id.nb_room);
        nbRoomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NbRoomSwipeItem nbRoomSwipeItem = new NbRoomSwipeItem();
                MinMaxAlertDialog minMaxAlertDialog = new MinMaxAlertDialog(nbRoomSwipeItem.createBeans(), NewSearchActivity.this,SwipeItemEnum.NB_ROOM,newSearchView.getNbRoom(),
                nbRoomSwipeItem.getPostitonMin(),nbRoomSwipeItem.getPositionMax());
                minMaxAlertDialog.createDialog();
            }
        });
    }

    @Subscribe
    public void getMessage(UpdateSwipeViewBus updateSwipeViewBus) {
        switch (updateSwipeViewBus.getSwipeItemEnum()){
            case RENT:
                newSearchView.getRent().setPositionMax(updateSwipeViewBus.getMax());
                newSearchView.getRent().setPostitonMin(updateSwipeViewBus.getMin());
                rentMin.setText(newSearchView.getRent().getDescriptionMin());
                rentMax.setText(newSearchView.getRent().getDescriptionMax());

                break;
            case NB_ROOM:
                newSearchView.getNbRoom().setPositionMax(updateSwipeViewBus.getMax());
                newSearchView.getNbRoom().setPostitonMin(updateSwipeViewBus.getMin());
                nbRoomMin.setText(newSearchView.getNbRoom().getDescriptionMin());
                nbRoomMax.setText(newSearchView.getNbRoom().getDescriptionMax());
                break;
            default:
                newSearchView.getSurface().setPositionMax(updateSwipeViewBus.getMax());
                surfaceMin.setText(newSearchView.getSurface().getDescriptionMin() );
                surfaceMax.setText(newSearchView.getSurface().getDescriptionMax());
                newSearchView.getSurface().setPostitonMin(updateSwipeViewBus.getMin());
                break;
        }

    }
}
