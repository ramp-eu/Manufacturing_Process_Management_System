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
@Table(name="Location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String loc_id;

    @Column
    private String loc_name;

    @Column
    private String loc_type;

    @Column
    private String loc_description;

    @Column
    private String loc_included_in;

}
