/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this.memory license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this.memory template
 */
package MiniPC.model.AsignadorMemoria;

import MiniPC.model.InformationRegister;
import MiniPC.model.Memory;
import MiniPC.model.MemoryRegister;
import MiniPC.model.PCB;
import MiniPC.model.Register;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author ricardosoto
 */
public class DynamicPartition implements AsignadorMemoria {
    private Memory memory;
    public DynamicPartition(Memory mem){
        this.memory = mem;
        
    }

    @Override
    public boolean allocatePCB(PCB pcb) {
         if(pcb==null){
            return false;
        }
        ArrayList<Integer> pcbData = pcb.getPCBData();
        ArrayList<MemoryRegister> instructions = pcb.getLoader().getInstrucionSet();
        //El PC
       
        if(this.memory.getCurrentIndex()+pcbData.size()+instructions.size() >this.memory.getSize()){                    
            //Error por desbordamiento de memoria
            return false;
        }
        
        pcb.setMemoryStartingIndex(this.memory.getCurrentIndex());
        for(int i = 0 ; i < pcbData.size(); i ++){
            Register infoRegister = new InformationRegister();
            infoRegister.setPCB(pcb);
            
            
            infoRegister.setValue(pcbData.get(i));
            this.memory.getRegisters().set(this.memory.getCurrentIndex(),Optional.of(infoRegister));
            this.memory.setCurrentIndex(this.memory.getCurrentIndex() + 1);
        }
        pcb.setProgramCounter(this.memory.getCurrentIndex());
        for(int i = 0 ; i < instructions.size(); i ++){
            Register memoryRegister = new MemoryRegister();           
            memoryRegister = instructions.get(i);
            memoryRegister.setPCB(pcb);
            this.memory.getRegisters().set(this.memory.getCurrentIndex(),Optional.of(memoryRegister));
            this.memory.setCurrentIndex(this.memory.getCurrentIndex() + 1);
        }
        pcb.setMemory(this.memory);        
        this.memory.getPcbs().add(pcb);
        return true;
        
        
    }

    @Override
    public void deallocatePCB(PCB pcb) {
           int index = pcb.getMemoryStartingIndex();
        int tamannioPcb = pcb.getInstructions().size()+pcb.getPCBData().size();      
               
        
        
        for(int i = 0 ; i < tamannioPcb; i++){                
            this.memory.getRegisters().remove(index);            
            
        }
        
        
        //se busca el PCB siguiente        
        PCB nextProcessToExe= null;
        PCB current = pcb;        
        
        int k = 0;        
        
        for(int i = 0 ; i < this.memory.getPcbs().size(); i++){
            if(this.memory.getPcbs().get(i).equals(pcb)){                
                try{                                        
                    nextProcessToExe  = this.memory.getPcbs().get(i+1);                    
                    nextProcessToExe.setProgramCounter(current.getMemoryStartingIndex()+nextProcessToExe.getPCBData().size());                    
                    nextProcessToExe.setMemoryStartingIndex(current.getMemoryStartingIndex());                                                                                                                           
                   
                    current = nextProcessToExe;                    
                    k = i+1;
                    break;
                } catch(IndexOutOfBoundsException e){
                    k=i+1;
                    break;
                }
                
            }
        }
       
        for(int r = k ;r < this.memory.getPcbs().size(); r++){
            try{                                        
                nextProcessToExe  = this.memory.getPcbs().get(r+1); 
                int finalIndexCurr = current.getMemoryStartingIndex()+current.getPCBData().size()+current.getInstructions().size();
                nextProcessToExe.setProgramCounter(finalIndexCurr+nextProcessToExe.getPCBData().size());                    
                nextProcessToExe.setMemoryStartingIndex(finalIndexCurr);                                                                                                                           
                current = nextProcessToExe;              
            } catch(IndexOutOfBoundsException e){
                    
            }
        }
        this.memory.getPcbs().remove(pcb);
        
        
        
                    
        
        //Se agrega al final de los reigstros la memoria liberada
        for(int j = 0 ; j < tamannioPcb; j++){
            this.memory.getRegisters().add(Optional.empty());                 
        }
        int newIndex = 0;
        while(this.memory.getRegisters().get(newIndex).isPresent()){
            newIndex++;
        } 
        this.memory.setCurrentIndex(newIndex);
        System.out.println("Final index: "+newIndex);
    }

    @Override
    public int getNextPC(PCB pcb) {
        return pcb.getProgramCounter()+1;
    }
    
}
