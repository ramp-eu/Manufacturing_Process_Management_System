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
@Table(name="TaskDef")
public class TaskDef {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long task_id;

    @Column
    private String task_modeler_id;

    @Column
    private String task_unique_id;

    @Column
    private String task_name;

    @Column
    private String task_code;

    @Column
    private String task_description;

    @Column
    private String task_type;

    @Column
    private Long task_process_id;

    @Column
    private long task_role_id;

    @Column
    private String task_location;

    @Column
    private long task_duration;

    @Column
    private String task_work_params;

    @Column
    private String task_output_params;

}
