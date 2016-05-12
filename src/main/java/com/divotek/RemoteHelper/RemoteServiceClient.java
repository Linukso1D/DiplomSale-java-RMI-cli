/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divotek.RemoteHelper;



import com.divotek.control.formControlAdmin;
import static com.divotek.control.formControlAdmin.loadJlistUsers;
import com.divotek.helpers.Singleton;
import com.divotek.users.jpa.hibernate.Person;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


/**
 *
 * @author maxxl
 */
public class RemoteServiceClient
{
    
   Registry registry ;
   /**
     * ip ipConn(from singleton)
     * @throws java.rmi.RemoteException
    */
   public RemoteServiceClient() throws RemoteException
   {
       
	   this.registry = LocateRegistry.getRegistry(Singleton.getInstance().getIpConn()
           , 2099);
   }
   
   public void sendString ()
   {
       try{
			RemoteServiceAllPerson service = (RemoteServiceAllPerson) registry.lookup("SendPToServer");
			String s = (String)service.testString("Its test");
                        System.out.println("Ответ от сервера:" + s);
			
		}catch(RemoteException | NotBoundException ex)
		{
		  new Thread(() -> 
                        {
                          JOptionPane.showMessageDialog(null, "Сервер не в сети" );
                      }).start();  
                  
		}
   }
   
   /* Метод отправляет одну персону на удаленный сервер */
   public String sendPersonToRemote(Person person) 
   {
	String answer = "Нет ответа";
	RemoteServiceAllPerson service = null;
		try{
                service = (RemoteServiceAllPerson) registry.lookup("SendPToServer");
		answer =(String) service.sendOnePerson(person);
			
		}catch(RemoteException | NotBoundException ex)
		{
                    System.out.println(answer + " ex= "+ex );
               

                     try {
                        registry.rebind("SendPToServer", (RemoteServiceAllPerson) this);
                        answer =(String) service.sendOnePerson(person);
                     } catch (RemoteException ex1) {
                         new Thread(() -> {
                          JOptionPane.showMessageDialog(null, "Не удалось отправить ученика: "+ex); 
                      }).start();  
                     }
		}
		return answer; 
	
   }
   /* Метод получает весь список персон из удаленного сервера */
   public void getAllFromServer(DefaultListModel listmodel, JComboBox jc)
   {
   /* тут будет использован метод  Map <String,Person> getAllPerson() throws RemoteException;
   после которого сервер отправит весь список персон и они просто заменят текущий список.
       все текущие удалятся , новые заполнятся
   */
       RemoteServiceAllPerson service= null;
                 try{
                     
			 service = (RemoteServiceAllPerson) registry.lookup("SendPToServer");
			Map allPerson= service.getAllPerson(); // get remote allperson
			
                        Singleton.getInstance().getAllPerson().forEach((k,p)->{
                        Singleton.getInstance().getManager().getTransaction().begin();
                        Singleton.getInstance().getManager().remove(Singleton.getInstance().getManager().contains(p) ? p : Singleton.getInstance().getManager().merge(p));
                        Singleton.getInstance().getManager().getTransaction().commit();
                        });
                        /* запись новых */
                        allPerson.forEach((k,v)->
                        {
                        Singleton.getInstance().getManager().getTransaction().begin();
                        Singleton.getInstance().getManager().persist(Singleton.getInstance().getManager().contains(v) ? v : Singleton.getInstance().getManager().merge(v));
                        Singleton.getInstance().getManager().getTransaction().commit();    
                        });
                                          
                        formControlAdmin.fca.getList().setModel(loadJlistUsers(jc));      
                        
		}catch(RemoteException | NotBoundException ex)
		{
                    try {
                        registry.rebind("SendPToServer", (RemoteServiceAllPerson) this);
                        Map allPerson= service.getAllPerson(); // get remote allperson
			
                        Singleton.getInstance().getAllPerson().forEach((k,p)->{
                        Singleton.getInstance().getManager().getTransaction().begin();
                        Singleton.getInstance().getManager().remove(Singleton.getInstance().getManager().contains(p) ? p : Singleton.getInstance().getManager().merge(p));
                        Singleton.getInstance().getManager().getTransaction().commit();
                        });
                        /* запись новых */
                        allPerson.forEach((k,v)->
                        {
                        Singleton.getInstance().getManager().getTransaction().begin();
                        Singleton.getInstance().getManager().persist(Singleton.getInstance().getManager().contains(v) ? v : Singleton.getInstance().getManager().merge(v));
                        Singleton.getInstance().getManager().getTransaction().commit();    
                        });
                                          
                        formControlAdmin.fca.getList().setModel(loadJlistUsers(jc));  
                     } catch (Exception ex1) {
                         new Thread(() -> {
                          JOptionPane.showMessageDialog(null, "Не удалось получить список учеников: "+ex); 
                      }).start();  
                     }
		}
       
       
   
    
   }
}
