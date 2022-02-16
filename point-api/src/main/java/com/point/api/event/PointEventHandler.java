package com.point.api.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class PointEventHandler {

    @TransactionalEventListener
    public void transactionEvent(TransactionEvent event) {
        log.info(event.toString());
        //todo publish re-balance event
    }
}
