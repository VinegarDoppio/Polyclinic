package runner.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Patients")
public class Patients extends SuperID {

    @Column(name = "Name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patients")
    private Set<Appeal> appeals;

    @Override
    public String toString() {
        return "Patients{" +
                "name='" + name + '\'' +
                ", appeals=" + appeals +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Patients patients = (Patients) o;
        return Objects.equals(name, patients.name) && Objects.equals(appeals, patients.appeals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, appeals);
    }
}
