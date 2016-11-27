package uottawa.ca.cookhelper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * The class CustomEditText overrides the onSelectionChanged method
 * of EditText so that the cursor position of a TextField begins at the start of
 * the field when a user is adding a new recipe.
 * <p>
 * It extends EditText.
 */
public class CustomEditText extends EditText {
    /**
     * 1-arg constructor.
     *
     * @param context the context
     */
    public CustomEditText(Context context) {
        super(context);
    }

    /**
     * 2-arg constructor.
     *
     * @param context    the context
     * @param attributes the attributes
     */
    public CustomEditText(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

    /**
     * 3-arg constructor
     *
     * @param context    the context
     * @param attributes the attributes
     * @param defStyle   the default style to apply to this view
     */
    public CustomEditText(Context context, AttributeSet attributes, int defStyle) {
        super(context, attributes, defStyle);
    }

    /**
     * onSelectionChanged method.
     *
     * @param selStart start selection position
     * @param selEnd   end selection position
     */
    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        System.out.println(getText().toString());
        if (getText().toString().equals("")) {
            setSelection(0);
        }
    }
}
