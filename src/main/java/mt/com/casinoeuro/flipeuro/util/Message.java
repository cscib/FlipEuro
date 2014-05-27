package mt.com.casinoeuro.flipeuro.util;

import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * A wrapper class for a message.
 *
 * @author cscib
 * @version 1.0.0
 * @since 26/05/2014 20:58
 */
public class Message {
    private static final Logger log = Logger.getLogger(Message.class);

    /**
     * The name of the resource bundle containing all the message keys.
     */
    public static final String RESOURCE_ID = "content.Language";

    /**
     * The type of message. This message should be correctly interpreted by the UI in the view.
     */
    public static enum Type {
        /**
         * Denotes a success message.
         */
        SUCCESS,

        /**
         * Denotes an information message.
         */
        INFO,

        /**
         * Denotes an error message.
         */
        ERROR;
    }

    /**
     * The message text.
     */
    private String text;

    /**
     * The message type.
     */
    private Type type;

    /**
     * Creates and initializes a new instance of the {@link Message} class.
     *
     * @param text The message text.
     * @param type The message type as specified by the {@link Type} enumeration.
     */
    public Message(String text, Type type) {
        this.text = text;
        this.type = type;
    }

    /**
     * Returns the message text.
     *
     * @return The message text.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the new message text.
     *
     * @param text The new message text.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Returns the message type.
     *
     * @return The message type.
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the new message type.
     *
     * @param type The new message type.
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Returns the message string from the specified resource bundle {@code key}.
     *
     * @param key The resource bundle key.
     * @return The resolved message string.
     */
    private static String messageFromKey(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_ID);
        return bundle.getString(key);
    }

    /**
     * Creates a new {@link Message} instance from the specified resource bundle {@code key},
     * using the message {@link Type}.
     *
     * @param key  The resource bundle key.
     * @param type The assigned message {@link Type}.
     * @return A new {@link Message} instance containing the text resolved from the resource
     * bundle, coupled with the error {@link Type}.
     */
    public static Message messageFromKey(String key, Message.Type type) {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_ID);
        return new Message(bundle.getString(key), type);
    }

    /**
     * Creates a new {@link Message} instance from the specified resource bundle {@code key},
     * using the message {@link Type}.
     *
     * @param key    The resource bundle key.
     * @param type   The assigned message {@link Type}.
     * @param params The parameters to be replaced in the message {@link Object}.
     * @return A new {@link Message} instance containing the text resolved from the resource
     * bundle, formatted with the optional parameters, and coupled with the error {@link Type}.
     */
    public static Message messageFromKey(String key, Message.Type type, Object... params) {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_ID);
        return new Message(MessageFormat.format(bundle.getString(key), params), type);
    }


    /**
     * Returns a {@link String} representation of this instance.
     *
     * @return This instance represented as {@link String}.
     */
    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", type=" + type +
                '}';
    }
}
