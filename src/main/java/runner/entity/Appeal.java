package runner.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Appeal")
public class Appeal extends SuperID {

    @Column(name = "Complains")
    private String complains;

    @Column(name = "DateOfTheApplication")
    private String DateOfTheApplication;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Patients")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Patients patients;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "appeal")
    private List<Conclusion> conclusions;

    @Override
    public String toString() {
        return "Appeal{" +
                "complains='" + complains + '\'' +
                ", DateOfTheApplication='" + DateOfTheApplication + '\'' +
                ", conclusions=" + conclusions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Appeal appeal = (Appeal) o;
        return Objects.equals(complains, appeal.complains) && Objects.equals(DateOfTheApplication, appeal.DateOfTheApplication) && Objects.equals(conclusions, appeal.conclusions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), complains, DateOfTheApplication, conclusions);
    }
}
