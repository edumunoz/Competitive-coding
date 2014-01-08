import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by edu on 7/1/14.
 */
public class Appointment implements Comparable<Appointment>{
    long startTime, endTime;
    boolean hasConflict;


    public static ArrayList<Appointment> markConflicts(ArrayList<Appointment> apntmnts) {

        Collections.sort(apntmnts);

        for (int i = 0; i < apntmnts.size()-1; i++) {
            if (apntmnts.get(i).endTime > apntmnts.get(i+1).startTime) {
                apntmnts.get(i).hasConflict = true;
                apntmnts.get(i+1).hasConflict = true;
            }
        }


        return apntmnts;
    }

    public int compareTo(Appointment app2) {
        if (this.startTime != app2.startTime)
            return (int) (this.startTime - app2.startTime);
        else
            return (int) (this.endTime - app2.endTime);
    }

}
