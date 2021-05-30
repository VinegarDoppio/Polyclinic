package runner.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Doctors")
public class Doctors extends SuperID {

    @Column(name = "Name")
    private String name;

    @Column(name = "Specialty")
    private String specialty;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctors")
    private List<Conclusion> conclusions;

    @Override
    public String toString() {
        return "Doctors{" +
                "name='" + name + '\'' +
                ", specialty='" + specialty + '\'' +
                ", conclusions=" + conclusions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Doctors doctors = (Doctors) o;
        return Objects.equals(name, doctors.name) && Objects.equals(specialty, doctors.specialty) && Objects.equals(conclusions, doctors.conclusions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, specialty, conclusions);
    }
}
