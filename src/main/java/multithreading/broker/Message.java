package multithreading.broker;

import java.util.Objects;

public final class Message {
    private final String data;

    public Message(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Message message = (Message) object;
        return Objects.equals(data, message.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "Message{" + "data='" + data + '\'' + '}';
    }
}
