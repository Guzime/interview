package collection;

public class BadHashCode {
    private final int hash;

    @Override
    public String toString() {
        return "{" +
                "hash=" + hash +
                '}';
    }

    public BadHashCode(int hash) {
        this.hash = hash;
    }

    @Override
    public int hashCode() {
        return 2;
    }
}
