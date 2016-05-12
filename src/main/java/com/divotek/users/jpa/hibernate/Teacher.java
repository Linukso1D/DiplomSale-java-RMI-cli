/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divotek.users.jpa.hibernate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

/**
/**
 *
 * @author maxxl
 
 */
@Entity
public class Teacher implements Serializable
{
   @Id   @GeneratedValue   @Getter   @Setter   private Integer id;
   @Getter    @Setter    private String login;
   @Getter    @Setter    private String pass;
   @Getter    @Setter    private String FIO; 
   /* Специализация препадов */
   @Type(type = "serializable")
   @Column( length = 400 )    @Getter    @Setter
   private ArrayList controlSubject = new ArrayList();
   @Getter    @Setter    private Integer level;
}
