package ru.adelbergis.secondapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.HashMap;
import java.util.UUID;

public class MyDialogFragment extends DialogFragment {

    private Context context;

    public MyDialogFragment(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Выберите действие")
                    .setCancelable(true)
                    .setPositiveButton("Изменить",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    //Toast.makeText(context,"cliked" + savedInstanceState.getString("userName"),Toast.LENGTH_SHORT).show();
                                    dialog.cancel();

                                }
                            })
                    .setNeutralButton("Удалить",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    Users users = Users.get(context);
                                    HashMap<UUID,String> userList = users.getUserList();
                                    String searchUser = savedInstanceState.getString("userName");
                                    for (UUID key : userList.keySet()) {
                                        if (userList.get(key).equals(searchUser)){
                                            users.Delete(key.toString());
                                            break;
                                        }
                                    }

                                    dialog.cancel();
                                }
                            })
                    .setNegativeButton("Отмена",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            });

            return builder.create();

    }
}
