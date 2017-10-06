package myown.themoviesdb.interfaces;

/**
 * Created by Netaq on 10/5/2017.
 *
 * This interface is also extended by other interface.
 * It's respective abstract method is overridden by the BAL listeners in order to handle no internet exception.
 */

public interface GeneralNetworkListener {

    void onNetworkFailure();

}
