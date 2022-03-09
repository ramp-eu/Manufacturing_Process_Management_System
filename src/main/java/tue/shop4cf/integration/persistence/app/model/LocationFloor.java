package tue.shop4cf.integration.persistence.app.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name="LocationFloor")
public class LocationFloor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long locf_id;

    @Column
    private String locf_code;

    @Column
    private String locf_descr;

    @Column
    private String locf_coord;

    @Column
    private String locf_type;

}
