/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MiniPC.model;

import MiniPC.model.AsignadorMemoria.AsignadorMemoria;
import MiniPC.model.AsignadorMemoria.DynamicPartition;
import MiniPC.model.AsignadorMemoria.FixedPartitioning;
import MiniPC.model.AsignadorMemoria.Paging;
import MiniPC.model.AsignadorMemoria.Segmentation;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

/**
 *
 * @author ricardosoto
 */
public class Memory {
    //La memoria para liberar y volverse a reaorganizar tiene una cola que lo permite
    private LinkedList<PCB> pcbs;
    private int size;    
    private ArrayList<Optional<Register>> registers;    
    private int START_INDEX = 10;
    private String method;
    private AsignadorMemoria memoryAsigner;
    private int allocationIndex = 0;
    private int allocatedMemorySize = 0; 
    private int currentIndex = 0;
   
    
    public Memory(int size){
        registers = new ArrayList<>();
        for(int i = 0 ; i <size ; i ++){
            registers.add(Optional.empty());                 
        }        
        this.size = size;
        this.pcbs = new LinkedList<>();
        this.setMethod("Particion Dinamica");
        
        switch (this.method) {
            case "Particion Dinamica":
                this.memoryAsigner = new DynamicPartition(this);
                break;
            case "Paginacion":
                this.memoryAsigner  = new Paging(this);
                break;
            case "Particion Fija":                    
                this.memoryAsigner  = new FixedPartitioning(this);
                break;
            case "Segmentacion":
                this.memoryAsigner  = new Segmentation(this);
                break;
            default:
                break;
        }
    }
    public void setMethod(String m){
        this.method = m;
    }
    
    public ArrayList<Optional<Register>> getInstructions(){
        return this.getRegisters();
    }
    private boolean spaceFull(int startingIndex, int space){        
        if(startingIndex + space > this.getSize()){
                        
            
            return true;   
        }
        if(this.getRegisters().get(startingIndex).isEmpty()){
            for(int i = startingIndex ; i < space; i ++){
                if(this.getRegisters().get(i).isPresent()){                    
                    
                    return true;
                }
            }
        }
        return false;
        
    }
    public void deallocatePCB(PCB pcb){
        this.memoryAsigner.deallocatePCB(pcb);
     
  
        
    }
    public int getNextPC(PCB pcb ){
        return this.memoryAsigner.getNextPC(pcb);
    }
    
    
    
    public void printMemory(){
        System.out.println("Memory printing....");
        for(int i = 0 ;i < this.getRegisters().size(); i ++){
            if(this.getRegisters().get(i).isPresent()){
                
                System.out.println(this.getRegisters().get(i).get().toBinaryString());
            }
        }
    }
    public boolean PCBfits(PCB pcb){
        ArrayList<Integer> pcbData = pcb.getPCBData();
        ArrayList<MemoryRegister> instructions = pcb.getLoader().getInstrucionSet();
        //El PC
       
        return this.getCurrentIndex()+pcbData.size()+instructions.size() <=this.getSize();
            //Error por desbordamiento de memoria
            
        
    }
    public LinkedList<PCB> getProcessesLoaded(){
        return this.getPcbs();
    }
    
    public boolean allocatePCB(PCB pcb){
     
     return this.memoryAsigner.allocatePCB(pcb);
       
    }
    
    
    
    
    
    
    
    public void clean(){
        this.getRegisters().clear();       
    }
    public int getAllocationIndex(){
        return this.allocationIndex;
    }
    
   
    public int geAllocatedMemorySize(){
        return this.getAllocatedMemorySize();
    }

    public int getSize() {
        return size;
    }

    /**
     * @return the pcbs
     */
    public LinkedList<PCB> getPcbs() {
        return pcbs;
    }

    /**
     * @param pcbs the pcbs to set
     */
    public void setPcbs(LinkedList<PCB> pcbs) {
        this.pcbs = pcbs;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the registers
     */
    public ArrayList<Optional<Register>> getRegisters() {
        return registers;
    }

    /**
     * @param registers the registers to set
     */
    public void setRegisters(ArrayList<Optional<Register>> registers) {
        this.registers = registers;
    }

    /**
     * @return the START_INDEX
     */
    public int getSTART_INDEX() {
        return START_INDEX;
    }

    /**
     * @param START_INDEX the START_INDEX to set
     */
    public void setSTART_INDEX(int START_INDEX) {
        this.START_INDEX = START_INDEX;
    }

    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param allocationIndex the allocationIndex to set
     */
    public void setAllocationIndex(int allocationIndex) {
        this.allocationIndex = allocationIndex;
    }

    /**
     * @return the allocatedMemorySize
     */
    public int getAllocatedMemorySize() {
        return allocatedMemorySize;
    }

    /**
     * @param allocatedMemorySize the allocatedMemorySize to set
     */
    public void setAllocatedMemorySize(int allocatedMemorySize) {
        this.allocatedMemorySize = allocatedMemorySize;
    }

    /**
     * @return the currentIndex
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * @param currentIndex the currentIndex to set
     */
    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
    
    
    
    
}
