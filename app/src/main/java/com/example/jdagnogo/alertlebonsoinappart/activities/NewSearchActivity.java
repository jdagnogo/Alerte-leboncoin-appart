package com.example.jdagnogo.alertlebonsoinappart.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.models.NewSearchView;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.GlobalBus;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.UpdateSwipeViewBus;
import com.example.jdagnogo.alertlebonsoinappart.utils.MinMaxAlertDialog;
import com.example.jdagnogo.alertlebonsoinappart.utils.swipeItem.NbRoomSwipeItem;
import com.example.jdagnogo.alertlebonsoinappart.utils.swipeItem.RentSwipeItem;
import com.example.jdagnogo.alertlebonsoinappart.utils.swipeItem.SurfaceSwipeItem;

import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_search);
        ButterKnife.bind(this);
        newSearchView = new NewSearchView();
        GlobalBus.getBus().register(this);
        initLayout();

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
                MinMaxAlertDialog minMaxAlertDialog = new MinMaxAlertDialog(RentSwipeItem.createBeans(0), NewSearchActivity.this);
                minMaxAlertDialog.createDialogMinRent();
            }
        });

        LinearLayout surfaceLayout = (LinearLayout) findViewById(R.id.surface);
        surfaceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MinMaxAlertDialog minMaxAlertDialog = new MinMaxAlertDialog(SurfaceSwipeItem.createBeans(0), NewSearchActivity.this);
                minMaxAlertDialog.createDialogMinRent();
            }
        });

        LinearLayout nbRoomLayout = (LinearLayout) findViewById(R.id.nb_room);
        nbRoomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MinMaxAlertDialog minMaxAlertDialog = new MinMaxAlertDialog(NbRoomSwipeItem.createBeans(0), NewSearchActivity.this);
                minMaxAlertDialog.createDialogMinRent();
            }
        });
    }
    private void updateView(){
   
        rentMin.setText(""+newSearchView.getRent().getMin());
        //rentMax.setText(newSearchView.getRent().getMax());
        //surfaceMin.setText(newSearchView.getSurface().getMin());
        //surfaceMax.setText(newSearchView.getSurface().getMax());
        //nbRoomMin.setText(newSearchView.getNbRoom().getMin());
        //nbRoomMax.setText(newSearchView.getNbRoom().getMax());
    }

    @Subscribe
    public void getMessage(UpdateSwipeViewBus updateSwipeViewBus) {
        switch (updateSwipeViewBus.getSwipeItemEnum()){
            case RENT:
                newSearchView.getRent().setMax(updateSwipeViewBus.getMax());
                newSearchView.getRent().setMin(updateSwipeViewBus.getMin());
                updateView();
                break;
            case NB_ROOM:
                newSearchView.getNbRoom().setMax(updateSwipeViewBus.getMax());
                newSearchView.getNbRoom().setMin(updateSwipeViewBus.getMin());
                break;
            default:
                newSearchView.getSurface().setMax(updateSwipeViewBus.getMax());
                newSearchView.getSurface().setMin(updateSwipeViewBus.getMin());
                break;
        }
       updateView();
    }
}
