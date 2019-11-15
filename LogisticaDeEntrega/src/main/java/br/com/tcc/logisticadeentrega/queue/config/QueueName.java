package br.com.tcc.logisticadeentrega.queue.config;

public enum QueueName {
    SHIPMENT_STATE("shipment_state");

    private final String queueName;

    QueueName(final String queueName) {
        this.queueName = queueName;
    }

    public String getQueueName() {
         return this.queueName;
    }

}
