package uottawa.ca.cookhelper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;


public class CustomEditText extends EditText {
     public CustomEditText(Context context) {
         super(context);
     }

    public CustomEditText(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

    public CustomEditText(Context context, AttributeSet attributes, int defStyle) {
        super(context, attributes, defStyle);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        System.out.println(getText().toString());
        if (getText().toString().equals("")) {
            setSelection(0);
        }
    }
}
