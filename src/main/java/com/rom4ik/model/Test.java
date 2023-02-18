package com.rom4ik.model;


import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author rom4ik
 */

@Data
@NoArgsConstructor
public class Test {
    private int id;

    @NotEmpty(message = "Name field should not be empty")
    @Size(min = 2, max = 20, message = "Size must be between 2 and 20 characters")
    private String name;

    @NotEmpty(message = "Subject field should not be empty")
    @Size(min = 2, max = 20, message = "Size must be between 2 and 20 characters")
    private String subject;

    @Min(value = 1, message = "Minimum value should be 1")
    private int duration;

    @Min(value = 1, message = "Minimum value should be 1")
    private int numberOfQuestions;

    @Builder
    public Test(int id, String name, String subject, int duration, int numberOfQuestions) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.duration = duration;
        this.numberOfQuestions = numberOfQuestions;
    }
}
