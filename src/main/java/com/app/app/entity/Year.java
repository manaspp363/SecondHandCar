
package com.app.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "year")
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "year", nullable = false, unique = true, length = 5)
    private String year;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    @Override
    public String toString() {
        return String.format(
                "Year \n{\n" +
                        "    id: %d,\n" +
                        "    year: '%s'\n" +
                        "}", id, year
        );
    }

}