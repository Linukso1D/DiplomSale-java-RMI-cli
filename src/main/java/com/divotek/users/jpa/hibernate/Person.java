/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divotek.users.jpa.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

/**
 *
 * @author maxxl
 * @version 1.0
 * Описывает учеников и методы сохранения получения с бд
 */
@SuppressWarnings("PMD")
@Entity
public class Person implements Serializable
{
   /**
    * Ид
    */
   @Id
   @GeneratedValue
   @Getter
   @Setter
   private Integer id;
  
   @Getter
   @Setter
   private Boolean sex;
   
   public Person() {}
   public Person(Person another) {
    this.achievements = another.achievements; 
    this.achievementsStudy = another.achievementsStudy;
    this.additionalSubject = another.additionalSubject;
    this.className = another.className;
    this.director = another.director;
    this.examSubject= another.examSubject;
    this.id=another.id;
    this.lastname=another.lastname;
    this.name=another.name;
    this.medal= another.medal;
    this.number=another.number;
    this.regCode=another.regCode;
    this.seria=another.seria;
    this.sex= another.sex;
    this.subjects=another.subjects;
    this.surname = another.surname;
  }
   
   
   /**
    * Серия атестат
    */
   @Getter
   @Setter
   private String seria;
    /**
    * Номер атестат
    */
   @Getter
   @Setter
   private String number;
    /**
    * Фамилия ученика
    */
   @Getter
   @Setter
   private String surname;
   /**
    * Имя ученика
    */
   @Getter
   @Setter
   private String name;
   /**
    * Отчество ученика
    */
   @Getter
   @Setter
   private String lastname;
   
   @Getter
   @Setter
   private String className;
   
    /**
    * Перечень предметов и оценок
    */
   @Type(type = "serializable")
   @Column( length = 100000 )
   @Getter
   @Setter
   public Map subjects = new LinkedHashMap<String, String>();
   /**
    * Сохранение оценок по екзаменам 
    */
   @Type(type = "serializable")
   @Column( length = 10000 )
   @Getter
   @Setter
   private Map examSubject = new LinkedHashMap<>();
   /**
    * Факультативы
    */
   @Type(type = "serializable")
   @Column( length = 10000 )
   @Getter
   @Setter
   private ArrayList additionalSubject = new ArrayList();
   @Getter
   @Setter
   /**
    * Достижения
    */
   private String achievements;
   
    @Getter
   @Setter
   private String medal;
   
   @Getter
   @Setter
   /**
    *  Достижения по учебе
    */
  
   private String achievementsStudy;
   /**
    * Фио директора
    */
   @Getter
   @Setter
   private String director;    
   /**
    * Регистрационный номер приложения
    */
   @Getter
   @Setter
   private Integer regCode;

   
   @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() == this.getClass()) {
           Person p = (Person) obj;
           if( this.name.equals(p.getName()) && this.surname.equals(p.getSurname())&&
               this.lastname.equals(p.getLastname())&& this.regCode.equals(p.getRegCode()) )
           {
           return true;
           }
        }
        return false;
    }

   
   
}