package com.point.api.ui;

import com.point.api.common.exception.BusinessException;
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