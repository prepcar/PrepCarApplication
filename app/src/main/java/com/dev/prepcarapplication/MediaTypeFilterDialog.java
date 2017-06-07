/*
 * Copyright (c) 2017. André Mion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dev.prepcarapplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.SparseBooleanArray;

import com.andremion.louvre.Louvre;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MediaTypeFilterDialog extends DialogFragment implements DialogInterface.OnMultiChoiceClickListener, DialogInterface.OnClickListener {

    private static final String ARG_REQUEST_CODE = "request_code_arg";
    private static final String ARG_MAX_SELECTION = "max_selection_arg";

    public static void show(@NonNull FragmentManager fragmentManager, int requestCode, int maxSelection) {
        MediaTypeFilterDialog dialog = new MediaTypeFilterDialog();
        Bundle args = new Bundle();
        args.putInt(ARG_REQUEST_CODE, requestCode);
        args.putInt(ARG_MAX_SELECTION, maxSelection);
        dialog.setArguments(args);
        dialog.show(fragmentManager, TAG);
    }

    private final SparseBooleanArray mSelectedTypes;

    public MediaTypeFilterDialog() {
        mSelectedTypes = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.media_type_prompt)
                .setMultiChoiceItems(Louvre.IMAGE_TYPES, null, this)
                .setPositiveButton(android.R.string.ok, this)
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        if (isChecked) {
            mSelectedTypes.put(which, true);
        } else {
            mSelectedTypes.put(which, false);
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        //noinspection WrongConstant
        Louvre.init(getActivity())
                .setRequestCode(getArguments().getInt(ARG_REQUEST_CODE))
                .setMaxSelection(getArguments().getInt(ARG_MAX_SELECTION))
                .setMediaTypeFilter(parseToArray(mSelectedTypes))
                .open();
    }

    @NonNull
    private String[] parseToArray(@NonNull SparseBooleanArray selectedTypes) {
        List<String> selectedTypeList = new ArrayList<>();
        for (int i = 0; i < selectedTypes.size(); i++) {
            int key = selectedTypes.keyAt(i);
            if (selectedTypes.get(key, false)) {
                selectedTypeList.add(Louvre.IMAGE_TYPES[key]);
            }
        }
        String[] array = new String[selectedTypeList.size()];
        selectedTypeList.toArray(array);
        return array;
    }
}
