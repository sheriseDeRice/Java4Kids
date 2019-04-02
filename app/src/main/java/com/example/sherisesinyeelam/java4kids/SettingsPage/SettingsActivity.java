package com.example.sherisesinyeelam.java4kids.SettingsPage;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sherisesinyeelam.java4kids.LocalSharedPrefManager;
import com.example.sherisesinyeelam.java4kids.LoginAndRegister.RequestHandler;
import com.example.sherisesinyeelam.java4kids.R;
import com.example.sherisesinyeelam.java4kids.StartAppActivity;
import com.example.sherisesinyeelam.java4kids.LoginAndRegister.LoginActivity;
import com.example.sherisesinyeelam.java4kids.SharedPrefManager;
import com.example.sherisesinyeelam.java4kids.UserProfilePage.UserInfoUpdate;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class SettingsActivity extends Fragment {

    Dialog dialog;
    ProgressDialog progressDialog;
    TextView edit_icon, edit_background, qAndA, go_login, go_logout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_settings, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Settings");

        dialog = new Dialog(getActivity());
        progressDialog = new ProgressDialog(getActivity());

        edit_icon = (TextView) getView().findViewById(R.id.edit_icon);
        edit_background = (TextView) getView().findViewById(R.id.edit_background);
        qAndA = (TextView) getView().findViewById(R.id.q_and_a);
        go_login = (TextView) getView().findViewById(R.id.go_login);
        go_logout = (TextView) getView().findViewById(R.id.go_logout);

        if(SharedPrefManager.getInstance(getActivity()).checkLoginOrNot()){
            //if user already logged in.
            go_login.setVisibility(View.GONE);
            go_logout.setVisibility(View.VISIBLE);
        }

        edit_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopEditIcon();
            }
        });

        edit_background.setVisibility(View.GONE);
        edit_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: future expectation
                Toast.makeText(getActivity(), "you selected edit_background", Toast.LENGTH_SHORT).show();
            }
        });

        qAndA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new fragment and transaction
                showPopQandA();
            }
        });

        go_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        go_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Logout successful :)", Toast.LENGTH_SHORT).show();
                SharedPrefManager.getInstance(getActivity()).userLogout();
                go_login.setVisibility(View.VISIBLE);
                go_logout.setVisibility(View.INVISIBLE);
                getActivity().finish();
                Intent intent = new Intent(getActivity(), StartAppActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showPopQandA() {
        dialog.setContentView(R.layout.activity_qanda);

        TextView closePopUp = (TextView) dialog.findViewById(R.id.close_popup);

        closePopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        QandA qandA = new QandA(dialog);
        qandA.getQandA();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void showPopEditIcon() {
        dialog.setContentView(R.layout.activity_editicon);

        TextView closePopUp = (TextView) dialog.findViewById(R.id.close_popup);
        Button save = (Button) dialog.findViewById(R.id.save_button);

        closePopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ImageView current_icon = dialog.findViewById(R.id.current_icon);

        if (SharedPrefManager.getInstance(getActivity()).checkLoginOrNot()){
            current_icon.setImageResource(SharedPrefManager.getInstance(getActivity()).getUserIcon());
        } else {
            if (LocalSharedPrefManager.getInstance(getActivity()).checkLoginOrNot()) {
                current_icon.setImageResource(LocalSharedPrefManager.getInstance(getActivity()).getUserIcon());
            }
        }

        final EditIcon editIcon = new EditIcon(dialog, current_icon);
        editIcon.editIcon_content();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SharedPrefManager.getInstance(getActivity()).checkLoginOrNot()){
                    //if user already logged in.
                    int userIcon = 0;
                    if(editIcon.getIcon_to_save() == R.drawable.default_icon_foreground){
                        userIcon = 0;
                    } else if (editIcon.getIcon_to_save() == R.drawable.dollify2_sherise) {
                        userIcon = 1;
                    } else if(editIcon.getIcon_to_save() == R.drawable.dollify1){
                        userIcon = 2;
                    }
                    iconUpdate(v, userIcon);
                } else {
                    int userIcon = editIcon.getIcon_to_save();
                    LocalSharedPrefManager.getInstance(getActivity())
                            .userIconUpdate(userIcon);
                    Toast.makeText(getActivity(), "Icon updated", Toast.LENGTH_SHORT).show();

                    NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
                    // Change the header
                    View hView =  navigationView.getHeaderView(0);
                    ImageView icon = (ImageView) hView.findViewById(R.id.imageView);
                    icon.setImageResource(LocalSharedPrefManager.getInstance(getActivity()).getUserIcon());
                }
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void iconUpdate(View v, int icon_to_save){
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    if(!jsonResponse.getBoolean("error")){
                        // if error is false mean user successfully update score
                        if(SharedPrefManager.getInstance(getActivity()).checkLoginOrNot()){
                            //if user already logged in.
                            SharedPrefManager.getInstance(getActivity())
                                    .userIconUpdate(jsonResponse.getInt("userIcon"));
                            NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
                            // Change the header
                            View hView =  navigationView.getHeaderView(0);
                            ImageView icon = (ImageView) hView.findViewById(R.id.imageView);
                            icon.setImageResource(SharedPrefManager.getInstance(getActivity()).getUserIcon());
                            Toast.makeText(getActivity(), "Icon updated", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), jsonResponse.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) { e.printStackTrace(); }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();;
            }
        };
        // cancel the progress dialog if it runs overtime.
        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                progressDialog.cancel();
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 5000);

        int useriD = SharedPrefManager.getInstance(getActivity()).getUserID();
        int current_level = SharedPrefManager.getInstance(getActivity()).getUserLevel();
        int currentScore = SharedPrefManager.getInstance(getActivity()).getUserTotalScore();
        int userIcon = icon_to_save;

        UserInfoUpdate userInfoUpdate = new UserInfoUpdate(useriD, current_level,currentScore, userIcon, responseListener, errorListener);
        RequestHandler.getInstance(getActivity()).addToRequestQueue(userInfoUpdate);
    }



}
