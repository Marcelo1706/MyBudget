package com.comfycraft.mybudget;

import android.content.Context;
import android.graphics.Typeface;

public class FontManager {

    public static final String ROOT = "fonts/",
            FONTAWESOME_SOLID = ROOT + "font-awesome-solid.ttf",
            FONTAWESOME_BRANDS = ROOT + "fa-brands.ttf",
            FONTAWESOME_REGULAR = ROOT + "fa-regular.ttf";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }

}
