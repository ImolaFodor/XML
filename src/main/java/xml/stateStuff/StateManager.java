package xml.stateStuff;

import java.io.*;

public class StateManager {

    private static final String FILE_PATH = "./src/main/resources/state.bin";

    private static final String PREDLAGANJE_AKATA = "Predlaganje akata";
    private static final String PREDLAGANJE_AMANDMANA = "Predlaganje amandmana";
    private static final String GLASANJE = "Glasanje";


    public static State getState(){
        DataInputStream dis = null;
        byte[] bytes = new byte[PREDLAGANJE_AMANDMANA.toString().getBytes().length];

        try {
            dis = new DataInputStream(new BufferedInputStream(new FileInputStream(FILE_PATH)));
            dis.read(bytes);
            return new State(new String(bytes));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean setState(String state){

        switch (state){
            case PREDLAGANJE_AKATA:
            case PREDLAGANJE_AMANDMANA:
            case GLASANJE:
                if(writeToFile(state))
                    return true;

            default:
                return false;
        }

    }

    private static boolean writeToFile(String state) {
        DataOutputStream dos = null;

        try {
            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(FILE_PATH)));
            dos.writeBytes(state);
            dos.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
