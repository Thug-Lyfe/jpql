/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpqldemo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author butwhole
 */
public class ControllerFacade {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpqlDemoPU");
    private EntityManager em = emf.createEntityManager();

    public List<Student> findAll() {
        Query q = em.createNamedQuery("Student.findAll");
        return q.getResultList();
    }

    public List<Student> findStudentFirst(String name){
        Query q = em.createNamedQuery("Student.findByFirstname");
        q.setParameter("firstname", name);
        return q.getResultList();
    }
    
    public List<Student> findStudentLast(String name){
        Query q = em.createNamedQuery("Student.findByLastname");
        q.setParameter("lastname", name);
        return q.getResultList();
    }
    
    public int getScore(int id){
        Query q = em.createNamedQuery("Student.findById");
        q.setParameter("id", id);
        List<Student> list = q.getResultList();
        
        int sum = 0;
        for (Studypoint col : list.get(0).getStudypointCollection()) {
            sum = sum + col.getScore();
        }

        
        return sum;
    }
    
    public int totalScore(){
        List<Student> list = findAll();
        int sum = 0;
        for (Student list1 : list) {
            sum = sum + getScore(list1.getId());}
        return sum;
    }
    
    public List<Student> getHighest(){
        List<Student> list = findAll();
        List<Student> high = new ArrayList();
        for (Student s : list) {
            if(high.isEmpty()){
                high.add(s);
            }
            else if(getScore(s.getId()) > getScore(list.get(0).getId())){
                high.clear();
                high.add(s);
            }
            else if(getScore(s.getId()) == getScore(list.get(0).getId())){
                high.add(s);
            }
            
        }
        return high;
    }
    
    public List<Student> getLowest(){
        List<Student> list = findAll();
        List<Student> high = new ArrayList();
        for (Student s : list) {
            if(high.isEmpty()){
                high.add(s);
            }
            else if(getScore(s.getId()) < getScore(list.get(0).getId())){
                high.clear();
                high.add(s);
            }
            else if(getScore(s.getId()) == getScore(list.get(0).getId())){
                high.add(s);
            }
            
        }
        return high;
    }
    
    public void createStudent(String Firstname, String Lastname){
        Student s = new Student();
        s.setFirstname(Firstname);
        s.setLastname(Lastname);
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
    }
    
    public void addStudypoint(int studentId, int maxV, int score, String disc){
        Query q = em.createNamedQuery("Student.findById");
        q.setParameter("id", studentId);
        List<Student> list = q.getResultList();
        
        em.getTransaction().begin();
        list.get(0).addStudypoint(maxV,score,disc);
        em.getTransaction().commit();
        
    }
    
}
