package models;

import java.util.Map;

/**
 * Created by daniel.rothig on 21/10/2016.
 *
 * A single survey question
 */
public class Question {
    public final int id;
    public final String question;
    public final Map<DigitalRole, Double> roleMap;


    public Question(int id, String question, Map<DigitalRole, Double> roleMap) {
        this.id = id;
        this.question = question;
        this.roleMap = roleMap;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
