package mooc.vandy.java4android.birthday.logic;

import java.util.ArrayList;
import java.util.Random;

import mooc.vandy.java4android.birthday.ui.OutputInterface;

/**
 * This is where the logic of this App is centralized for this assignment.
 * <p>
 * The assignments are designed this way to simplify your early Android interactions.
 * Designing the assignments this way allows you to first learn key 'Java' features without
 * having to beforehand learn the complexities of Android.
 */
public class Logic
        implements LogicInterface {
    /**
     * This is a String to be used in Logging (if/when you decide you
     * need it for debugging).
     */
    public static final String TAG = Logic.class.getName();

    /**
     * This is the variable that stores our OutputInterface instance.
     * <p>
     * This is how we will interact with the User Interface
     * [MainActivity.java].
     * <p>
     * It is called 'mOut' because it is where we 'out-put' our
     * results. (It is also the 'in-put' from where we get values
     * from, but it only needs 1 name, and 'mOut' is good enough).
     */
    OutputInterface mOut;

    /**
     * This is the constructor of this class.
     * <p>
     * It assigns the passed in [MainActivity] instance
     * (which implements [OutputInterface]) to 'out'
     */
    public Logic(OutputInterface out) {
        mOut = out;
    }

    /**
     * This is the method that will (eventually) get called when the
     * on-screen button labelled 'Process' is pressed.
     */
    public void process() {
        int groupSize = mOut.getSize();
        int simulationCount = mOut.getCount();

        if (groupSize < 2 || groupSize > 365) {
            mOut.println("Error: Group Size must be in the range 2-365.");
            return;
        }
        if (!(simulationCount >= 1 && simulationCount <= 100000)) {
            mOut.println("Error: Simulation Count must be in the range 1-100000.");
            return;
        }

        double percent = calculate(groupSize, simulationCount);

        // report results
        mOut.println("For a group of " + groupSize + " people, the percentage "
                + " of times that two people share the same birthday is "
                + String.format("%.2f%% of the time.", percent));

    }

    /**
     * This is the method that actually does the calculations.
     * <p>
     * We provide you this method that way we can test it with unit testing.
     */
    public double calculate(int kisiSayisi, int simulasyonSayisi) {
        // TODO -- add your code here
        double tekrarSayisi = 0.0;
        for (int i = 0; i < simulasyonSayisi; i++) {
            int simNo = i;
            if (tekrarVarMi(kisiSayisi, simNo) == true) {
                tekrarSayisi++;
            }
        }
        double oran = (tekrarSayisi * 100.0) / simulasyonSayisi;
        return oran;


    }


    // TODO -- add your helper methods here
    public boolean tekrarVarMi(int kisiSayisi, int simNo) {
        ArrayList<Integer> dogum_gunleri = new ArrayList<Integer>();
        boolean tekrarVarMi = false;
        Random random = new Random();
        random.setSeed(simNo);

        for (int i = 0; i < kisiSayisi; i++) {

            int yeniTarih = random.nextInt(364);//Todo: Please check your boundary
            if (i == 0) {
                dogum_gunleri.add(yeniTarih);
                // System.out.println("ilk tarih eklendi: "+ yeniTarih);

            } else if (dogum_gunleri.contains(yeniTarih)) {
                // System.out.println("Donus sayisi: "+i+" Bu tarih zaten var!: "+yeniTarih);
                tekrarVarMi = true;
                break;

            } else {
                dogum_gunleri.add(yeniTarih);
                // System.out.println("Yeni tarih eklendi! "+ yeniTarih);
            }

        }
        return tekrarVarMi;

    }
}
