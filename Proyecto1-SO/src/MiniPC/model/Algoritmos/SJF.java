/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MiniPC.model.Algoritmos;

import MiniPC.model.Algoritmos.Algoritmos;
import MiniPC.controller.PCController;
import MiniPC.model.PCB;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author ricardosoto
 */
public class SJF implements Algoritmos{
    private Queue<PCB> procesosEsperando;
    private PCB currentPCB;
    private int currentTime;
    private int currentTimeAux;
    private boolean programFinished;
    private ArrayList<String> status;
    
    public SJF(){
        
        this.currentTime = 1;
        this.procesosEsperando = new LinkedList<PCB>();
        this.programFinished = false;
        this.currentTimeAux= 1;
        
        
    }
    @Override
    //Lo que procura este método es que retornen el PCB(proceso) que se va a ejecutar, aunque deben ejecutarlo antes para
    // tener información contable del mismo
    public PCB executeInstruction(Queue<PCB> colaProcesosCPU,PCController cont){
        //ESTO ES PARTE DEL MÉTODO A IMPLEMENTAR
//---------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------------
        
        Queue<PCB> col = colaProcesosCPU;
        this.programFinished = false;
        
        ArrayList<PCB> procesos = new ArrayList<>();
        
        for(PCB proceso: col){
           //los procesos llegan en el instante actual
           
           //System.out.println("PCB->"+proceso.getFileName()+"ARRIVAL->"+proceso.getArrivalTime()+"Tiempo actual"+this.currentTime);
           
           if(proceso.getArrivalTime()<=this.currentTimeAux && !this.procesosEsperando.contains(proceso)){   
              //System.out.println("PCB->"+proceso.getFileName()+" entra a espera");
              if(this.procesosEsperando.isEmpty()){
                   this.currentPCB = proceso;
                   this.currentPCB.setStatus("Exec");
                   
               }               
               procesos.add(proceso);
           }
        } 
        
        //System.out.println("--Proccesos--");
        //System.out.println(procesos);
        Collections.sort(procesos, new Comparator<PCB>() {
            @Override
            public int compare(PCB pcb1, PCB pcb2) {
                if (pcb1.getPCBinstrucctionSize() > pcb2.getPCBinstrucctionSize()) {
                    return 1;
                }
                if (pcb1.getPCBinstrucctionSize() < pcb2.getPCBinstrucctionSize()) {
                    return -1;
                }
                return 0;
            }
        });
        
        for(PCB proceso: procesos) {
            this.procesosEsperando.add(proceso);
            System.out.println("PBC: "+proceso.getFileName() +"RAFAGA:"+(proceso.getRafaga()));
        }
        
        
       this.currentPCB = this.procesosEsperando.peek();
        
        
        //ESTO ES PARTE DEL MÉTODO A IMPLEMENTAR
//---------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------------
        //ESTO ES PARTE DEL METODO POR DEFECTO, PROCUREN RETORNAR EL PCB que según el algoritmo se debe ejecutar
       PCB result =  this.currentPCB;     
       
       this.currentTimeAux += this.currentPCB.getPCBinstrucctionSize();
       
       if(result!=null){
           this.status = result.executeInstruction(cont);
            if(this.currentPCB.programFinished()){   
           this.programFinished = true;
           this.procesosEsperando.remove(this.currentPCB);     
           this.currentPCB = this.procesosEsperando.peek();
           
       }
       
           
       }
       
       this.currentTime++;
              
       return result;
       
        
    }
    
    
    @Override
     public boolean programIsFinished(){                
        return this.programFinished;
     }
    @Override 
    public ArrayList<String> getStatus(){
        return this.status;
    }        

    @Override
    public int getTime() {
        return this.currentTime;
    }
    
    
}
