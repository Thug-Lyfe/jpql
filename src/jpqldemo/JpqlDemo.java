/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpqldemo;

/**
 *
 * @author butwhole
 */
public class JpqlDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ControllerFacade con = new ControllerFacade();
        con.createStudent("david", "dicker dick");
        System.out.println(con.findStudentFirst("alex"));
        Studypoint sp = new Studypoint();
        sp.setMaxval(10);
        sp.setScore(9);
        
        con.addStudypoint(3,5,5,"yolo");
        System.out.println(con.findAll().toString());
        System.out.println(con.findStudentFirst("jan"));
        System.out.println(con.findStudentLast("olsen"));
        System.out.println(con.getScore(1));
        System.out.println(con.totalScore());
        System.out.println(con.getHighest());
        System.out.println(con.getLowest());
        
        
        
        
    
    }
    }
    

