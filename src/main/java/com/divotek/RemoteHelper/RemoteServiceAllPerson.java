/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divotek.RemoteHelper;

import com.divotek.users.jpa.hibernate.Person;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**
 *
 * @author maxxl
 */
public interface RemoteServiceAllPerson extends Remote
{
   Object sendOnePerson(Person person) throws RemoteException;
   Map <String,Person> getAllPerson() throws RemoteException;
      Object testString (String a) throws RemoteException;
}
