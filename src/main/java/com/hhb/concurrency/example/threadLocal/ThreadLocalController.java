package com.hhb.concurrency.example.threadLocal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: huanghongbo
 * @Date: 2019-06-16 09:31
 * @Description:
 */
@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalController {


    @RequestMapping(value = "test", method = RequestMethod.GET)
    public Long getId() {
        return RequestHolder.get();
    }

}
