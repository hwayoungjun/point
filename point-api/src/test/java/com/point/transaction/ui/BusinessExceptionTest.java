package com.point.transaction.ui;

import com.point.transaction.common.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessExceptionTest {

    @Test
    void businessException() {
        try {
            throw new BusinessException(999, "occur error.");
        } catch(BusinessException e) {
            assertEquals(999, e.getCode());
            assertEquals("occur error.", e.getMessage());
        }
    }
}