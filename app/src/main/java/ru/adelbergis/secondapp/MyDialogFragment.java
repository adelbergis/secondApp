package ru.adelbergis.secondapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

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
                                    Toast.makeText(context,"cliked" + savedInstanceState.getString("position"),Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }
                            })
                    .setNeutralButton("Удалить",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
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
