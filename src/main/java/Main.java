import info.Host;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Host host = new Host("../Hanukkah/src/main/resources/qna.json");
            host.startTheShow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
