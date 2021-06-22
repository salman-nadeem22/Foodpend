package com.example.foodwastagemanagmentsystem;

import android.text.TextUtils;
import android.widget.EditText;

public class Utils {
    private void isEditTextEmpty(String text, EditText editText) {
        if(TextUtils.isEmpty(text)) {
            editText.setError("This field cannot be empty.");
            return;
        }
    }

    public void validateAllEditText(EditText[] fields, String[] text){
        for(int i = 0; i < fields.length; i++){
            EditText currentField = fields[i];
            String currentText = text[i];
            if(currentField.getText().toString().length() <= 0){
                isEditTextEmpty(currentText, currentField);
            }
        }
    }
}
