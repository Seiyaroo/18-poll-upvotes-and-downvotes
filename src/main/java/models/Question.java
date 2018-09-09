package models;

import javax.persistence.*;

@Entity
@Table(name="questionpoll")
public class Question implements Comparable<Question>{
    @Id
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(
            name = "question_generator",
            sequenceName = "question_sequence",
            initialValue = 1001
    )
    public long id;
    public String question;
    public int votes;

    // Requires default constructor
    public Question(){}

    public Question(String question){
        this.question = question;
    }

    @Override
    public int compareTo(Question o) {
        return o.votes - this.votes;
    }
}