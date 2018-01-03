package com.warmcity.citygrants.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Evaluation implements Serializable {

  private static final long serialVersionUID = 8391566704550860062L;
  
  private String juryMemberId;
  private String juryMemberName;
  private int evaluation;

}
