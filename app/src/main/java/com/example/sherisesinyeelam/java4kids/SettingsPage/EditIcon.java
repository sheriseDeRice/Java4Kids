package com.example.sherisesinyeelam.java4kids.SettingsPage;

import android.app.Dialog;
import android.view.View;

import android.widget.ImageView;

import com.example.sherisesinyeelam.java4kids.R;

public class EditIcon implements View.OnClickListener{

    Dialog dialog;

    ImageView current_icon, default_icon, icon1, icon2, icon3, icon4, icon5;

    int icon_to_save;

    public EditIcon(Dialog dialog, ImageView current_icon){
        this.dialog = dialog;
        this.current_icon = current_icon;
        icon_to_save = R.drawable.default_icon_foreground;
    }

    public void editIcon_content(){

        default_icon = dialog.findViewById(R.id.icon_default);
        icon1 = dialog.findViewById(R.id.icon1);
        icon2 = dialog.findViewById(R.id.icon2);
        icon3 = dialog.findViewById(R.id.icon3);
        icon4 = dialog.findViewById(R.id.icon4);
        icon5 = dialog.findViewById(R.id.icon5);

        default_icon.setOnClickListener(this);
        icon1.setOnClickListener(this);
        icon2.setOnClickListener(this);
        icon3.setOnClickListener(this);
        icon4.setOnClickListener(this);
        icon5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int image_id;
        switch (v.getId()) {
            case R.id.icon_default :
                image_id = R.drawable.default_icon_foreground;
                current_icon.setImageResource(image_id);
                icon_to_save = image_id;
                break;
            case R.id.icon1 :
                image_id = R.drawable.dollify2_sherise;
                current_icon.setImageResource(image_id);
                icon_to_save = image_id;
                break;
            case R.id.icon2 :
                image_id = R.drawable.dollify1;
                current_icon.setImageResource(image_id);
                icon_to_save = image_id;
                break;
            case R.id.icon3 :
                image_id = R.drawable.faceq1;
                current_icon.setImageResource(image_id);
                icon_to_save = image_id;
                break;
            case R.id.icon4 :
                image_id = R.drawable.faceq2;
                current_icon.setImageResource(image_id);
                icon_to_save = image_id;
                break;
            case R.id.icon5 :
                image_id = R.drawable.faceq3;
                current_icon.setImageResource(image_id);
                icon_to_save = image_id;
                break;
        }
    }

    public int getIcon_to_save(){
        return icon_to_save;
    }
}
