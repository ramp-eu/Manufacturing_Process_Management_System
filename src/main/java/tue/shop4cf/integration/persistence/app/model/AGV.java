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
@Table(name="agv")
public class AGV {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long agv_id;

    @Column(nullable = false)
    private String agv_tag;

    @Column
    private String agv_name;

    @Column
    private String agv_loc_coord;

    @Column
    private Boolean agv_online;

    @Column
    private Integer agv_capacity;

    @Column
    private Integer agv_capacity_in_use;

    @Column
    private String agv_state;

    @Column
    private Long agv_battery_level;

    @Column
    private Long agv_task_id;

    @Column
    private Long agv_start_loc_id;

    @Column
    private Long agv_end_loc_id;

    @Column
    private Long agv_agent_id;

}
