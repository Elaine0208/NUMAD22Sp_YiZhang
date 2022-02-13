package edu.neu.madcourse.numad22sp_yizhang;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class EnterUrlPopup extends AppCompatDialogFragment {
    private EditText editName;
    private EditText editUrl;
    private EnterUrlListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.enter_url, null);

        builder.setView(view)
                .setTitle("Enter Name and Url")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = editName.getText().toString();
                        String url = editUrl.getText().toString();
                        listener.applyTexts(name, url);
                    }
                });

        editName = view.findViewById(R.id.edit_name);
        editUrl = view.findViewById(R.id.edit_url);
        return builder.create();
    }

    public interface EnterUrlListener {
        void applyTexts(String name, String url);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (EnterUrlListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement EnterUrlListner");
        }
    }
}
