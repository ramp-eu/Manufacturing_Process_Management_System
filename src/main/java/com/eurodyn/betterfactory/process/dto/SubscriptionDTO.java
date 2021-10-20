package com.eurodyn.betterfactory.process.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String subscriptionId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SubjectDTO subject;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private NotificationDTO notification;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String expires;

}
