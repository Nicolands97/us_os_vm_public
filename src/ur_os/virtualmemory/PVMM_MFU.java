/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.virtualmemory;

import java.util.LinkedList;
import ur_os.memory.paging.PageTable;
import ur_os.memory.paging.PageTableEntry;

/**
 *
 * @author user
 */
public class PVMM_MFU extends ProcessVirtualMemoryManager{

    public PVMM_MFU(){
        type = ProcessVirtualMemoryManagerType.MFU;
    }
    
    @Override
    public int getVictim(LinkedList<Integer> memoryAccesses, PageTable pt) {
        LinkedList<Integer> validPages = new LinkedList<>();
        int i = 0;
        for (PageTableEntry pte : pt.getList()) {
            if (pte.isValid()) validPages.add(i);
            i++;
        }
        int victim = -1;
        int highestCount = Integer.MIN_VALUE;
        for (int page : validPages) {
            int count = 0;
            for (int access : memoryAccesses) {
                if (access == page) count++;
            }
            if (count > highestCount) {
                highestCount = count;
                victim = page;
            }
        }
        return victim;
    }
    
}
