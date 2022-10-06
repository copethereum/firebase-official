package common.core.functions;

import java.util.ArrayList;

import firebase.core.web.FireBaseWebTestBase;

public class CoreFunctions extends FireBaseWebTestBase {

    public CoreFunctions() {}

    /**
     * Generator for Strings
     * @param lang
     * @param length
     * @return
     */

    public static boolean IsEven(ArrayList<Double> array) {
        int arrayLength = array.size();

        if(arrayLength % 2 == 0) {
            System.out.println("Entered Number is Even");
            return true;
        } else {
            System.out.println("Entered Number is Odd");
            return false;
        }
    }

}
