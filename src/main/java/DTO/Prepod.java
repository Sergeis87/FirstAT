package DTO;

import lombok.Data;

@Data
public class Prepod {


    public Prepod(String surname, String name, String patronimyc) {
        this.name = name;
        this.surname = surname;
        this.patronimyc = patronimyc;
    }

    private String surname;
    private String name;
    private String patronimyc;

}
