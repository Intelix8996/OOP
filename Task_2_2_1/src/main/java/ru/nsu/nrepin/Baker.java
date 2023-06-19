package ru.nsu.nrepin;

/**
 * Class that represents Baker.
 */
public class Baker extends StoppableThread {

    private static final int DEFAULT_WORK_DURATION = 3000;

    private final int workDuration;

    private final BlockingQueue<Integer> orderQueue;
    private final Storage storage;

    private boolean shouldStop = false;

    private final int bakerNumber;

    /**
     * Creates new baker with given parameters.
     *
     * @param experience baker's experience (bake time = experience * 3000ms)
     * @param number baker's id
     * @param orderQueue order queue that baker will use
     * @param storage storage that baker will use
     */
    public Baker(float experience, int number, BlockingQueue<Integer> orderQueue, Storage storage) {
        this.workDuration = (int) (DEFAULT_WORK_DURATION * experience);
        this.orderQueue = orderQueue;
        this.storage = storage;
        this.bakerNumber = number;
    }

    @Override
    public void run() {
        while (!shouldStop) {
            Integer id;
            try {
                id = orderQueue.take();
            } catch (InterruptedException e) {
                return;
            }

            System.out.printf("[Baker-%d] Take from queue %d%n", bakerNumber, id);

            try {
                Thread.sleep(workDuration);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                storage.store(id);
            } catch (InterruptedException e) {
                return;
            }

            System.out.printf("[Baker-%d] Put in storage %d%n", bakerNumber, id);
        }
    }

    @Override
    void requestStop() {
        shouldStop = true;
        interrupt();
    }
}
