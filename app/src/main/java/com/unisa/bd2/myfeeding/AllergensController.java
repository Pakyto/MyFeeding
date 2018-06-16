package com.unisa.bd2.myfeeding;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.view.View;

import java.util.HashSet;
import java.util.Set;

public class AllergensController {
    static String[] lattosio = {"milk", "lactose", "butter", "beurre", "lait", "lactose", "Laktose ", "Milch", "Butter", "lactosérum", "Magermilchpulver", "Süßmolkenpulver", "Yaourt", "cream", "latte", "burro"};
    static String[] soia = {"soje", "soia", "soja"};
    static String[] glutine = {"Weizenmalz", "Gerstenmalz", "cebada", "glúten", "trigo", "orge", "frumento", "farro", "grano", "orzo", "avena", "segale"};
    static String[] arachidi = {"Haselnüsse", "nocciole", "nocciola", "arachidi", "noisettes"};
    static private Set<String> allergens;
    static private Set<String> problemFound = new HashSet<>();
    static View view;
    static Context context;

    public static void checkProduct(Context contextPassed, String allergensOfProduct) {
        allergens = AllergensPersistence.loadPreferences();
        context = contextPassed;
        for (String a : allergens) {
            if (a == "Lattosio") {
                for (String item : lattosio) {
                    if (allergensOfProduct.contains(item)) {
                        problemFound.add("Lattosio");
                    }
                }
            } else if (a == "Soia") {
                for (String item : soia) {
                    if (allergensOfProduct.contains(item)) {
                        problemFound.add("Soia");
                    }
                }
            } else if (a == "Glutine") {
                for (String item : glutine) {
                    if (allergensOfProduct.contains(item)) {
                        problemFound.add("Glutine");
                    }
                }
            } else if (a == "Arachidi") {
                for (String item : arachidi) {
                    if (allergensOfProduct.contains(item)) {
                        problemFound.add("Arachidi");
                    }
                }
            } else {
            }
        }
        if (problemFound.isEmpty() == false) {
            launchAlert(problemFound);
        }
    }

    private static void launchAlert(Set<String> allergen) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle("ATTENZIONE")
                .setMessage("CONTIENE DEGLI INGREDIENTI CHE NON PUOI MANGIARE " + allergen.toString()).setNeutralButton("HO CAPITO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
}
