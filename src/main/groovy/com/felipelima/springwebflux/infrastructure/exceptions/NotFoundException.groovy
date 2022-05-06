package com.felipelima.springwebflux.infrastructure.exceptions

import org.springframework.http.HttpStatus

class NotFoundException extends BusinessException {

    NotFoundException() {
        super(null, HttpStatus.NOT_FOUND)
    }

    NotFoundException(String msg) {
        super(msg, HttpStatus.NOT_FOUND)
    }

}
