/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MiniPC.model.AsignadorMemoria;

import MiniPC.model.PCB;

/**
 *
 * @author ricardosoto
 */
public interface AsignadorMemoria {
    boolean allocatePCB(PCB pcb);
    void deallocatePCB(PCB pcb);
    int getNextPC(PCB pcb);
    
}
