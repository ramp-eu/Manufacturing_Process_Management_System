package tue.shop4cf.integration.persistence.app.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name="ProcessDef")
public class ProcessDef {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long process_id;

    @Column
    private String process_name;

    @Column
    private String process_descr;

    @Column
    private String process_modeler_id;

    @Column
    private String process_work_params;

    @Column
    private String process_output_params;

    @Column
    private double process_objective_cost;

    @Column
    private double process_objective_time;

    @Column
    private double process_objective_flexibility;

    @Column
    private double process_objective_quality;


}
