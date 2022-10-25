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
public class HRRN implements Algoritmos {
    private Queue<PCB> procesosEsperando;
    private PCB currentPCB;
    private int currentTime;
    private int currentTimeAux;
    private double actual;
    private double prox;
    private boolean programFinished;
    private ArrayList<String> status;
    
    
    public HRRN(){
       
        this.currentTime = 1;
        this.currentTimeAux = 1;
        this.procesosEsperando = new LinkedList<PCB>();
        this.programFinished = false;
    }
    
    //Lo que procura este método es que retornen el PCB(proceso) que se va a ejecutar, aunque deben ejecutarlo antes para
    // tener información contable del mismo
    @Override
    public PCB executeInstruction(Queue<PCB> colaProcesosCPU, PCController cont) {
        //ESTO ES PARTE DEL MÉTODO A IMPLEMENTAR
//---------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------------
 
        Queue<PCB> col = colaProcesosCPU;
        this.programFinished = false;
        
        for(PCB proceso: col){
           //los procesos llegan en el instante actual
           if(proceso.getArrivalTime()<=this.currentTimeAux && !this.procesosEsperando.contains(proceso)){   
               
              if(this.procesosEsperando.isEmpty()){
                   this.currentPCB = proceso;
                   this.currentPCB.setStatus("Exec");
               }               
               this.procesosEsperando.add(proceso);
               //col.remove(proceso);
               
               
               
           }
        }
        
        
        // result = ((currentTime-getArrivalTime())+getRagafa()) / getRagafa()
        for(PCB proceso: this.procesosEsperando){
            double actual = (double)((currentTime-this.currentPCB.getArrivalTime())+this.currentPCB.getPCBinstrucctionSize()) / this.currentPCB.getPCBinstrucctionSize();
            double prox = (double)((currentTime-proceso.getArrivalTime())+proceso.getPCBinstrucctionSize()) / proceso.getPCBinstrucctionSize(); 
            
            //System.out.println("Actual-->"+currentTime+this.currentPCB.getFileName()+"  Arivo  "+this.currentPCB.getArrivalTime()+"  Rafaga  "+this.currentPCB.getPCBinstrucctionSize());
            //System.out.println("Prox-->"+currentTime+proceso.getFileName()+"  Arivo  "+proceso.getArrivalTime()+"  Rafaga  "+proceso.getPCBinstrucctionSize());
            
            //System.out.println(currentTime)
            //System.out.println("PCB1->  "+prox+proceso.getFileName()+"    Actual->  "+actual+this.currentPCB.getFileName());
            if(prox > actual){
                this.currentPCB = proceso;
                 
            }
        }
        
       //System.out.println("Escogido->"+this.currentPCB.getFileName()); 
       //System.out.println("-------------------------------------------"); 
       PCB result =  this.currentPCB;     
       
       this.currentTimeAux += this.currentPCB.getPCBinstrucctionSize();
       
       
       if(result!=null){   
           this.status = result.executeInstruction(cont);
            if(this.currentPCB.programFinished()){   
           this.programFinished = true;
           this.procesosEsperando.remove(this.currentPCB);     
           this.currentPCB = this.procesosEsperando.peek();
           
       }}
       
       this.currentTime++;
       return result;
        

    }
    
    @Override
    public boolean programIsFinished() {
        return this.programFinished;
    }

    @Override
    public ArrayList<String> getStatus() {
        return this.status;    
    }

    @Override
    public int getTime() {
        return this.currentTime;
    }
    
}
