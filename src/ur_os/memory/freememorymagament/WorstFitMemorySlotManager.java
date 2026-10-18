
package ur_os.memory.freememorymagament;

public class WorstFitMemorySlotManager extends FreeMemorySlotManager{

    public WorstFitMemorySlotManager(int memSize){
        super(memSize);
    }

    @Override
    public MemorySlot getSlot(int size) {

        MemorySlot worst = null;

        for (MemorySlot slot : list) {
            if (slot.canContain(size)) {
                
                if (worst == null || slot.getRemainder(size) > worst.getRemainder(size)) {
                    worst = slot;
                }
            }
        }

        if (worst == null) {
            System.out.println("Error - Memory cannot allocate a slot big enough for the requested memory");
            return null;
        }

        if (worst.getSize() == size) {
            list.remove(worst);
            return worst;
        }

        return worst.assignMemory(size);
    }

}
