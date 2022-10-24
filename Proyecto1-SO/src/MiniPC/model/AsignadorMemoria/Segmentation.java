/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MiniPC.model.AsignadorMemoria;

import MiniPC.model.Memory;
import MiniPC.model.PCB;

/**
 *
 * @author ricardosoto
 */
public class Segmentation implements AsignadorMemoria {
private Memory memory;
    
    public Segmentation(Memory mem){
        this.memory = mem;
        
    }
    @Override
    public boolean allocatePCB(PCB pcb) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deallocatePCB(PCB pcb) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getNextPC(PCB pcb) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
