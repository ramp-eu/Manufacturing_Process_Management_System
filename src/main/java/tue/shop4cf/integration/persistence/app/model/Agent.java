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
@Table(name="Agent")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long agent_id;

    @Column
    private String agent_name;

    @Column
    private String agent_type;

    @Column
    private String agent_fullname;

    @Column
    private Long agent_role_id;

    @Column
    private boolean agent_online;

    @Column
    private double agent_performance_time;

    @Column
    private double agent_performance_quality;

    @Column
    private double agent_travel_speed;

    @Column
    private int agent_queue;

    @Column
    private Timestamp agent_busy_time;

    @Column
    private double agent_cost;

}
