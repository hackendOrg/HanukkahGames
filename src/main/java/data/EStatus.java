package data;

public enum EStatus {
    CORRECT(1113),
    WRONG(0);
    private int status;

    EStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
