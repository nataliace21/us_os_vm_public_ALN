
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
                //Keep track of the candidate with the largest remainder.
                //Strict > keeps the first one (lowest base) in case of a tie.
                if (worst == null || slot.getRemainder(size) > worst.getRemainder(size)) {
                    worst = slot;
                }
            }
        }

        //If there is no slot big enough to contain the requested memory, it will return null
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
