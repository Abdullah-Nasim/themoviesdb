package myown.themoviesdb.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

import myown.themoviesdb.R;
import myown.themoviesdb.models.MovieDetailsResponse;

/**
 * Created by Abdullah on 10/5/2017.
 *
 * This class contains the method which are widely used through out the application.
 * All of the methods in this class are static so that they can be easily accessed throughout the application where ever they are needed.
 *
 */
public class Utils {

    /**
     * this method displays the progress layout.
     * @param progressLayout is the layout referring to the progress layout of the specific activity.
     */
    public static void showFullScreenProgress(FrameLayout progressLayout){
        progressLayout.setVisibility(View.VISIBLE);
    }

    /**
     * This method hides the progress layout.
     * @param progressLayout is the layout referring to the progress layout of the specific activity.
     */
    public static void hideFullScreenProgress(FrameLayout progressLayout){
        progressLayout.setVisibility(View.GONE);
    }

    /**
     * This method handles no internet exception.
     * @param context is the context of the respective activity which is calling this method.
     */
    public static void noInternetException(Context context){
        showAlertDialog(context.getString(R.string.no_internet_messege), context);
    }

    /**
     * This method handles the data fetch failure exception.
     * @param context is the context of the respective activity which is calling this method.
     */
    public static void unableToFetchDataException(Context context){
        showAlertDialog(context.getString(R.string.server_error_messege), context);
    }

    /**
     * This method processes the list of genres and converts them into the string form and returns back.
     * @param genres are the genres of the specific movie in list form.
     * @return is the string form of all genres.
     */
    public static String processGenres (List<MovieDetailsResponse.Genre> genres){

        String genresString = "";

        for(int i=0; i < genres.size(); i++){
            genresString = genresString + genres.get(i).getName()+ " ";
        }

        return genresString;
    }

    /**
     * This method is responsible for making a dialog box with provided message and OK button.
     * @param msg is the message which we wants this method to display in dialog box.
     * @param context is the context of the respective activity which is calling this method.
     */
    public static void showAlertDialog(String msg, Context context){

        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(R.string.error)
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

}
