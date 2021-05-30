package runner.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Conclusion")
public class Conclusion extends SuperID {

    @Column(name = "Diagnosis")
    private String diagnosis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Doctor")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Doctors doctors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Appeal")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Appeal appeal;

    @Column(name = "Cost")
    private float cost;

    @Override
    public String toString() {
        return "Conclusion{" +
                "diagnosis='" + diagnosis + '\'' +
                ", cost=" + cost +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Conclusion that = (Conclusion) o;
        return Float.compare(that.cost, cost) == 0 && Objects.equals(diagnosis, that.diagnosis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), diagnosis, cost);
    }
}
