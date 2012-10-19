package eu.gruchala.tweete.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Tweet implements Serializable {

    private static final long serialVersionUID = -172412219240754562L;
    @Size(min = 1, max = 160)
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Tweet");
        sb.append("{value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
